import React, { Component } from "react";
import { Link } from "react-router-dom";
import { deleteProjectTask } from "../../../actions/backlogActions";
import PropTypes from "prop-types";
import { connect } from "react-redux";
class ProjectTask extends Component {
  onDeleteClick = (backlog_id, pt_id) => {
    this.props.deleteProjectTask(backlog_id, pt_id);
  };

  render() {
    const { project_task } = this.props;
    let priorityString;
    let priorityCLass;

    if (project_task.priority === 1) {
      priorityString = "HIGH";
      priorityCLass = "bg-danger text-light";
    } else if (project_task.priority === 2) {
      priorityString = "MEDIUM";
      priorityCLass = "bg-warning text-light";
    } else if (project_task.priority === 3) {
      priorityString = "LOW";
      priorityCLass = "bg-info text-light";
    }

    return (
      <div className="card mb-1 bg-light">
        <div className={`card-header text-primary ${priorityCLass}`}>
          ID: {project_task.projectSequence} -- Priority: {priorityString}
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">{project_task.summary}</h5>
          <p className="card-text text-truncate ">
            {project_task.acceptanceCriteria}
          </p>
          <Link
            to={`/updateProjectTask/${project_task.projectIdentifier}/${project_task.projectSequence}`}
            className="btn btn-success"
          >
            View / Update
          </Link>
          &nbsp;
          <button
            className="btn btn-danger ml-4"
            onClick={this.onDeleteClick.bind(
              this,
              project_task.projectIdentifier,
              project_task.projectSequence
            )}
          >
            Delete
          </button>
        </div>
      </div>
    );
  }
}

ProjectTask.prototypes = {
  deleteProjectTask: PropTypes.func.isRequired,
};

export default connect(null, { deleteProjectTask })(ProjectTask);

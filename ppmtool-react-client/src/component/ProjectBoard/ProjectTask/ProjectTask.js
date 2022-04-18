import React, { Component } from "react";

class ProjectTask extends Component {
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
          <a href="" className="btn btn-primary">
            View / Update
          </a>
          &nbsp;
          <button className="btn btn-danger ml-4">Delete</button>
        </div>
      </div>
    );
  }
}
export default ProjectTask;

import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { getBacklog } from "../../actions/backlogActions";
import { getProject } from "../../actions/projectActions";
import ProjectBoardBacklogAlgo from "./ProjectBoardBacklogAlgo";

class ProjectBoard extends Component {
  //constructor to handle errors
  constructor() {
    super();
    this.state = {
      errors: {},
    };
  }
  componentDidMount() {
    const { id } = this.props.match.params;
    this.props.getBacklog(id);
    this.props.getProject(id, this.props.history);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }
  }

  render() {
    const { id } = this.props.match.params;

    const { project_tasks } = this.props.backlog;

    const { project } = this.props.project;

    const { errors } = this.state;

    const projName = project.projectName?.toUpperCase();

    return (
      <div className="container">
        <Link to={`/addProjectTask/${id}`} className="btn btn-success mb-3">
          <i className="fas fa-plus-circle"> Create Project Task</i>
        </Link>
        <h2 className="text-center">{projName}</h2>
        <hr />
        {ProjectBoardBacklogAlgo(errors, project_tasks)}
      </div>
    );
  }
}

ProjectBoard.propTypes = {
  backlog: PropTypes.object.isRequired,
  project: PropTypes.object.isRequired,
  getBacklog: PropTypes.func.isRequired,
  getProject: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  backlog: state.backlog,
  project: state.project,
  errors: state.errors,
});

export default connect(mapStateToProps, { getBacklog, getProject })(
  ProjectBoard
);

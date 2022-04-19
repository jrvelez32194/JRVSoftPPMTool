import Backlog from "./Backlog";
const ProjectBoardBacklogAlgo = (errors, project_tasks) => {
  if (project_tasks.length < 1) {
    if (errors.message) {
      return (
        <div className="alert alert-danger text-center" role="alert">
          {errors.message}
        </div>
      );
    } else {
      return (
        <div className="alert alert-info text-center" role="alert">
          No Project Task on this board
        </div>
      );
    }
  } else {
    return <Backlog project_tasks_prop={project_tasks} />;
  }
};

export default ProjectBoardBacklogAlgo;

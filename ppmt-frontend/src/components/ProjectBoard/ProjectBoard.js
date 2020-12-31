import React, { Component } from "react";
import { Link } from "react-router-dom";
import Backlog from "./Backlog";
import * as actions from "../../store/Actions/index";
import { connect } from "react-redux";

class ProjectBoard extends Component {
  state = {
    error: {},
  };
  componentDidMount() {
    //console.log("executing componentDidMount ::", this.props.match.params.id);
    const { id } = this.props.match.params;

    this.props.fetchProjectTasks(id);
  }
  componentDidUpdate() {
    // console.log("executing componentDidUpdate ::");

    if (this.props.errors !== this.state.error) {
      this.setState({
        ...this.state,
        error: this.props.errors,
      });
    }
  }

  removeProjectTask = (projectId, taskSequence) => {
    console.log(
      "removeProjectTask ",
      projectId,
      taskSequence,
      this.props.history,
    );
    this.props.removeTask(projectId, taskSequence, this.props.history);
  };

  render() {
    //console.log("executing render ::");

    const { id } = this.props.match.params;
    const { error } = this.state;
    const CreateProjectTask = () => (
      <div>
        <Link to={`/project-task-form/${id}`} className="btn btn-primary mb-3">
          <i className="fas fa-plus-circle"> Create Project Task</i>
        </Link>
        <br />
        <hr />
      </div>
    );

    const projectBoard = (errors, projectTasks) => {
      if (projectTasks.length === 0) {
        if (errors.projectNotFound === "No project task avaiable") {
          return (
            <React.Fragment>
              <CreateProjectTask />
              <div className="alert alert-info text-center">
                No task added to the project backlog
              </div>
            </React.Fragment>
          );
        } else if (errors.projectNotFound) {
          console.log(" inside  danger::", projectTasks.length);
          return (
            <div className="alert alert-danger text-center">
              {errors.projectNotFound}
            </div>
          );
        }
      } else {
        return (
          <React.Fragment>
            <CreateProjectTask />
            <Backlog
              projectTaskList={projectTasks}
              removeProjectTask={this.removeProjectTask}
            />
          </React.Fragment>
        );
      }
    };

    return (
      <div>
        <div className="container">
          {this.props.projectTasks.length >= 0
            ? projectBoard(error, this.props.projectTasks)
            : " "}
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    projectTasks: state.backlogReducers.projectTasks,
    errors: state.errorReducers.errors,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    fetchProjectTasks: (projectId) =>
      dispatch(actions.fetchBacklogTasks(projectId)),
    removeTask: (projectId, taskSequence, history) =>
      dispatch(actions.deleteProjectTask(projectId, taskSequence, history)),
  };
};
export default connect(mapStateToProps, mapDispatchToProps)(ProjectBoard);

import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import * as actions from "../../../store/Actions/index";

class ProjectTaskForm extends Component {
  state = {
    id: "",
    summary: "",
    status: "",
    priority: 0,
    acceptenceCreteria: "",
    dueDate: "",
    projectIdentifier: "",
    error: {},
  };

  componentDidMount() {
    this.setState({
      projectIdentifier: this.props.match.params.id,
    });
    if (this.props.match.path === "/update-project-task/:id/:sequence") {
      console.log("update form", this.props.match);
      const { id, sequence } = this.props.match.params;
      this.props.loadTaskToUpdate(id, sequence);
    }
  }

  componentDidUpdate(prevProps, prevState) {
    if (this.props.errors !== this.state.error) {
      this.setState({
        ...this.state,
        error: this.props.errors,
      });
    }
    console.log("componentDidMount", this.props.taskToUpdate);
    if (
      this.props.match.path === "/update-project-task/:id/:sequence" &&
      this.props.taskToUpdate.id !== this.state.id
    ) {
      const {
        id,
        summary,
        status,
        priority,
        acceptenceCreteria,
        dueDate,
      } = this.props.taskToUpdate;
      console.log("summary::", summary, "status::", status);
      this.setState({
        ...this.state,
        id: id,
        summary: summary,
        status: status,
        priority: priority,
        acceptenceCreteria: acceptenceCreteria,
        dueDate: dueDate,
      });
    }
  }

  inputChangeHandler = () => {
    console.log(event.target.name, " ", event.target.value);
    this.setState({
      ...this.state,
      [event.target.name]: event.target.value,
    });
  };

  onSubmitHandler = () => {
    event.preventDefault();
    console.log(this.state.projectIdentifier);
    const {
      id: dbId,
      summary,
      status,
      priority,
      acceptenceCreteria,
      dueDate,
      projectIdentifier,
    } = this.state;
    const projectTask = {
      summary: summary,
      acceptenceCreteria: acceptenceCreteria,
      status: status,
      priority: priority,
      dueDate: dueDate,
    };

    switch (this.props.match.path) {
      case "/project-task-form/:id":
        this.props.onAddingProjectTask(
          projectTask,
          projectIdentifier,
          this.props.history,
        );
        break;
      case "/update-project-task/:id/:sequence":
        const { id, sequence } = this.props.match.params;
        console.log("db id::", dbId, " state id", this.state.id);
        const task = {
          ...projectTask,
          id: this.state.id,
          projectSequence: sequence,
          projectIdentifier: projectIdentifier,
        };
        this.props.onUpdatingProjectTask(
          id,
          sequence,
          task,
          this.props.history,
        );
        break;

      default:
        break;
    }
  };

  render() {
    const { id } = this.props.match.params;
    const { projectIdentifier, error } = this.state;
    //console.log("task to update::", this.props.taskToUpdate);
    return (
      <div className="add-PBI">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <Link to={`/projectboard/${id}`} className="btn btn-light">
                Back to Project Board
              </Link>
              <h4 className="display-4 text-center">
                Add /Update Project Task
              </h4>
              <p className="lead text-center">Project Name + Project Code</p>
              <form onSubmit={this.onSubmitHandler}>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg"
                    name="summary"
                    placeholder="Project Task summary"
                    value={this.state.summary}
                    onChange={this.inputChangeHandler}
                  />
                  {error.summary && <div>{error.summary}</div>}
                </div>
                <div className="form-group">
                  <textarea
                    className="form-control form-control-lg"
                    placeholder="Acceptance Criteria"
                    name="acceptenceCreteria"
                    value={this.state.acceptenceCreteria}
                    onChange={this.inputChangeHandler}
                  ></textarea>
                </div>
                <h6>Due Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="dueDate"
                    value={this.state.dueDate}
                    onChange={this.inputChangeHandler}
                  />
                </div>
                <div className="form-group">
                  <select
                    className="form-control form-control-lg"
                    name="priority"
                    value={this.state.priority}
                    onChange={this.inputChangeHandler}
                  >
                    <option value={0}>Select Priority</option>
                    <option value={1}>High</option>
                    <option value={2}>Medium</option>
                    <option value={3}>Low</option>
                  </select>
                </div>

                <div className="form-group">
                  <select
                    className="form-control form-control-lg"
                    name="status"
                    value={this.state.status}
                    onChange={this.inputChangeHandler}
                  >
                    <option value="">Select Status</option>
                    <option value="TO_DO">TO DO</option>
                    <option value="IN_PROGRESS">IN PROGRESS</option>
                    <option value="DONE">DONE</option>
                  </select>
                </div>

                <input
                  type="submit"
                  className="btn btn-primary btn-block mt-4"
                />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    errors: state.errorReducers.errors,
    taskToUpdate: state.backlogReducers.projectTask,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    onAddingProjectTask: (task, projectId, history) =>
      dispatch(actions.addProjectTask(task, projectId, history)),
    loadTaskToUpdate: (projectId, taskSequence) =>
      dispatch(actions.fetchprojectTask(projectId, taskSequence)),
    onUpdatingProjectTask: (projectId, taskSequence, projectTask, history) =>
      dispatch(
        actions.updateProjectTask(
          projectId,
          taskSequence,
          projectTask,
          history,
        ),
      ),
  };
};
export default connect(mapStateToProps, mapDispatchToProps)(ProjectTaskForm);

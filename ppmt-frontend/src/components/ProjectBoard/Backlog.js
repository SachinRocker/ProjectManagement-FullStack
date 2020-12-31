import React, { Component } from "react";
import ProjectTask from "./ProjectTask/ProjectTask";

class Backlog extends Component {
  filterTasksByStatusHandler = (taskList, status) => {
    if (taskList !== null) {
      return taskList.map((task) => {
        if (task.status === status)
          return (
            <ProjectTask
              key={task.projectSequence}
              projectTask={task}
              removeProjectTask={this.props.removeProjectTask}
            />
          );
      });
    }
  };
  render() {
    return (
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>TO DO</h3>
              </div>
            </div>

            {/** <!-- SAMPLE PROJECT TASK STARTS HERE --> */}
            {this.filterTasksByStatusHandler(
              this.props.projectTaskList,
              "TO_DO",
            )}
            {/* <!-- SAMPLE PROJECT TASK ENDS HERE -->*/}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-primary text-white">
                <h3>In Progress</h3>
              </div>
            </div>

            {this.filterTasksByStatusHandler(
              this.props.projectTaskList,
              "IN_PROGRESS",
            )}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-success text-white">
                <h3>Done</h3>
              </div>
            </div>
            {this.filterTasksByStatusHandler(
              this.props.projectTaskList,
              "DONE",
            )}
          </div>
        </div>
      </div>
    );
  }
}
export default Backlog;

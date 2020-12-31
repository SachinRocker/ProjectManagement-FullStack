import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import * as actions from "../../../store/Actions/index";

class ProjectTask extends Component {
  priorityCodeHandler = (priority) => {};

  render() {
    const {
      summary,
      acceptanceCriteria,
      projectSequence,
      createdAt,
      dueDate,
      priority,
      status,
      projectIdentifier,
    } = this.props.projectTask;

    let priorityString = "";
    let priorityClass = "";
    switch (priority) {
      case 1:
        priorityString = "HIGH";
        priorityClass = "bg-danger text-light";
        break;
      case 2:
        priorityString = "MEDIUM";
        priorityClass = "bg-warning text-light";
        break;
      case 3:
        priorityString = "LOW";
        priorityClass = "bg-info text-light";
        break;

      default:
        break;
    }

    // const { priorityString, priorityClass } = this.priorityCodeHandler(priority);
    return (
      <div className="card mb-1 bg-light">
        <div className={`card-header text-primary ${priorityClass}`}>
          ID: {projectSequence} -- Priority: {priorityString}
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">{summary}</h5>
          <p className="card-text text-truncate ">{acceptanceCriteria}</p>
          <Link
            to={`/update-project-task/${projectIdentifier}/${projectSequence}`}
            className="btn btn-primary"
          >
            View / Update
          </Link>

          <button
            className="btn btn-danger ml-4"
            onClick={this.props.removeProjectTask.bind(
              this,
              projectIdentifier,
              projectSequence,
            )}
          >
            Delete
          </button>
        </div>
      </div>
    );
  }
}

export default ProjectTask;

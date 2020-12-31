import {
  ADD_PROJECT_TASK,
  GET_PROJECT_TASK,
  DELETE_PROJECT_TASK,
  GET_BACKLOG,
  GET_ERRORS,
} from "./ActionTypes";

import axios from "axios";

export const addTask = (task) => {
  return {
    type: ADD_PROJECT_TASK,
    addedTask: task,
  };
};

export const setErrorResponse = (errorData) => {
  return {
    type: GET_ERRORS,
    error: errorData,
  };
};

export const addProjectTask = (task, projectId, history) => {
  console.log("addProjectTask, ", projectId);
  return (dispatch) => {
    axios
      .post(`http://localhost:8080/backlog/${projectId}`, task)
      .then((res) => {
        history.push(`/projectboard/${projectId}`);
        dispatch(addTask(res.data));

        console.log("addProjectTask ", res.data);
      })
      .catch((err) => {
        console.log("error:: ", err.response);
        dispatch(setErrorResponse(err.response));
      });
  };
};
export const setLoadedTasks = (tasks) => {
  return {
    type: GET_BACKLOG,
    backlogTasks: tasks,
  };
};
export const fetchBacklogTasks = (projectId) => {
  return (dispatch) => {
    axios
      .get(`http://localhost:8080/backlog/${projectId}`)
      .then((res) => {
        dispatch(setLoadedTasks(res.data));
      })
      .catch((err) => {
        dispatch(setErrorResponse(err.response.data));
      });
  };
};

export const setProjectTask = (task) => {
  return {
    type: GET_PROJECT_TASK,
    projectTask: task,
  };
};

export const fetchprojectTask = (projectId, taskSequence) => {
  return (dispatch) => {
    axios
      .get(`http://localhost:8080/backlog/${projectId}/${taskSequence}`)
      .then((res) => {
        dispatch(setProjectTask(res.data));
      })
      .catch((err) => {
        dispatch(setErrorResponse(err.response.data));
      });
  };
};

export const deleteTask = (taskSequence) => {
  return {
    type: DELETE_PROJECT_TASK,
    taskSequence: taskSequence,
  };
};
export const deleteProjectTask = (projectId, taskSequence, history) => {
  return (dispatch) => {
    if (
      window.confirm(
        "Deleting this task will erase all related data to this task",
      )
    ) {
      axios
        .delete(`http://localhost:8080/backlog/${projectId}/${taskSequence}`)
        .then((res) => {
          dispatch(deleteTask(taskSequence));
          window.location.reload(false);
          //history.push(`/projectboard/${projectId}`);
          //history.push(`/dashboard`);
        })
        .catch((err) => {});
    }
  };
};

export const updateProjectTask = (
  projectId,
  taskSequence,
  projectTask,
  history,
) => {
  return (dispatch) => {
    axios
      .patch(
        `http://localhost:8080/backlog/${projectId}/${taskSequence}`,
        projectTask,
      )
      .then((res) => {
        history.push(`/projectboard/${projectId}`);
        //dispatch(setUpdatedTask(res.data));
      })
      .catch((err) => {
        console.log("updateProjectTask::", err.response.data);
        dispatch(setErrorResponse(err.response.data));
      });
  };
};

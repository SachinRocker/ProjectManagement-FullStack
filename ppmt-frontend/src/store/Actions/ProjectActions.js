import {
  GET_ERRORS,
  GET_PROJECT,
  GET_PROJECTID,
  DELETE_PROJECT,
} from "../Actions/ActionTypes";
import axios from "axios";

//
export const setCreateProjectError = (error) => {
  return {
    type: GET_ERRORS,
    error: error,
  };
};
export const setLoadedprojects = (projects) => {
  return {
    type: GET_PROJECT,
    loadedProjects: projects,
  };
};

export const deleteTheProject = (id) => {
  return {
    type: DELETE_PROJECT,
    projectId: id,
  };
};

//creating a project
export const createProject = (project, history) => {
  return (dispatch) => {
    axios
      .post("http://localhost:8080/project", project)
      .then((res) => {
        console.log("res", res);
        history.push("/dashboard");
        dispatch(setCreateProjectError({}));
      })
      .catch((err) => {
        console.log(err.response);
        dispatch(setCreateProjectError(err.response.data));
      });
  };
};

export const deleteProject = (projectId) => {
  return (dispatch) => {
    if (window.confirm(" Do you want to delete this project?")) {
      axios
        .delete(`http://localhost:8080/project/${projectId}`)
        .then((res) => {
          dispatch(deleteTheProject(projectId));
        })
        .catch((err) => {
          console.log(err.response);
        });
    }
  };
};

export const updateProject = (project, history) => {
  return (dispatch) => {
    axios
      .put("http://localhost:8080/project", project)
      .then((res) => {
        console.log("res", res);
        history.push("/dashboard");
        dispatch(setCreateProjectError({}));
      })
      .catch((err) => {
        console.log(err.response);
        dispatch(setCreateProjectError(err.response.data));
      });
  };
};
export const loadProject = () => {
  return (dispatch) => {
    axios
      .get("http://localhost:8080/projects")
      .then((res) => {
        console.log("res", res);
        dispatch(setLoadedprojects(res.data));
      })
      .catch((err) => {
        console.log(err.response);
        // dispatch(setCreateProjectError(err.response.data));
      });
  };
};

export const setProjectToUpdate = (project) => {
  return {
    type: GET_PROJECTID,
    project: project,
  };
};

export const fetchProjectToUpdate = (projectId) => {
  return (dispatch) => {
    axios
      .get(`http://localhost:8080/project/${projectId}`)
      .then((res) => {
        console.log("res", res.data);

        dispatch(setProjectToUpdate(res.data));
      })
      .catch((err) => {
        console.log(err.response);
        dispatch(setCreateProjectError(err.response.data));
      });
  };
};

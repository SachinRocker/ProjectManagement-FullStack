import {
  ADD_PROJECT_TASK,
  GET_BACKLOG,
  GET_PROJECT_TASK,
  DELETE_PROJECT_TASK,
} from "../Actions/ActionTypes";
const initialState = {
  projectTasks: [],
  projectTask: {},
};

const getBacklogTasks = (state, action) => {
  return {
    ...state,
    projectTasks: action.backlogTasks,
  };
};

const deleteBacklogTask = (state, action) => {
  const remainingTasks = state.projectTasks.filter(
    (task) => task.projectSequence !== action.taskSequence,
  );
  return {
    ...state,
    projectTasks: remainingTasks,
  };
};

const getBacklogTask = (state, action) => {
  console.log("getBacklogTask::", action.projectTask);
  return {
    ...state,
    projectTask: action.projectTask,
  };
};
const reducer = (state = initialState, action) => {
  switch (action.type) {
    case GET_BACKLOG:
      return getBacklogTasks(state, action);

    case GET_PROJECT_TASK:
      return getBacklogTask(state, action);
    case DELETE_PROJECT_TASK:
      return deleteBacklogTask(state, action);
    default:
      return state;
  }
};

export default reducer;

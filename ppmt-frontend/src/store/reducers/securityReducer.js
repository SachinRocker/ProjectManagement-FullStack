import {
  REGISTER_USER,
  AUTH_USER,
  SET_CURRENT_USER,
} from "../Actions/ActionTypes";

const initialState = {
  user: {},
  validToken: false,
};

const isTokenValid = (token) => {
  if (token) {
    return true;
  } else {
    return false;
  }
};

const registeredUser = (state, action) => {
  return {
    ...state,
    user: action.payload,
  };
};

const setCurrentUser = (state, action) => {
  console.log("setCurrentUser ", action.payload);
  return {
    ...state,
    validToken: isTokenValid(action.payload),
    user: action.payload,
  };
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case REGISTER_USER:
      return registeredUser(state, action);

    case SET_CURRENT_USER:
      return setCurrentUser(state, action);

    default:
      return state;
  }
};

export default reducer;

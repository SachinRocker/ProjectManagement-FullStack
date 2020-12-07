import {GET_ERRORS} from "../Actions/ActionTypes"

const initialState = {
  errors:""
}

const createProjectError = (state, action) => {
    return {
        ...state,
        errors: action.error,
    }
}


const reducer = (state = initialState, action) => {
    switch (action.type) {
        case GET_ERRORS:
            return createProjectError(state,action);
        default:
            return state;

    }
}
export default reducer;


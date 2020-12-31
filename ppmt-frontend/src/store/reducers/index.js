import { combineReducers } from 'redux'
import errorReducer from "./errorReducer"
import projectReducer from "./ProjectReducer"
import backlogReducer from "./backlogReducer"



export default combineReducers({
    errorReducers: errorReducer,
    projectReducers: projectReducer,
    backlogReducers: backlogReducer
})
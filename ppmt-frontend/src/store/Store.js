import { createStore, applyMiddleware, compose } from "redux"
import thunk from "redux-thunk";
import rootReducer from "./reducers";

const initialState = {};
const middleware = [thunk];
let store;
const composeEnhancer = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

if (window.navigator.userAgent.includes("Chrome")) {
    store = createStore(
        rootReducer,
        initialState,
       composeEnhancer(applyMiddleware(...middleware))
    )
    
} else {
     store = createStore(
        rootReducer,
        initialState,
        composeEnhancer(applyMiddleware(...middleware))
        
    )
}
export default store;
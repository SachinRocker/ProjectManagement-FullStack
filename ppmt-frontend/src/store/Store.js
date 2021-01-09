import { createStore, applyMiddleware, compose } from "redux";
import thunk from "redux-thunk";
import rootReducer from "./reducers";

const initialState = {};
const middleware = [thunk];
let store;
const composeEnhancer = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
const reactReduxDevTools =
  window.__REDUX_DEVTOOLS_EXTENSION_ && window.__REDUX_DEVTOOLS_EXTENSION_();

if (window.navigator.userAgent.includes("Chrome")) {
  store = createStore(
    rootReducer,
    initialState,
    composeEnhancer(applyMiddleware(...middleware)),
  );
} else {
  console.log("no dev tools");
  store = createStore(
    rootReducer,
    initialState,
    compose(applyMiddleware(...middleware)),
  );
}
export default store;

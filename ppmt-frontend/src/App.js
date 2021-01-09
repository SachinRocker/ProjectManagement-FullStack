import logo from "./logo.svg";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import {
  BrowserRouter,
  Route,
  Switch,
  Redirect,
  withRouter,
} from "react-router-dom";
import ProjectForm from "./components/Project/ProjectForm";
import ProjectBoard from "./components/ProjectBoard/ProjectBoard";
import ProjectTaskForm from "./components/ProjectBoard/ProjectTask/ProjectTaskForm";
import { Provider } from "react-redux";
import store from "./store/Store";
import LandingPage from "./components/Layout/LandingPage";
import RegisterUser from "./components/UserAccountManager/RegisterUser";
import Auth from "./components/UserAccountManager/Auth";
import setJWTToken from "./store/securityutils/setJWTToken";
import jwtDecode from "jwt-decode";
import { SET_CURRENT_USER } from "./store/Actions/ActionTypes";
import * as actions from "./store/Actions/index";
import SecuredRoutes from "./store/securityutils/securedRoutes";

const token = localStorage.jwtToken;
if (token) {
  setJWTToken(token);
  const decode = jwtDecode(token);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decode,
  });

  const currentTime = Date.now() / 1000;
  if (decode.exp < currentTime) {
    store.dispatch(actions.logout());
    window.location.href = "/";
  } else {
    console.log("token.exp ", token.exp, " currentTime ", currentTime);
  }
}

function App() {
  // let routeToComponent
  let routeToComponent = (
    //avaiable route

    //secured route
    <Switch>
      <Route path="/" exact component={LandingPage} />
      <Route path="/register-account" exact component={RegisterUser} />
      <Route path="/login" exact component={Auth} />
      <SecuredRoutes path="/dashboard" exact component={Dashboard} />
      <SecuredRoutes path="/addProject" exact component={ProjectForm} />
      <SecuredRoutes path="/projectboard/:id" exact component={ProjectBoard} />
      <SecuredRoutes
        path="/project-task-form/:id"
        exact
        component={ProjectTaskForm}
      />
      <SecuredRoutes path="/updateProject/:id" exact component={ProjectForm} />
      <SecuredRoutes
        path="/update-project-task/:id/:sequence"
        exact
        component={ProjectTaskForm}
      />
      <Redirect to="/" />
    </Switch>
  );
  return (
    <Provider store={store}>
      <BrowserRouter>
        <div className="App">
          <Header />
          {routeToComponent}
        </div>
      </BrowserRouter>
    </Provider>
  );
}

export default App;

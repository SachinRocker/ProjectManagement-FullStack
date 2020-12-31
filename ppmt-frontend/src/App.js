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
function App() {
  const routeToComponent = (
    <Switch>
      <Route path="/dashboard" exact component={Dashboard} />
      <Route path="/addProject" exact component={ProjectForm} />
      <Route path="/projectboard/:id" exact component={ProjectBoard} />
      <Route path="/project-task-form/:id" exact component={ProjectTaskForm} />
      <Route path="/updateProject/:id" exact component={ProjectForm} />
      <Route
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

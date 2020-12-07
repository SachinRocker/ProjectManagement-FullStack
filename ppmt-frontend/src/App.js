import logo from './logo.svg';
import './App.css';
import Dashboard from "./components/Dashboard"
import Header from "./components/Layout/Header"
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import {BrowserRouter, Route,Switch, Redirect,withRouter } from "react-router-dom"
import AddProject from './components/Project/AddProject';
import { Provider } from "react-redux"
import store from "./store/Store";
function App() {
  const routeToComponent = (
                  <Switch>
                  <Route path="/dashboard" exact component={Dashboard}/> 
                  <Route path="/addProject" exact component={AddProject}/> 
                  <Redirect to="/" />
                  </Switch>

                )
  return (
    <Provider store={store}>
    <BrowserRouter>
    <div className="App">
    <Header/>
    {routeToComponent}
     
    </div>
      </BrowserRouter>
      </Provider>
  );
}

export default App;

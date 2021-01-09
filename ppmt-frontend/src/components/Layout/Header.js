import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import * as actions from "../../store/Actions/index";

class Header extends Component {
  handelLogout = () => {
    this.props.logoutUser();
    window.location.href = "/";
  };
  render() {
    const { validToken, user } = this.props.security;
    let authenticatedUser = null;
    if (validToken) {
      authenticatedUser = (
        <React.Fragment>
          <div className="collapse navbar-collapse" id="mobile-nav">
            <ul className="navbar-nav mr-auto">
              <li className="nav-item">
                <a className="nav-link" href="/dashboard">
                  Dashboard
                </a>
              </li>
            </ul>

            <ul className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link className="nav-link " to="/">
                  <i className="fa fa-user-circle mr-1"> {user.fullname}</i>
                </Link>
              </li>
              <li className="nav-item">
                <Link
                  className="nav-link"
                  to="/logout"
                  onClick={this.handelLogout}
                >
                  Logout
                </Link>
              </li>
            </ul>
          </div>
        </React.Fragment>
      );
    } else {
      authenticatedUser = (
        <React.Fragment>
          <div className="collapse navbar-collapse" id="mobile-nav">
            <ul className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link className="nav-link " to="/register-account">
                  Sign Up
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/login">
                  Login
                </Link>
              </li>
            </ul>
          </div>
        </React.Fragment>
      );
    }
    return (
      <nav className="navbar navbar-expand-sm navbar-dark bg-primary mb-4">
        <div className="container">
          <a className="navbar-brand" href="Dashboard.html">
            Personal Project Management Tool
          </a>
          <button
            className="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#mobile-nav"
          >
            <span className="navbar-toggler-icon" />
          </button>

          {authenticatedUser}
        </div>
      </nav>
    );
  }
}
const mapStateToProps = (state) => {
  return {
    security: state.securityReducers,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    logoutUser: () => dispatch(actions.logout()),
  };
};
export default connect(mapStateToProps, mapDispatchToProps)(Header);

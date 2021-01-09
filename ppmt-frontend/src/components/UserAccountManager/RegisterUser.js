import React, { Component } from "react";
import { connect } from "react-redux";
import * as actions from "../../store/Actions/index";
import classnames from "classnames";

class RegisterUser extends Component {
  state = {
    fullname: "",
    username: "",
    password: "",
    confirmPassword: "",
    isEnable: false,
    error: {},
  };
  componentDidMount() {
    if (this.props.security.validToken) {
      this.props.history.push("/dashboard");
    }
  }

  onInputChangeHandler = () => {
    console.log(event.target.name, " ", event.target.value);
    this.setState({
      ...this.state,
      [event.target.name]: event.target.value,
    });
  };

  onSubmitHandler = () => {
    event.preventDefault();
    console.log("onSubmitHandler");

    const userData = {
      fullname: this.state.fullname,
      username: this.state.username,
      password: this.state.password,
      confirmPassword: this.state.confirmPassword,
    };

    this.props.userRegistrationHandler(userData, this.props.history);
  };

  componentDidUpdate = () => {
    if (this.props.errors !== this.state.error) {
      console.log("registration error::", this.props.errors);
      this.setState({
        error: this.props.errors,
      });
    }
  };
  render() {
    const {
      fullname,
      username,
      password,
      confirmPassword,
      isEnable,
      error,
    } = this.state;
    return (
      <div className="register">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h1 className="display-4 text-center">Sign Up</h1>
              <p className="lead text-center">Create your Account</p>
              <form action="create-profile.html">
                <div className="form-group">
                  <input
                    type="text"
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": error.fullname,
                    })}
                    placeholder="Name"
                    name="fullname"
                    onChange={this.onInputChangeHandler}
                    value={this.state.fullname}
                  />
                  {error.fullname && (
                    <div className="invalid-feedback">
                      <p>{error.fullname}</p>
                    </div>
                  )}
                </div>
                <div className="form-group">
                  <input
                    type="email"
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": error.username,
                    })}
                    placeholder="Email Address"
                    name="username"
                    onChange={this.onInputChangeHandler}
                    value={this.state.username}
                  />
                  {error.username && (
                    <div className="invalid-feedback">
                      <p>{error.username}</p>
                    </div>
                  )}
                </div>
                <div className="form-group">
                  <input
                    type="password"
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": error.password,
                    })}
                    placeholder="Password"
                    name="password"
                    onChange={this.onInputChangeHandler}
                    value={this.state.password}
                  />
                  {error.password && (
                    <div className="invalid-feedback">
                      <p>{error.password}</p>
                    </div>
                  )}
                </div>
                <div className="form-group">
                  <input
                    type="password"
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": error.confirmPassword,
                    })}
                    placeholder="Confirm Password"
                    name="confirmPassword"
                    onChange={this.onInputChangeHandler}
                    value={this.state.confirmPassword}
                  />
                  {error.confirmPassword && (
                    <div className="invalid-feedback">
                      <p>{error.confirmPassword}</p>
                    </div>
                  )}
                </div>
                <input
                  type="submit"
                  className="btn btn-info btn-block mt-4"
                  disabled={
                    !(fullname && password && username && confirmPassword)
                  }
                  onClick={this.onSubmitHandler}
                />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
const mapStateToProps = (state) => {
  return {
    errors: state.errorReducers.errors,
    security: state.securityReducers,
  };
};
const mapDispatchToProps = (dispatch) => {
  return {
    userRegistrationHandler: (user, history) =>
      dispatch(actions.registerUser(user, history)),
  };
};
export default connect(mapStateToProps, mapDispatchToProps)(RegisterUser);

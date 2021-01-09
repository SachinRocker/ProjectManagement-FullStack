import React, { Component } from "react";
import { connect } from "react-redux";
import * as actions from "../../store/Actions/index";
import classnames from "classnames";

class Auth extends Component {
  state = {
    username: "",
    password: "",
    error: {},
    user: {},
    isTokenValid: false,
  };

  componentDidMount() {
    if (this.props.security.validToken) {
      this.props.history.push("/dashboard");
    }
  }

  onChangeHandler = () => {
    this.setState({
      ...this.state,
      [event.target.name]: event.target.value,
    });
  };
  onSubmitHandler = () => {
    event.preventDefault();
    const userData = {
      username: this.state.username,
      password: this.state.password,
    };
    console.log("user data::", userData);
    this.props.authenticateUser(userData);
  };

  // componentWillReceiveProps = () => {
  //   if (this.props.security.user) {
  //     console.log("to dashboard....");
  //     this.props.history.push("/dashboard");
  //   }
  // };
  componentDidUpdate = () => {
    if (this.props.security.validToken === true) {
      console.log("to dashboard....");
      this.props.history.push("/dashboard");
    }
    if (this.props.error !== this.state.error) {
      console.log("componentDidUpdate::", this.props.error);
      this.setState({
        error: this.props.error,
      });
    }
  };

  render() {
    const { error } = this.state;
    console.log("error auth::", error.username);

    return (
      <div className="login">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h1 className="display-4 text-center">Log In</h1>
              <form onSubmit={this.onSubmitHandler}>
                <div className="form-group">
                  <input
                    type="text"
                    className={classnames("form-control form-control-lg", {
                      "is-invalid": error.username,
                    })}
                    placeholder="Email Address"
                    name="username"
                    onChange={this.onChangeHandler}
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
                    onChange={this.onChangeHandler}
                    value={this.state.password}
                  />
                  {error.password && (
                    <div className="invalid-feedback">
                      <p>{error.password}</p>
                    </div>
                  )}
                </div>
                <input type="submit" className="btn btn-info btn-block mt-4" />
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
    security: state.securityReducers,
    error: state.errorReducers.errors,
  };
};
const mapDispatchToProps = (dispatch) => {
  return {
    authenticateUser: (user) => dispatch(actions.login(user)),
  };
};
export default connect(mapStateToProps, mapDispatchToProps)(Auth);

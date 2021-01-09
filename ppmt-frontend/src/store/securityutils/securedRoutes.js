import React from "react";
import { Redirect, Route } from "react-router-dom";
import { connect } from "react-redux";

const SecuredRoutes = ({ component: Component, security, ...otherProps }) => {
  return (
    <div>
      <Route
        {...otherProps}
        render={(props) =>
          security.validToken === true ? (
            <Component {...props} />
          ) : (
            <Redirect to="/login" />
          )
        }
      />
    </div>
  );
};

const mapStateToProps = (state) => {
  return {
    security: state.securityReducers,
  };
};
export default connect(mapStateToProps)(SecuredRoutes);

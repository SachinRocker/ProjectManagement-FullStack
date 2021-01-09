import {
  REGISTER_USER,
  AUTH_USER,
  SET_CURRENT_USER,
  GET_ERRORS,
} from "./ActionTypes";
import axios from "axios";
import jwtDecode from "jwt-decode";

import setJWTToken from "./../securityutils/setJWTToken";

export const setRegisteredUser = (registeredUser) => {
  return {
    type: REGISTER_USER,
    payload: registeredUser,
  };
};
export const setRegistrationError = (error) => {
  return {
    type: GET_ERRORS,
    error: error,
  };
};

export const registerUser = (user, history) => {
  console.log("inside register actions, ", user);
  return (dispatch) => {
    axios
      .post("http://localhost:8080/users/register", user)
      .then((res) => {
        history.push("/login");
        dispatch(setRegisteredUser(res.data));
      })
      .catch((err) => {
        console.log("error::", err.response.data);
        dispatch(setRegistrationError(err.response.data));
      });
  };
};

export const setloggedInUser = (decodedToken) => {
  return {
    type: SET_CURRENT_USER,
    payload: decodedToken,
  };
};
export const setLoginError = (error) => {
  return {
    type: GET_ERRORS,
    error: error,
  };
};
export const login = (loginRequest) => {
  return (dispatch) => {
    //post => Login Request
    axios
      .post("http://localhost:8080/users/login", loginRequest)
      .then((res) => {
        const { token } = res.data;
        //store the token in local storage
        console.log("token::", token);
        localStorage.setItem("jwtToken", token);
        //set our token in error
        setJWTToken(token);
        const decode = jwtDecode(token);
        console.log("decode::", decode);

        dispatch(setloggedInUser(decode));
      })
      .catch((err) => {
        dispatch(setLoginError(err.response.data));
      });
  };
};

export const logout = () => {
  console.log("inside logout");
  localStorage.removeItem("jwtToken");
  setJWTToken(false);
  return (dispatch) => {
    dispatch(setloggedInUser({}));
  };
};

import {SIGNUP_SUCCESS, SIGNUP_FAIL, SIGNIN_SUCCESS, SIGNIN_FAIL, SET_MESSAGE, SIGNOUT} from './types';
import AuthService from '../services/auth.service';

export const signup = (id, password) => (dispatch) => {
  return AuthService.signup(id, password).then(
    (response) => {
      dispatch({
        type: SIGNUP_SUCCESS,
      });

      dispatch({
        type: SET_MESSAGE,
        payload: response.data.message,
      });

      return Promise.resolve();
    },
    (error) => {
      const message =
        (error.response && error.response.data && error.response.data.message) || error.message || error.toString();

      dispatch({
        type: SIGNUP_FAIL,
      });

      dispatch({
        type: SET_MESSAGE,
        payload: message,
      });

      return Promise.reject();
    },
  );
};

export const signin = (id, password) => (dispatch) => {
  return AuthService.signin(id, password).then(
    (data) => {
      dispatch({
        type: SIGNIN_SUCCESS,
        payload: {member: data},
      });

      return Promise.resolve();
    },
    (error) => {
      const message =
        (error.response && error.response.data && error.response.data.message) || error.message || error.toString();

      dispatch({
        type: SIGNIN_FAIL,
      });

      dispatch({
        type: SET_MESSAGE,
        payload: message,
      });

      return Promise.reject();
    },
  );
};

export const signout = () => (dispatch) => {
  AuthService.signout();

  dispatch({
    type: SIGNOUT,
  });
};

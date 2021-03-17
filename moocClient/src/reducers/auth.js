import {
    SIGNUP_SUCCESS,
    SIGNUP_FAIL,
    SIGNIN_SUCCESS,
    SIGNIN_FAIL,
    SIGNOUT,
  } from "../actions/types";
  
  const member = JSON.parse(localStorage.getItem("mooc-member"));
  
  const initialState = member
    ? { isLoggedIn: true, member: member }
    : { isLoggedIn: false, member: null };
  
  export default function (state = initialState, action) {
    const { type, payload } = action;
  
    switch (type) {
      case SIGNUP_SUCCESS:
        return {
          ...state,
          isLoggedIn: false,
        };
      case SIGNUP_FAIL:
        return {
          ...state,
          isLoggedIn: false,
        };
      case SIGNIN_SUCCESS:
        return {
          ...state,
          isLoggedIn: true,
          member: payload.member,
        };
      case SIGNIN_FAIL:
        return {
          ...state,
          isLoggedIn: false,
          member: null,
        };
      case SIGNOUT:
        return {
          ...state,
          isLoggedIn: false,
          member: null,
        };
      default:
        return state;
    }
  }
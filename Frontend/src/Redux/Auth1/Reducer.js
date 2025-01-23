import {
    SignUp,
    SignIn,
    Req_User
  } from "./ActionType";
  
  const initialState = {
    signin: null,
    signup: null

  };
  
  export const authReducer = (state = initialState, { type, payload }) => {
    if (type === SignUp) {
      return { ...state, signup: payload };
    } else if (type === SignIn) {
      return { ...state, signin: payload };
    } else if (type === Req_User) {
      return { ...state, reqUser: payload };
    }
    else {
      return state;
    }
  };
  
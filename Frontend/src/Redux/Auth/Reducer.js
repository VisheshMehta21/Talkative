import {
    REGISTER
  } from "./ActionType";
  
  const initialState = {
    signin: null,
    signup: null

  };
  
  export const authReducer = (state = initialState, { type, payload }) => {
    if (type === REGISTER) {
      return { ...state, signup: payload };
    } else if (type === LOGIN_USER) {
      return { ...state, signin: payload };
    }
    else {
      return state;
    }
  };
  
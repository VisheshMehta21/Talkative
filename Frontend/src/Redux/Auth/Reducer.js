import {
  REGISTER,
  LOGIN_USER,
  REQ_USER,
  SEARCH_USER,
  UPDATE_USER,
  LOGOUT_USER,
  AUTH_ERROR
} from "./ActionType";

const initialState = {
  signin: null,
  signup: null,
  reqUser: null,
  searchUser: [],
  updatedUser: null,
  error: null,  // General error state
};

export const authReducer = (state = initialState, { type, payload }) => {
  switch (type) {
      case REGISTER:
          return { ...state, signup: payload, error: null };
      case LOGIN_USER:
          return { ...state, signin: payload, error: null };
      case REQ_USER:
          return { ...state, reqUser: payload, error: null };
      case SEARCH_USER:
          return { ...state, searchUser: payload, error: null };
      case UPDATE_USER:
          return { ...state, updatedUser: payload, error: null };
      case LOGOUT_USER:
          return { ...state, signin: null, signup: null, reqUser: null, error: null };
      case AUTH_ERROR:
          return { ...state, error: payload }; // Stores any authentication-related error
      default:
          return state;
  }
};

import {
  REGISTER,
  LOGIN_USER,
  REQ_USER,
  SEARCH_USER,
  UPDATE_USER,
  UPDATE_PROFILE_PIC,
  LOGOUT_USER,
  AUTH_ERROR,
  RESET_AUTH_ERROR
} from './ActionType';

const initialState = {
  user: null,
  searchedUsers: [],
  error: null,
};

const authReducer = (state = initialState, action) => {
  switch (action.type) {
      case REGISTER:
          return { ...state, user: action.payload, error: null };
      case LOGIN_USER:
          return { ...state, user: action.payload, error: null };
      case REQ_USER:
          return { ...state, user: action.payload };
      case SEARCH_USER:
          return { ...state, searchedUsers: action.payload };
      case UPDATE_USER:
          return { ...state, user: action.payload };
      case UPDATE_PROFILE_PIC:
          return { 
              ...state, 
              user: { ...state.user, profilePicUrl: action.payload.profilePicUrl } 
          };
      case LOGOUT_USER:
          return { ...state, user: null };
      case AUTH_ERROR:
          return { ...state, error: action.payload };
      case RESET_AUTH_ERROR:
          return { ...state, error: null };
      default:
          return state;
  }
};

export default authReducer;

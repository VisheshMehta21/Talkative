import { BASE_API_URL } from '../../Config/Config.js';

import {
    SignUp,
    SignIn, 
    Req_User
  } from './ActionType';

  export const signup = (data) => async (dispatch) => {
    try {
      const res = await fetch(`${BASE_API_URL}/auth/signup`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      });
  
      const resData = await res.json();
      if (resData.jwt) localStorage.setItem('token', resData.jwt);
      dispatch({ type: SignUp, payload: resData });
    } catch (error) {
      console.error('Registration error:', error);
    }
  };


  export const signin = (data) => async (dispatch) => {
    try {
      const res = await fetch(`${BASE_API_URL}/auth/signin`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      });

      const resData = await res.json();
      if (resData.jwt) localStorage.setItem('token', resData.jwt);
      dispatch({ type: SignIn, payload: resData });
    } catch (error) {
      console.error('Login error:', error);
    }
  };

  export const currentUser = (token) => async (dispatch) => {
    try {
      const res = await fetch(`${BASE_API_URL}/api/users/profile`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
        },
      });
  
      const resData = await res.json();
     console.log("current chat data",resData);
      dispatch({ type: Req_User, payload: resData });
    } catch (error) {
      console.error('Fetch current user error:', error);
    }
  };
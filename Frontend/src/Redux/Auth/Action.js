import { BASE_API_URL } from '../../Config/Config.js';

import {
    REGISTER
  } from './ActionType';

  export const register = (data) => async (dispatch) => {
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
      dispatch({ type: REGISTER, payload: resData });
    } catch (error) {
      console.error('Registration error:', error);
    }
  };
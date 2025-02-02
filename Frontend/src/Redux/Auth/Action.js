import { BASE_API_URL } from '../../Config/Config.js';
import {
  REGISTER,
  LOGIN_USER,
  REQ_USER,
  SEARCH_USER,
  UPDATE_USER,
  LOGOUT_USER,
  AUTH_ERROR,
  RESET_AUTH_ERROR
} from './ActionType';

export const signup = (data) => async (dispatch) => {
  try {
    const res = await fetch(`${BASE_API_URL}/auth/signup`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });

    const resData = await res.json();

    if (res.status === 201) {
      // Successful signup
      dispatch({ type: REGISTER, payload: resData });
    } else if (res.status === 409) {
      // Email already exists
      dispatch({ type: AUTH_ERROR, payload: resData.message });
    } else {
      // Generic error
      dispatch({ type: AUTH_ERROR, payload: "Signup failed. Please try again." });
    }
  } catch (error) {
    console.error('Registration error:', error);
    dispatch({ type: AUTH_ERROR, payload: "Signup failed. Please try again." });
  }
};

export const loginUser = (data) => async (dispatch) => {
  try {
    const res = await fetch(`${BASE_API_URL}/auth/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });

    if (!res.ok) {
      if (res.status === 500 || res.status === 0) {
        throw new Error("Network error. Please try again later.");
      }
      
      const errorData = await res.json();
      throw new Error(errorData.message || "Incorrect email or password. Please sign up if you haven’t created an account.");
    }

    const resData = await res.json();
    if (resData.jwtToken) {
      localStorage.setItem('token', resData.jwtToken);
    }
    dispatch({ type: LOGIN_USER, payload: resData });

  } catch (error) {
    console.error('Login error:', error.message);
    dispatch({ type: AUTH_ERROR, payload: error.message });
  }
};

export const currentUser = (token) => async (dispatch) => {
  try {
    const res = await fetch(`${BASE_API_URL}/users/profile`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`,
      },
    });

    if (res.status === 404) {
      throw new Error('User not found. Please log in again.');
    }

    if (res.status === 401) {
      throw new Error('Unauthorized. Please log in to access your profile.');
    }

    if (!res.ok) {
      throw new Error('Failed to fetch user profile. Please try again.');
    }

    const resData = await res.json();
    dispatch({ type: REQ_USER, payload: resData });
  } catch (error) {
    console.error('Fetch current user error:', error);
    dispatch({ type: AUTH_ERROR, payload: error.message });
  }
};


export const searchUser = (data) => async (dispatch) => {
  try {
    const res = await fetch(
      `${BASE_API_URL}/users/search?query=${data.keyword}`,
      {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${data.token}`,
        },
      }
    );

    if (!res.ok) {
      throw new Error('Search failed. Please try again.');
    }

    const resData = await res.json();
    // console.log(resData);
    dispatch({ type: SEARCH_USER, payload: resData });
  } catch (error) {
    console.error('Search user error:', error);
    dispatch({ type: AUTH_ERROR, payload: error.message });
  }
};

export const updateUser = (data) => async (dispatch) => {
  try {
    const res = await fetch(
      `${BASE_API_URL}/users/update`,
      {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${data.token}`,
        },
        body: JSON.stringify(data.data),
      }
    );

    if (!res.ok) {
      throw new Error('Profile update failed. Please try again.');
    }

    const resData = await res.json();
    dispatch({ type: UPDATE_USER, payload: resData });
    console.log("Profile update successful for ", resData.firstName);
  } catch (error) {
    console.error('Update user error:', error);
    dispatch({ type: AUTH_ERROR, payload: error.message });
  }
};

export const logoutUser = () => async (dispatch) => {
  try {
    localStorage.removeItem('token');
    dispatch({ type: LOGOUT_USER, payload: null });
    dispatch({ type: REQ_USER, payload: null });
    console.log('User logged out successfully...');
  } catch (error) {
    console.error('Logout error:', error);
    dispatch({ type: AUTH_ERROR, payload: 'Logout failed. Please try again.' });
  }
};

export const resetAuthError = () => ({
  type: RESET_AUTH_ERROR
});

export const updateUserProfilePic = (data) => async (dispatch) => {
  try {
    const formData = new FormData();
    formData.append("profilePicture", data.file);
    
    const res = await fetch(`${BASE_API_URL}/users/update-profile-pic?email=${encodeURIComponent(data.email)}`, {
      method: 'PUT',
      headers: {
        Authorization: `Bearer ${data.token}`,
      },
      body: formData,
    });

    if (!res.ok) {
      throw new Error('Profile picture update failed. Please try again.');
    }

    const resData = await res.json();
    dispatch({ type: UPDATE_USER, payload: resData });
    console.log("Profile picture updated successfully at location ", resData.profileUrl);
  } catch (error) {
    console.error('Profile picture update error:', error);
    dispatch({ type: AUTH_ERROR, payload: error.message });
  }
};
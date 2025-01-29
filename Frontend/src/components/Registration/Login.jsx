import { Alert, Button, Snackbar } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { CiUser } from "react-icons/ci";
import { RiLockPasswordLine } from "react-icons/ri";
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { currentUser, loginUser } from '../../Redux/Auth/Action';
import { authReducer } from '../../Redux/Auth/Reducer';

function Login() {

    const [error, setError] = useState(''); // useNavigate hook for navigation

    const [openSnackBar, setOpenSnackBar] = useState(false);
    const [inputData, setInputData] = useState({ email: "", password: "" });
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const token = localStorage.getItem("token");
    const {auth} = useSelector(store=>store);

    useEffect(() => {

      if (token) {

          dispatch(currentUser(token));
      }
    }, [token, auth.reqUser, dispatch]);

    const handleChange = (e) => {
      const {name, value} = e.target;
      setInputData((values)=>({...values,[name]:value }));
    }

    const handleSubmit = (e) => {

      e.preventDefault();
      dispatch(loginUser(inputData));

    };

    useEffect(() => {
      console.log("UseEffect 1 : Signin");
      if (token) {
          console.log("dispatching currentuser with token :: "+token);
          dispatch(currentUser(token));
      }
  }, [token]);


  useEffect(() => { 

      console.log("Auth as JSON:", JSON.stringify(auth, null, 2));

      console.log("UseEffect 2 : Signin");


      if (authReducer.reqUser?.fullName) {
           
          console.log("navigating on chat page :: " + auth);
          setOpenSnackBar(true);
          navigate("/");
      }

      console.log("we dont have authuser :: " +auth);
  }, [auth.reqUser]);


    return (
        <div>
          <form onSubmit={handleSubmit}> 
            <div className="flex items-center space-x-1 bg-gray-200 rounded-lg p-2">
              <CiUser className="text-xl" />
              <input
                className="bg-transparent w-full outline-none"
                type="text"
                placeholder="email"
                required
                name="email"
                onChange={handleChange} 
                value={inputData.email} 
              />
            </div>
            <div className="my-4 flex items-center space-x-1 bg-gray-200 rounded-lg p-2">
              <RiLockPasswordLine className="text-xl" />
              <input
                className="bg-transparent w-full outline-none form-control"
                type="password"
                placeholder="password"
                required
                name="password"
                onChange={handleChange} 
                value={inputData.password} 
              />
            </div>
            {error && <p className="text-danger">{error}</p>} 
            <button type="submit" className="bg-black text-white rounded-lg w-full p-2 mb-4">
              Login
            </button>
          </form>
        </div>
    );
} 

export default Login; 




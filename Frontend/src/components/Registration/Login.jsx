import { Alert, Button, Snackbar } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { CiUser } from "react-icons/ci";
import { RiLockPasswordLine } from "react-icons/ri";
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { currentUser, loginUser } from '../../Redux/Auth/Action';
import { authReducer } from '../../Redux/Auth/Reducer';

function Login() {

    // const [error, setError] = useState(''); 

    const [openSnackBar, setOpenSnackBar] = useState(false);
    const [inputData, setInputData] = useState({ email: "", password: "" });
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const token = localStorage.getItem("token");
    const {auth} = useSelector(store=>store);
    const { error } = useSelector(store => store.auth);  // Access error from authReducer
    const [loginError, setLoginError] = useState(error);



    useEffect(() => {

      if (token) {

          dispatch(currentUser(token));
      }
    }, [token, auth.reqUser, dispatch]);

    const handleInputDataChange = (e) => {
      const {name, value} = e.target;
      setInputData((values)=>({...values,[name]:value }));

      if (loginError) setLoginError(null);
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


      if (authReducer.reqUser?.email) {
           
          console.log("navigating on chat page :: " + auth);
          setOpenSnackBar(true);
          navigate("/");
      }

      console.log("we dont have authuser :: " +auth);
  }, [auth.reqUser]);

  useEffect(() => {
    setLoginError(error);
  }, [error]);



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
                onChange={handleInputDataChange} 
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
                onChange={handleInputDataChange} 
                value={inputData.password} 
              />
            </div>
            
            {loginError && (<Alert severity="error" className="mb-2">{loginError}</Alert>)}

            <button type="submit" className="bg-black text-white rounded-lg w-full p-2 mb-4">
              Login
            </button>
          </form>
        </div>
    );
} 

export default Login; 




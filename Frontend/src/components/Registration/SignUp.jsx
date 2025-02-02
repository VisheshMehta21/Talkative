import { Alert, Snackbar } from '@mui/material';
import { CiUser } from "react-icons/ci"
import { RiLockPasswordLine } from "react-icons/ri";
import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { resetAuthError, signup } from '../../Redux/Auth/Action';

const SignUp = () => {
  const [openSnackBar, setOpenSnackBar] = useState(false);
  const [inputData, setInputData] = useState({ firstName: "", lastName: "", email: "", password: "", confirmPassword: "" });
  const [passwordMismatch, setPasswordMismatch] = useState(false);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { error, user } = useSelector(store => store.auth);


  const handleSignup = (e) => {
    e.preventDefault();

    if (inputData.password !== inputData.confirmPassword) {
      setPasswordMismatch(true);
      return; 
    }

    setPasswordMismatch(false); 
    dispatch(signup(inputData)); 
    setOpenSnackBar(true); 
  };

  const handleInputDataChange = (e) => {
    setPasswordMismatch(false);
    const { name, value } = e.target;
    setInputData((values) => ({ ...values, [name]: value }));

    if (error) {
      dispatch(resetAuthError());
    }
  };

  const handleSnackBarClose = () => {
    setOpenSnackBar(false);
  };

  useEffect(() => {
    console.log(user?.email);
    if (user?.email) {
      setOpenSnackBar(true);
      setTimeout(() => {
        navigate("/login");
      }, 6000);
    }
  }, [user, navigate]);

  return (
    <div>
        
       <form onSubmit={handleSignup}>
 
         <div className="flex items-center space-x-1 bg-gray-200 rounded-lg p-2">
           <CiUser className="text-xl" />
           <input
             className="bg-transparent w-full outline-none"
             type="text"
             placeholder="First Name"
             name="firstName"
             required
             onChange={handleInputDataChange}
             value={inputData.firstName}
           />
         </div>
 
         <div className="my-4 flex items-center space-x-1 bg-gray-200 rounded-lg p-2">
           <CiUser className="text-xl" />
           <input
             className="bg-transparent w-full outline-none"
             type="text"
             placeholder="Last Name"
             name="lastName"
             required
             value={inputData.lastName} 
             onChange={handleInputDataChange}
           />
         </div>
 
         <div className="my-4 flex items-center space-x-1 bg-gray-200 rounded-lg p-2">
           <CiUser className="text-xl" />
           <input
             className="bg-transparent w-full outline-none"
             type="text"
             placeholder="Email"
             name="email"
             required
             value={inputData.email} 
             onChange={handleInputDataChange}
           />
         </div>
 
         <div className="my-4 flex items-center space-x-1 bg-gray-200 rounded-lg p-2">
           <RiLockPasswordLine className="text-xl" />
           <input
             className="bg-transparent w-full outline-none"
             type="password"
             placeholder="Password"
             name="password"
             required
             value={inputData.password} 
             onChange={handleInputDataChange}
           />
         </div>

         <div className="my-4 flex items-center space-x-1 bg-gray-200 rounded-lg p-2">
           <RiLockPasswordLine className="text-xl" />
           <input
             className="bg-transparent w-full outline-none"
             type="password"
             placeholder="Confirm Password"
             name="confirmPassword"
             required
             value={inputData.confirmPassword} 
             onChange={handleInputDataChange}
           />
         </div>
          {passwordMismatch && <p className="text-danger text-red-700 pb-2"> Passwords do not match. </p> }

          {/* Render error message if exists */}
          {error && <Alert severity="error" className="mb-2">{error}</Alert>} 
         <button className="bg-black text-white rounded-lg w-full p-2 mb-4">Sign up</button>
       </form>

       <Snackbar open={openSnackBar} autoHideDuration={6000} onClose={handleSnackBarClose}>
        <Alert onClose={handleSnackBarClose} severity="success" sx={{ width: '100%' }}>
          Signup successful! Please login to continue.
        </Alert>
      </Snackbar>
     </div>
   );
 }

export default SignUp; 
import { Alert, Button, Snackbar } from '@mui/material';
import { CiUser } from "react-icons/ci"
import { RiLockPasswordLine } from "react-icons/ri";
import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { currentUser, signup } from '../../Redux/Auth/Action';

const SignUp = () => {
  const [openSnackBar, setOpenSnackBar] = useState(false);
  const [inputData, setInputData] = useState({ firstName: "", lastName: "", email: "", password: "", confirmPassword: "" });
  const [passwordMismatch, setPasswordMismatch] = useState(false);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { auth } = useSelector(store => store);
  const token = localStorage.getItem("token");
  const [error, setError] = useState('');

  useEffect(() => {
    if (token && !auth.reqUser) {
      dispatch(currentUser(token));
    }
  }, [token, auth.reqUser, dispatch]);

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
  };

  const handleSnackBarClose = () => {
    setOpenSnackBar(false);
  };

  useEffect(() => {
    if (auth.reqUser?.fullName) {
      navigate("/home");
    }
  }, [auth, navigate]);





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
          {/* error && <p className="text-danger">{error}</p> */} 
         <button className="bg-black text-white rounded-lg w-full p-2 mb-4">Sign up</button>
       </form>
     </div>
   );
 }


  /* return (
    <div>
      <div className='flex flex-col justify-center min-h-screen items-center'>
        <div className='w-[30%] p-10 shadow-md bg-white'>
          <form onSubmit={handleSubmit} className='space-y-5'>
            <div>
              <p className='mb-2'>Full Name</p>
              <input
                type="text"
                placeholder='Enter your full name'
                name='fullName'
                onChange={handleChange}
                value={inputData.fullName}
                className='py-2 outline outline-blue-500 w-full rounded-md border'
              />
            </div>
            <div>
              <p className='mb-2'>Email</p>
              <input
                type="text"
                placeholder='Enter your email'
                name='email'
                onChange={handleChange}
                value={inputData.email}
                className='py-2 outline outline-blue-500 w-full rounded-md border'
              />
            </div>
            <div>
              <p className='mb-2'>Password</p>
              <input
                type="password"
                placeholder='Enter your password'
                name='password'
                onChange={handleChange}
                value={inputData.password}
                className='py-2 outline outline-blue-600 w-full rounded-md border'
              />
            </div>
            <div>
              <p className='mb-2'>Confirm Password</p>
              <input
                type="password"
                placeholder='Confirm Password'
                name='confirmPassword'
                onChange={handleChange}
                value={inputData.confirmPassword}
                className='py-2 outline outline-blue-600 w-full rounded-md border'
              />
              {passwordMismatch && <p className="text-red-500">Passwords do not match.</p>}
            </div>

            <div>
              <Button className='w-full' variant='contained' type='submit'>
                SignUp
              </Button>
            </div>
          </form>

          <div className='flex space-x-3 items-center mt-5'>
            <p className='m-0'> already have account </p>
            <Button className='' variant='text' onClick={() => navigate("/login")}>
              Login
            </Button>
          </div>
        </div>
      </div>

      <Snackbar
        open={openSnackBar}
        autoHideDuration={6000}
        onClose={handleSnackBarClose}
      >
        <Alert onClose={handleSnackBarClose} severity="success" sx={{ width: '100%' }}>
          Your Account Created Successfully !
        </Alert>
      </Snackbar>
    </div>
  );
}; */

export default SignUp; 
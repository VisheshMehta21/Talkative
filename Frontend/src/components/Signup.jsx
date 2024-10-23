import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { CiUser } from "react-icons/ci";
import { RiLockPasswordLine } from "react-icons/ri";

function Signup() {

  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  //const [confirmPassword, setConfirmPassword] = useState('');
  const navigate = useNavigate(); // Get the history object for redirection

  const handleSignup = async (e) => {

    e.preventDefault();
      try {
          

         /* if (password !== confirmPassword) {
              throw new Error("Passwords do not match");
          }*/ 

          const response = await axios.post('http://localhost:8000/api/v1/auth/signup', {
              firstName: firstName,
              lastName: lastName,
              email: email,
              password: password
          });
          // Handle successful signup
          console.log(response.data);
          navigate('/home');
      } catch (error) {
          // Handle signup error
          console.error('Signup failed:', error.response ? error.response.data : error.message);
          setError(error.response ? error.response.data : error.message);
      }
  };
  
  return (
   <div>
       
      <form>

        <div className="flex items-center space-x-1 bg-gray-200 rounded-lg p-2">
          <CiUser className="text-xl" />
          <input
            className="bg-transparent w-full outline-none"
            type="text"
            placeholder="First Name"
            required
            value={firstName} 
            onChange={(e) => setFirstName(e.target.value)}
          />
        </div>

        <div className="my-4 flex items-center space-x-1 bg-gray-200 rounded-lg p-2">
          <CiUser className="text-xl" />
          <input
            className="bg-transparent w-full outline-none"
            type="text"
            placeholder="Last Name"
            required
            value={lastName} 
            onChange={(e) => setLastName(e.target.value)}
          />
        </div>

        <div className="my-4 flex items-center space-x-1 bg-gray-200 rounded-lg p-2">
          <CiUser className="text-xl" />
          <input
            className="bg-transparent w-full outline-none"
            type="text"
            placeholder="email"
            required
            value={email} 
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>

        <div className="my-4 flex items-center space-x-1 bg-gray-200 rounded-lg p-2">
          <RiLockPasswordLine className="text-xl" />
          <input
            className="bg-transparent w-full outline-none"
            type="password"
            placeholder="password"
            required
            value={password} 
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
         {/* Render error message if exists */}
         {error && <p className="text-danger">{error}</p>}
        <button className="bg-black text-white rounded-lg w-full p-2 mb-4" onClick={handleSignup}>Sign up</button>
      </form>
    </div>
  );
}

export default Signup;

import React from 'react'
import { CiUser } from "react-icons/ci";
import { RiLockPasswordLine } from "react-icons/ri";
import {  useState } from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';

function Login() {
  
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {

      e.preventDefault();
        try {

            const response = await axios.post('http://localhost:8080/api/v1/auth/login', { email: email, password });
            console.log('Login successful:', response.data);
            navigate('/home');
        } catch (error) {
            console.error('Login failed:', error.response ? error.response.data : error.message);
            setError('Invalid username or password.');
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
                placeholder="email" 
                required
                value={email} 
                onChange={(e) => setEmail(e.target.value)} 
              />
            </div>
            <div className="my-4 flex items-center space-x-1 bg-gray-200 rounded-lg p-2">
              <RiLockPasswordLine className="text-xl" />
              <input
                className="bg-transparent w-full outline-none form-control"
                type="password"
                placeholder="password"
                required
                value={password} 
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
            {error && <p className="text-danger">{error}</p>} {/* Render error message if exists */}
            <button className="bg-black text-white rounded-lg w-full p-2 mb-4" onClick={handleLogin} >Login</button>
          </form>
        </div>
      );
}

export default Login;

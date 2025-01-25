import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Login from "./Login";
import SignUp from "./SignUp";
import welcome from '../assets/Welcome.jpg';

export const Registration = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const [isLogin, setIsLogin] = useState(false);

  // Check the route to toggle between login and signup
  useEffect(() => {
    if (location.pathname === "/login") {
      setIsLogin(true);
    } else {
      setIsLogin(false);
    }
  }, [location]);

  const toggleAuthPage = () => {
    if (isLogin) {
      navigate("/signup"); // Navigate to the signup page
    } else {
      navigate("/login"); // Navigate to the login page
    }
    setIsLogin(!isLogin); // Update the state
  };

  return (
    <div className="bg-sky-50">
      <div className="px-4 max-w-7xl mx-auto lg:space-x-20 flex justify-center items-center h-screen bg-slate-400">
       
        <div className="w-1/2 hidden lg:block">
          <img className="rounded-3xl" src={welcome} alt="" />
        </div> 

        <div className="lg:w-[50%]">
          <div className="text-center py-4">
            <h1 className="text-7xl font-semibold">SmartChat</h1>
            <p className="p-2">Chat in your native Language.</p>
          </div>

          {isLogin ? <Login /> : <SignUp />}

          <div className="pb-4 text-sm flex items-center justify-between">
            <p>{isLogin ? "Don't have an account?" : 'Already have an account?'}</p>
            <button 
              onClick={toggleAuthPage} 
              className="font-semibold underline"
            >
              {isLogin ? 'Sign up' : 'Login'}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}; 

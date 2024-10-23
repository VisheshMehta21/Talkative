import React, { useState } from "react";
import { Login } from "./Login";
import { Signup } from "./Signup";
import welcome from '../assets/Welcome.jpg'
import { FaFacebookF } from "react-icons/fa";
import { FaGoogle } from "react-icons/fa";
export const Registration = () => {
  const [isLogin, setIsLogin] = useState(false);
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

      {isLogin ? <Login /> : <Signup />}
        <div className="pb-4 text-sm flex items-center justify-between">
            <p>{isLogin ? "Don't have an account?" : 'Already have an account?'}</p>
            <button onClick={() => setIsLogin(!isLogin)} className="font-semibold underline">{isLogin ? 'Sign up' : 'Login'}</button>
        </div>
      
      
     </div>
    </div>
    </div>
  );
};

/*

<div className="flex items-center space-x-4">
          <hr className="w-full" />
          <p className="shrink-0">Login with Others</p>
          <hr className="w-full" />
        </div>
        <div className="my-4 flex items-center justify-center border border-black rounded-lg space-x-1 p-2">
          <FaGoogle />
          <p>Sign in with Google</p>
        </div>
        <div className="flex items-center justify-center border border-black rounded-lg space-x-1 p-2">
          <FaFacebookF />
          <p>Sign in with Facebook</p>
        </div> */
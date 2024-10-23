import React from "react";
import { CiUser } from "react-icons/ci";
import { RiLockPasswordLine } from "react-icons/ri";
export const Signup = () => {
  return (
   <div>
       
      <form>

        <div className="flex items-center space-x-1 bg-gray-200 rounded-lg p-2">
          <CiUser className="text-xl" />
          <input
            className="bg-transparent w-full outline-none"
            type="text"
            placeholder="First Name"
          />
        </div>

        <div className="my-4 flex items-center space-x-1 bg-gray-200 rounded-lg p-2">
          <CiUser className="text-xl" />
          <input
            className="bg-transparent w-full outline-none"
            type="text"
            placeholder="Last Name"
          />
        </div>

        <div className="my-4 flex items-center space-x-1 bg-gray-200 rounded-lg p-2">
          <CiUser className="text-xl" />
          <input
            className="bg-transparent w-full outline-none"
            type="text"
            placeholder="email"
          />
        </div>

        <div className="my-4 flex items-center space-x-1 bg-gray-200 rounded-lg p-2">
          <RiLockPasswordLine className="text-xl" />
          <input
            className="bg-transparent w-full outline-none"
            type="password"
            placeholder="password"
          />
        </div>
        <button className="bg-black text-white rounded-lg w-full p-2 mb-4">Sign up</button>
      </form>
    </div>
  );
};

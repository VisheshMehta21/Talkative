import { Alert } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { CiUser } from "react-icons/ci";
import { RiLockPasswordLine } from "react-icons/ri";
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { currentUser, loginUser, resetAuthError } from '../../Redux/Auth/Action';

function Login() {
    const [inputData, setInputData] = useState({ email: "", password: "" });
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const token = localStorage.getItem("token");

    // Get auth state and error from Redux
    const { error, user } = useSelector(store => store.auth);

    useEffect(() => {
        if (token) {
            dispatch(currentUser(token));
        }
    }, [token, dispatch]);

    useEffect(() => {
        if (user?.firstName) {
            navigate("/home");
        }
    }, [user, navigate]);

    const handleInputDataChange = (e) => {
        const { name, value } = e.target;
        setInputData(prev => ({ ...prev, [name]: value }));

        // Clear error when user starts typing
        if (error) {
            dispatch(resetAuthError());
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        dispatch(loginUser(inputData));
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <div className="flex items-center space-x-1 bg-gray-200 rounded-lg p-2">
                    <CiUser className="text-xl" />
                    <input
                        className="bg-transparent w-full outline-none"
                        type="text"
                        placeholder="Email"
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
                        placeholder="Password"
                        required
                        name="password"
                        onChange={handleInputDataChange}
                        value={inputData.password}
                    />
                </div>

                {error && <Alert severity="error" className="mb-2">{error}</Alert>}

                <button type="submit" className="bg-black text-white rounded-lg w-full p-2 mb-4">
                    Login
                </button>
            </form>
        </div>
    );
}

export default Login;

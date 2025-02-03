import React, { useState, useEffect } from 'react';
import { BsArrowLeft, BsCheck2, BsPencil } from 'react-icons/bs';
import { useDispatch, useSelector } from 'react-redux';
import { updateUser, updateUserProfilePic } from '../../Redux/Auth/Action';

const Profile = ({ HandleCloseOpenProfile }) => {
    const [editFirstName, setEditFirstName] = useState(false);
    const [editLastName, setEditLastName] = useState(false);
    const [firstName, setFirstName] = useState(null);
    const [lastName, setLastName] = useState(null);
    const [tempPicture, setTempPicture] = useState(null);
    
    const  auth  = useSelector((store) => store.auth);
    const dispatch = useDispatch();

    // Set initial values from the user's data
    useEffect(() => {
        setFirstName(auth.user?.firstName);
        setLastName(auth.user?.lastName);
    }, [auth.user]);

    const handleEditFirstName = () => setEditFirstName(true);
    const handleEditLastName = () => setEditLastName(true);

    const handleCheckFirstName = () => {
        if (firstName !== auth.user?.firstName) {
            const data = {
                id: auth.user.userId,
                token: localStorage.getItem("token"),
                data: { firstName, lastName: auth.user.lastName },
            };
            dispatch(updateUser(data));
        }
        setEditFirstName(false);
    };

    const handleCheckLastName = () => {
        if (lastName !== auth.user?.lastName) {
            const data = {
                id: auth.user.userId,
                token: localStorage.getItem("token"),
                data: { firstName: auth.user.firstName, lastName },
            };
            dispatch(updateUser(data));
        }
        setEditLastName(false);
    };

    const handleProfilePicUpload = (file) => {
        setTempPicture(URL.createObjectURL(file));
        const data = {
            file: file,
            email: auth.user.email,
            token: localStorage.getItem("token"),
        };
        dispatch(updateUserProfilePic(data));
    };

    return (
        <div className='w-full h-full'>
            <div className='flex items-center -space-x-10 bg-cyan-600 text-white pt-16 px-10 pb-5 justify-between'>
                <BsArrowLeft className='cursor-pointer text-2xl font-bold' onClick={HandleCloseOpenProfile} />
                <p className='font-semibold cursor-pointer'> Profile </p>
            </div>

            <div className="flex flex-col justify-center items-center my-12">
                <label htmlFor='ImgInput'>
                    <img
                        className='rounded-full w-[15vw] h-[15vw] cursor-pointer'
                        src={tempPicture || auth.user?.profileUrl || "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png"}
                        alt="Profile"
                    />
                </label>
                <input
                    onChange={(e) => handleProfilePicUpload(e.target.files[0])}
                    type="file"
                    id='ImgInput'
                    className='hidden'
                />
            </div>

            <div className='bg-white'>
                <p className='px-3 py-3'></p>

                {!editFirstName ? (
                    <div className='w-full flex justify-between items-center'>
                        <p className='px-3 py-3'>{auth.user?.firstName}</p>
                        <BsPencil onClick={handleEditFirstName} className='cursor-pointer mr-3' />
                    </div>
                ) : (
                    <div className='w-full flex items-center justify-between py-2'>
                        <input
                            onKeyPress={(e) => e.key === "Enter" && handleCheckFirstName()}
                            onChange={(e) => setFirstName(e.target.value)}
                            className='ml-3 w-[80%] outline-none border-b-2 border-orange-700 p-2'
                            type="text"
                            placeholder='Enter your First Name'
                        />
                        <BsCheck2 onClick={handleCheckFirstName} className='cursor-pointer text-2xl mr-3' />
                    </div>
                )}

                {!editLastName ? (
                    <div className='w-full flex justify-between items-center'>
                        <p className='px-3 py-3'>{auth.user?.lastName}</p>
                        <BsPencil onClick={handleEditLastName} className='cursor-pointer mr-3' />
                    </div>
                ) : (
                    <div className='w-full flex items-center justify-between py-2'>
                        <input
                            onKeyPress={(e) => e.key === "Enter" && handleCheckLastName()}
                            onChange={(e) => setLastName(e.target.value)}
                            className='ml-3 w-[80%] outline-none border-b-2 border-orange-700 p-2'
                            type="text"
                            placeholder='Enter your Last Name'
                        />
                        <BsCheck2 onClick={handleCheckLastName} className='cursor-pointer text-2xl mr-3' />
                    </div>
                )}
            </div>
            <div className='px-3 my-0'>
                <p className='py-10'>This name will appear on your messages.</p>
            </div>
        </div>
    );
};

export default Profile;

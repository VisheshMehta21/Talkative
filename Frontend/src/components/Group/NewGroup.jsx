import { Button, CircularProgress } from '@mui/material';
import React, { useState } from 'react';
import { BsArrowLeft, BsCheck2 } from 'react-icons/bs';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { createGroupChat } from '../../Redux/Chat/Action';

const NewGroup = ({ groupMember, setIsGroup }) => {
    const [isImgUploading, setIsImgUploading] = useState(false);
    const [groupName, setGroupName] = useState("");
    const [groupImg, setGroupImg] = useState(null);
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const token = localStorage.getItem("token");

    const HandleNavigate = () => {
        navigate(-1);
    };

    const handleFileChange = (e) => {
        const file = e.target.files[0];
        setGroupImg(file); // Store the selected file in state
    };

    const handleCreateGroup = () => {
        let userIds = [];
    
        for (let user of groupMember) {
            userIds.push(user.userId);
        }
    
        const group = {
            userIds,
            chatName: groupName,
        };
    
        const formData = new FormData();
        formData.append("groupChatReq", JSON.stringify(group));  // Append group data as a string
        if (groupImg) {
            formData.append("chatImage", groupImg);  // Append the image file
        }
    
        const data = {
            formData,
            token,
        };
    
        console.log("req for create grp", group);
        dispatch(createGroupChat(data));  // Dispatch the data including the image file
        setIsGroup(false);
        console.log("grp created");
    
        navigate("/home");
    };
    

    return (
        <div className='w-full h-full'>
            {/* Header */}
            <div className='flex items-center space-x-10 bg-[#4693ff] text-white pt-16 px-10 pb-5'>
                <BsArrowLeft className='cursor-pointer text-2xl font-bold' onClick={HandleNavigate} />
                <p className='text-xl font-semibold'> New Group </p>
            </div>

            {/* Image Upload */}
            <div className='flex flex-col justify-center items-center my-12'>
                <label htmlFor="imgInput" className='relative'>
                    <div className='rounded-full border-2 border-gray-500 overflow-hidden w-40 h-40'>
                        <img className='w-full h-full object-cover'
                            src={groupImg ? URL.createObjectURL(groupImg) : "https://media.istockphoto.com/id/1158561473/vector/three-persons-icon-black-vector.webp?s=2048x2048&w=is&k=20&c=q8IXcz8hnaQ3ICQHTB489rEOZMfvJxMWnZUJfSDEjZQ="} alt="" />
                        {isImgUploading && <CircularProgress className='absolute top-[4rem] left-[4rem]' />}
                    </div>
                    <input type="file" id='imgInput' className='hidden' onChange={handleFileChange} />
                </label>
            </div>

            {/* Group Name Input */}
            <div className='w-full flex items-center py-2 px-5 justify-between'>
                <input
                    className='w-full outline-none border-b-2 border-blue-500 px-2 bg-transparent'
                    placeholder='Grp name'
                    value={groupName}
                    type="text"
                    onChange={(e) => setGroupName(e.target.value)}
                />
            </div>

            {/* Check Button */}
            {groupName &&
                <div className='py-10 bg-slate-200 flex items-center justify-center'>
                    <Button style={{ borderRadius: '50%' }} onClick={handleCreateGroup}>
                        <div className='bg-[#4fc7fa] rounded-full p-4'>
                            <BsCheck2 className='text-white font-bold text-3xl' />
                        </div>
                    </Button>
                </div>
            }
        </div>
    );
};

export default NewGroup;

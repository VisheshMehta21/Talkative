import { BASE_API_URL } from "../../Config/Config"
import { CREATE_CHAT } from "./ActionType";


export const createChat =(chatData) => async (dispatch) =>{
    console.log("chatdata", chatData);
try {
    const res = await fetch(`${BASE_API_URL}/api/chats/single`, {
        method:"POST",
        headers:{
            "Content-Type" : "application/json",
            Authorization:`Bearer ${chatData.token}`
        },
        body:JSON.stringify(chatData.data)
    })

    const data = await res.json();
    console.log("Chat data from server", data);
    dispatch({type:CREATE_CHAT,payload:data});
} catch (error) {
    console.log("Something went wrong in chat Action (CreateChat):: UI Error :: ",error);
}
}

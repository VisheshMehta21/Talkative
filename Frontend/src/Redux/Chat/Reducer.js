import { CREATE_CHAT } from "./ActionType"

const initialValue = {
    chats:[],
    createdGroup:null
}


export const chatReducer=(store=initialValue, {type,payload}) =>{

    if(type===CREATE_CHAT){
        return{ ...store,createdChat:payload }
    }
    else if(type===CREATE_GROUP){
        return{ ...store,createdGroup:payload }
    }
    return store;
}
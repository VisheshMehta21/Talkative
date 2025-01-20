import { CREATE_CHAT } from "./ActionType"

const initialValue = {
    chats:[],
    

}


export const chatReducer=(store=initialValue, {type,payload}) =>{

    if(type===CREATE_CHAT){
        return{ ...store,createdChat:payload }
    }
    return store;
}
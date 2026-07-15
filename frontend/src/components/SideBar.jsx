import {Link} from "react-router-dom"
import './styles/SideBar.css'

import ChatSideBar from "./ChatSideBar";
import Perfil from "./Perfil";
function SideBar({ chats, activeChat, onChatClick }){

    const chatsOrdenados = [...chats].sort((a, b) => {
        if (!a.lastMessageTime && !b.lastMessageTime) return 0;
        
        if (!a.lastMessageTime) return 1;
        
        if (!b.lastMessageTime) return -1;

        return new Date(b.lastMessageTime) - new Date(a.lastMessageTime);
    });


    return (
        <nav className="sideBar">
            <div className="sideBar-container">
                <div className="title-chat-container">
                    <h2 className="title-chat">Chats</h2>
                </div>
                <Perfil/>
                <p className="sidebar-chats-title">Meus chats</p>
                <ul className="sidebar-chats">
                    {
                        chatsOrdenados.map((chat) => {
                            const isActive = activeChat && (activeChat.chatId === chat.chatId)

                            return (
                                <li className={`sidebar-chat-item ${isActive ? "sidebar-chat-active" : ""}`} key={chat.chatId} onClick={() => onChatClick(chat)}>
                                    <ChatSideBar chat={chat} />
                                </li>
                            )
                    })
                    }
                </ul>
               
            </div>
        </nav>
    );

}

export default SideBar
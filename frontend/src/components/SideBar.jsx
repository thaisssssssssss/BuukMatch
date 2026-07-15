import {Link} from "react-router-dom"
import './styles/SideBar.css'

import ChatSideBar from "./ChatSideBar";
import Perfil from "./Perfil";
function SideBar({ chats, onChatClick }){


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
                        chats.map((chat) => (
                            <li className="sidebar-chat-item" key={chat.chatId} onClick={() => onChatClick(chat)}>
                                <ChatSideBar chat={chat} />
                            </li>
                        ))
                    }
                </ul>
               
            </div>
        </nav>
    );

}

export default SideBar
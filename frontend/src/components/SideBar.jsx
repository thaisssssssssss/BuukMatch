import {Link} from "react-router-dom"
import './styles/SideBar.css'
import profile from "../assets/woman.png"
import ChatSideBar from "./ChatSideBar";

function SideBar({ chats, onChatClick }){

    const user_data_save = localStorage.getItem("user_data")
    let user_data = null

    if(user_data_save) {
        user_data = JSON.parse(user_data_save)
    }

    return (
        <nav className="sideBar">
            <div className="sideBar-container">
                <div className="title-chat-container">
                    <h2 className="title-chat">Chats</h2>
                </div>
                <div className="profileContainer"> {/* o profile nao precisa ter lik para nada*/}
                    <Link to = "/" className="links-SideBar"> 
                        <img className="profile-img" src = {profile}  alt = "profile"/>
                    </Link>
                    <div className="profileContents">
                        <p className="name">Hello, { user_data ? user_data.name : "Usuário" }👋</p>
                        <p className="email">{ user_data ? user_data.email : "Email" }</p>
                    </div>
                </div>
                <p className="sidebar-chats-title">Meus chats</p>
                <ul className="sidebar-chats">
                    {
                        chats.map((chat) => (
                            <li className="sidebar-chat-item" onClick={() => onChatClick(chat)}>
                                <ChatSideBar key={chat.id} chat={chat} />
                            </li>
                        ))
                    }
                </ul>
               
            </div>
        </nav>
    );

}

export default SideBar
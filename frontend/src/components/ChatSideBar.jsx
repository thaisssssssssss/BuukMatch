import "./styles/ChatSideBar.css"

import { User, UserRound } from "lucide-react"

function ChatSideBar({ chat }) {
    return (
        <div className="chatsidebar-container" >
            <UserRound className="chatsidebar-icon" size={40}/>
            <div className="chatsidebar-content">
                <h3 className="chatsidebar-content">{ chat.name }</h3>
                <p className="chatsidebar-last-message">{ chat.lastMessage === ''? 'Sem mensagens' : chat.lastMessage }</p>
            </div>
            <div className="chatsidebar-hour-last-message">00h</div>
        </div>
    )

}

export default ChatSideBar
import "./styles/ChatSideBar.css"

import { User, UserRound } from "lucide-react"

function ChatSideBar({ chat }) {
    return (
        <div className="chatsidebar-container" >
            <UserRound className="chatsidebar-icon" size={40}/>
            <div className="chatsidebar-content">
                <h3 className="chatsidebar-content">{ chat.user_dst }</h3>
                <p className="chatsidebar-last-message">{ chat.messages.at(-1).content }</p>
            </div>
            <div className="chatsidebar-hour-last-message">{ chat.messages.at(-1).hour }</div>
        </div>
    )

}

export default ChatSideBar
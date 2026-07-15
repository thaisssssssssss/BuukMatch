import "./styles/Chat.css"
// import Input from "../components/Input"
import logoImg from "../assets/logo.png"
import { Link } from "react-router-dom"
import SideBar from "../components/SideBar"
import NavBarApp from "../components/NavBarApp"
import MainChatRender from "../components/MainChatRender"
import { useState, useEffect } from "react"

import { chatService } from "../services/chat"

function Chat() {

    const [focusChat, setFocusChat] = useState(null)

    const [chats, setChats] = useState([])

    // Buscando chats
    useEffect(() => {
        const buscarChats = async () => {
            const token = localStorage.getItem("token")
            try {
                const resposta_servidor = await chatService.listChatsService(token)
                setChats(resposta_servidor)    
            } catch(erro) {
                console.log(`Houve um erro: ${erro}`)
            }
        }

        buscarChats()
    }, [])

    return (
        <div className="chat-pai">
            <div className="chat-content"> 
                <NavBarApp /> 
                <div className="chat-main-content">
                    <SideBar chats={chats} onChatClick={setFocusChat}/>
                    <MainChatRender chat={focusChat} />
                </div>
            </div>
        </div>
    )
}

export default Chat
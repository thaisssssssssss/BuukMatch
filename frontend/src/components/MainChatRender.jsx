import "./styles/MainChatRender.css"
import Message from "../components/Message"
import Input from "../components/Input"
import { SendHorizonal } from "lucide-react";
import { useState, useEffect } from 'react'
import { chatService } from '../services/chat'

function MainChatRender({ chat }) {
    if (!chat) return null;

    const [messages, setMessages] = useState([])

    useEffect(() => {
        const buscarMensagens = async () => {
            const token = localStorage.getItem("token")
            const chat_id = chat.chatId
            try {
                const resposta_servidor = await chatService.listChatMessages(token, chat_id)
                console.log(resposta_servidor)  
            } catch(erro) {
                console.log(`Houve um erro: ${erro}`)
            }
        }

        buscarMensagens()
    }, [])

    return (
        <div className="mainchat-container">
            <ul className="mainchat-message-list">
                {chat?.messages?.map((message) => (
                    <li key={message.id} className="mainchat-message-container">
                        <Message message={message} isRemetente={!(chat.user_dst === message.owner)} />
                    </li>
                ))}
            </ul>
            
            <div className="mainchat-input-container">
                <input className="mainchat-input" type="text" placeholder="Digite aqui" />
                <button className="mainchat-send-button">
                    <SendHorizonal color="white" className="mainchat-send-icon" />
                </button>
            </div>
        </div>
    )

}

export default MainChatRender
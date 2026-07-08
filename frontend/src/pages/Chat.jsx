import "./styles/Chat.css"
// import Input from "../components/Input"
import logoImg from "../assets/logo.png"
import { Link } from "react-router-dom"
import SideBar from "../components/SideBar"
import NavBarApp from "../components/NavBarApp"
import MainChatRender from "../components/MainChatRender"
import { useState } from "react"

function Chat() {

    const [focusChat, setFocusChat] = useState(null)
    
    const chats = [
        {
            "user_dst": "Ronald",
            "messages": [
                {
                    "id": 1,
                    "owner": "Ronald",
                    "hour": "10h39",
                    "content": "Ola, Marina!",
                },
                {
                    "id": 2,
                    "owner": "Marina",
                    "hour": "10h39",
                    "content": "Ola, Ronald!",
                },
                {
                    "id": 3,
                    "owner": "Ronald",
                    "hour": "10h52",
                    "content": "Gostei do seu livro!",
                },
                {
                    "id": 4,
                    "owner": "Marina",
                    "hour": "10h54",
                    "content": "Também adorei o seu livro, Marina!",
                },
            ]

        },
        {
            "user_dst": "Thais",
            "messages": [
                {
                    "id": 1,
                    "owner": "Ronald",
                    "hour": "10h39",
                    "content": "Ola, Thais!",
                },
                {
                    "id": 2,
                    "owner": "Thais",
                    "hour": "10h39",
                    "content": "Ola, Ronald!",
                },
                {
                    "id": 3,
                    "owner": "Ronald",
                    "hour": "10h39",
                    "content": "Gostei do seu livro!",
                },
                {
                    "id": 4,
                    "owner": "Thais",
                    "hour": "10h53",
                    "content": "Onde podemos nos encontrar?",
                },
            ]

        },
        {
            "user_dst": "Vitor",
            "messages": [
                {
                    "id": 1,
                    "owner": "Ronald",
                    "hour": "10h39",
                    "content": "Ola, Vitor!",
                },
                {
                    "id": 2,
                    "owner": "Vitor",
                    "hour": "10h39",
                    "content": "Ola, Ronald!",
                },
                {
                    "id": 3,
                    "owner": "Ronald",
                    "hour": "10h39",
                    "content": "Gostei do seu livro!",
                },
                {
                    "id": 4,
                    "owner": "Vitor",
                    "hour": "10h52",
                    "content": "Qual sua opinião sobre o livro?",
                },
            ]

        }
    ]

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
import "./styles/Chat.css"
// import Input from "../components/Input"
import logoImg from "../assets/logo.png"
import { Link } from "react-router-dom"
import { useState } from "react"
import SideBar from "../components/SideBar"
import NavBar from "../components/NavBar"

function Chat() {
    

    return (
        <div className="chat-pai">
            <div className="chat-content"> 
                <NavBar/> 
                <div className="chat-main-content">
                    <SideBar/>
                </div>
            </div>



        </div>
    )
}

export default Chat
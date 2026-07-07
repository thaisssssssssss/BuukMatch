import logo from "../assets/logo.png"
import { NavLink, Link } from "react-router-dom"
import './styles/NavBarApp.css'
import { useLocation } from "react-router-dom";

import { UserRound } from "lucide-react";

function NavBarApp(){
    const path = useLocation()

    return (
        <nav className="navBar">
            <div className="navBar-container">
                <div className="left-section">
                    <Link to = "/" className="links">
                        <img className="logo" src = {logo}  alt = "Logo"/>
                    </Link>
                </div>
                <ul className="links-list">
                    <li><NavLink className="option-navbar" to = "/feed" end>Feed</NavLink></li>
                    <li><NavLink className="option-navbar" to = "/matches">Matches</NavLink></li>
                    <li><NavLink className="option-navbar" to = "/chat">Chat</NavLink></li>
                    <li><NavLink className="option-navbar" to = "/me">Meu perfil</NavLink></li>
                </ul>
                <div className="profile">
                    <UserRound />
                    <p>Olá, <span>Ronald</span>!</p>
                </div>
            </div>
        </nav>
    );

}

export default NavBarApp
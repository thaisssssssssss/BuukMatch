import {Link} from "react-router-dom"
import './styles/SideBar.css'
import profile from "../assets/woman.png"

function SideBar(){
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
                        <p className="name">Hello, Marina👋</p>
                        <p className="email">marinasorvete@gmail.com</p>
                    </div>
                </div>

                <div className="chatsContainer">
                    <ul>
                        
                    </ul>
                </div>
               
            </div>
        </nav>
    );

}

export default SideBar
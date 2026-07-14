import "./styles/Perfil.css"
import {Link} from "react-router-dom"
import profile from "../assets/woman.png"

function Perfil() {  
    return (
        <div className="profileContainer"> {/* o profile nao precisa ter lik para nada*/}
            <Link to = "/" className="links-SideBar"> 
                <img className="profile-img" src = {profile}  alt = "profile"/>
            </Link>
            <div className="profileContents">
                <p className="name">Hello, Marina👋</p>
                <p className="email">marinasorvete@gmail.com</p>
            </div>
        </div>
    )
}

export default Perfil
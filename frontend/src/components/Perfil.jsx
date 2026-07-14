import "./styles/Perfil.css"
import {Link} from "react-router-dom"
import profile from "../assets/woman.png"

function Perfil() {  
    const user_data_save = localStorage.getItem("user_data")
    let user_data = null

    if(user_data_save) {
        user_data = JSON.parse(user_data_save)
    }
    return (
        <div className="profileContainer"> {/* o profile nao precisa ter lik para nada*/}
                <Link to = "/" className="links-SideBar"> 
                    <img className="profile-img" src = {profile}  alt = "profile"/>
                </Link>
                <div className="profileContents">
                    <p className="name">Hello, { user_data ? user_data.name : "Usuário" }👋</p>
                    <p className="email">{ user_data ? user_data.email : "Email" }</p>
                </div>
        </div>
    )
}

export default Perfil
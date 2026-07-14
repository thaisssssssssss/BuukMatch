import { use } from "react"
import NavBarApp from "../components/NavBarApp"
import "./styles/Profile.css"


function Profile() {
    let user_data = null

    const user_data_save = localStorage.getItem("user_data")
    if(user_data_save) {
        user_data = JSON.parse(user_data_save)
    }

    return (
        <div className="profile-container">
            <NavBarApp />
            <div className="profile-content">
                <img className="profile-foto" src="/books/1984.jpeg" alt="Foto de perfil" />
                <div className="profile-boas-vindas">
                    <h2 className="profile-hello">Caro leitor, {user_data ? user_data.name : "Usuário"}!</h2>
                    <p className="profile-email">{ user_data ? user_data.email : "Email" }</p>
                </div>
                <div className="profile-options">
                    <button>Ola</button>
                </div>
            </div>
        </div>
    )
}

export default Profile
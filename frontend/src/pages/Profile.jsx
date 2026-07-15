import { Link } from "react-router-dom"
import NavBarApp from "../components/NavBarApp"
import { ArrowLeft } from "lucide-react"
import "./styles/Profile.css"


function Profile() {
    let user_data = null

    const user_data_save = localStorage.getItem("user_data")
    if(user_data_save) {
        user_data = JSON.parse(user_data_save)
    }

    const handleLogout = () => {
        localStorage.removeItem("token");
        localStorage.removeItem("user_data");
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
                <ul className="profile-options-list">
                    <li className="profile-option profile-create-post"><Link to="/post">🌟 Criar post</Link></li>
                    <li className="profile-option profile-create-post"><Link>❤️ Meus posts</Link></li>
                    <li className="profile-option profile-logout"><Link to="/" onClick={handleLogout}><ArrowLeft /> Logout</Link></li>
                </ul>
                </div>
        </div>
    )
}

export default Profile
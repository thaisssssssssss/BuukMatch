import { Link } from "react-router-dom"
import NavBarApp from "../components/NavBarApp"
import { ArrowLeft, X } from "lucide-react"
import { useState } from "react"
import { postService } from "../services/post"
import "./styles/Profile.css"


function Profile() {
    let user_data = null

    const [showPopUp, setShowPopUp] = useState(false)
    const [userPosts, setUserPosts] = useState([])

    const user_data_save = localStorage.getItem("user_data")
    const token = localStorage.getItem("token")
    if(user_data_save) {
        user_data = JSON.parse(user_data_save)
    }

    const handleLogout = () => {
        localStorage.removeItem("token");
        localStorage.removeItem("user_data");
    }

    const handleShowPopUp = () => {
        fetchUserPosts()
        setShowPopUp(true)
    }

    const handleClosePopUp = () => {
        setShowPopUp(false)
    }

    const fetchUserPosts = async () => {
        try {
            const response = await postService.listUserPosts(token)
            setUserPosts(response)
            console.log(response)
        }
        catch(e) {
            console.log("Houve um erro", e)
        }
    }



    return (
        <div className="profile-container">
            {showPopUp && (
                <div className='profile-pop-up-container'>
                    <div className='profile-pop-up-content'>
                        <button className="profile-close-button" onClick={handleClosePopUp}><X color="white"/></button>
                        <h2 className="profile-pop-up-title">Meus posts</h2>
                        <ul className="profile-post-list">
                            {
                                userPosts.map((post) => (
                                    <li className="profile-post-card" key={post.id}>
                                        <div className="profile-post-image-container">
                                            <img 
                                                className="profile-post-img" 
                                                src={`data:image/jpeg;base64,${post.photo}`}
                                                alt="Capa do post" 
                                            />
                                        </div>
                                        <div className="profile-post-info">
                                            <h3 className="profile-post-title">{post.book.title}</h3>
                                            <p className="profile-post-description">{post.legend}</p>
                                        </div>
                                    </li>
                                ))
                            }
                        </ul>
                    </div>
                </div>
                )
            }
            <NavBarApp />
            <div className="profile-content">
                <img className="profile-foto" src="/books/1984.jpeg" alt="Foto de perfil" />
                <div className="profile-boas-vindas">
                    <h2 className="profile-hello">Caro leitor, {user_data ? user_data.name : "Usuário"}!</h2>
                    <p className="profile-email">{ user_data ? user_data.email : "Email" }</p>
                </div>
                <ul className="profile-options-list">
                    <li className="profile-option profile-create-post"><Link to="/post">🌟 Criar post</Link></li>
                    <li className="profile-option profile-create-post"><Link onClick={handleShowPopUp}>❤️ Meus posts</Link></li>
                    <li className="profile-option profile-logout"><Link to="/" onClick={handleLogout}><ArrowLeft /> Logout</Link></li>
                </ul>
                </div>
        </div>
    )
}

export default Profile
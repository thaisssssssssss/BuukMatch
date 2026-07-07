import logo from "../assets/logo.png"
import { NavLink, Link } from "react-router-dom"
import './styles/NavBar.css'
// import Cadastro from "../pages/Cadastro";
import SignIn from "../pages/SignIn";
import SobreNos from "../pages/SobreNos";
import { useLocation } from "react-router-dom";

function NavBar(){
    const path = useLocation()

    return (
        <nav className="navBar">
            <div className="navBar-container">
                <Link to = "/" className="links">
                    <img className="logo" src = {logo}  alt = "Logo"/>
                </Link>
                <h1 className="logo-name">BuukMatch</h1>
                <ul>
                    <li><NavLink className="option-navbar" to = "/" end>Início</NavLink></li>
                    <li><NavLink className="option-navbar" to = "/sobre">Sobre Nós</NavLink></li>
                    <li><NavLink className="cadastrar" to = "/signin">Cadastrar</NavLink></li>
                    <li><NavLink className="login" to = "/login">Login</NavLink></li>
                </ul>
            </div>
        </nav>
    );

}

export default NavBar
import logo from "../assets/logo.png"
import {Link} from "react-router-dom"
import './styles/NavBar.css'
import Cadastro from "../pages/Cadastro";
import Entrar from "../pages/Entrar";
import SobreNos from "../pages/SobreNos";

function NavBar(){
    return (
        <nav className="navBar">
            <div className="navBar-container">
                <Link to = "/" className="links">
                    <img className="logo" src = {logo}  alt = "Logo"/>
                </Link>
                <h1 className="logo-name">BuukMatch</h1>
                <ul>
                    <li><Link className="sobre-nos" to = "/">SobreNos</Link></li>
                    <li><Link className="entrar" to = "/">Entrar</Link></li>
                    <li><Link className="cadastrar" to = "/">Cadastro</Link></li>
                </ul>
            </div>
        </nav>
    );

}

export default NavBar
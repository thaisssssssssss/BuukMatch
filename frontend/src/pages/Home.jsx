import NavBar from "../components/NavBar"
import "./styles/Home.css"
import {Link} from "react-router-dom"
import home_img from "../assets/home.png"

function Home(){


    return (
        <div className ="home">
            <NavBar/>
            <div className="hero">
                <div className="texto">
                    <h2 className="t-1">Troque livros,</h2>
                    <h2 className="t-2">faça encontros.</h2>
                    <h3 className="t-3">De um novo destino aos seus livros e encontre novas histórias para você.</h3> 
                    <h2 className="t-4">Quero dar march!</h2>
                </div>  
                
                <Link to = "/" className="link-hero-image">
                    <img className="home-img" src = {home_img}  alt = "Logo"/>
                </Link>
            </div>
        </div>
    );
}

export default Home
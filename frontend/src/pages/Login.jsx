import "./styles/Login.css"
import Input from "../components/Input"
import logoImg from "../assets/logo.png"
import { Link } from "react-router-dom"
import { useState } from "react"
import { authentication } from "../services/authentication"
import { useNavigate } from "react-router-dom";
import { ToastContainer, Bounce, toast } from 'react-toastify'
import {catchErro} from "../utils/ErroHandler"

function Login() {
    const trocarNavegacao = useNavigate(); 
    
    const [formData, setFormData] = useState({
        email: "",
        password: ""
    })

    const handleChange = (e) => {
        console.log(e)
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value
        }))
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            console.log("Tentando conectar com o backend");

            const respostaServidor = await authentication.login(formData);
            const token = respostaServidor.token;
            const dadosUsuario = respostaServidor.user;
            console.log(dadosUsuario)

            if(token){
                localStorage.setItem("token", respostaServidor.token);
                localStorage.setItem("user_data", JSON.stringify(dadosUsuario))
            }

            toast(`Bem-vindo de volta, ${dadosUsuario?.name || 'usuário'}! 🐮`, {
                position: "top-right",
                autoClose: 2500,
                hideProgressBar: false,
                closeOnClick: false,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "light",
                transition: Bounce,
                onClose: () => trocarNavegacao("/feed")
            });

        } catch(erro){
            catchErro(erro);
        }
    }

    return (
        <div className="login-pai">
            <ToastContainer
            position="top-right"
            autoClose={5000}
            hideProgressBar={false}
            newestOnTop={false}
            closeOnClick={false}
            rtl={false}
            pauseOnFocusLoss
            draggable
            pauseOnHover
            theme="light"
            transition={Bounce}
            />
            <section className="image-section login-section-g"></section>
            <section className="login-section login-section-g">
                <div className="head">
                    <div className="title-box">
                        <div className="title">
                            <span>Login</span>
                            <Link to="/" className="logo-link">
                                <img className="logo" src={logoImg} alt="Logo"></img>
                            </Link>
                        </div>
                        <p className="title-description">comece já a aproveitar!</p>
                    </div>

                    <form className="form-class">
                        <Input type="text" value={formData.email} label="Email" placeholder="Digite seu e-mail" name="email" onChange={handleChange} />
                        <Input type="password" value={formData.password} label="Senha" placeholder="Digite sua senha" name="password" onChange={handleChange} />
                    </form>

                    <button type="submit" className="login-button" onClick={handleSubmit}>Fazer login</button>

                    <div className="criar-conta">
                        <p>Ainda não tem uma conta?</p>
                        <Link to="/signin" className="signin-link">
                            <p className="link-criar">Entrar agora!</p>
                        </Link>
                    </div>
                </div>
            </section>
        </div>
    )
}

export default Login
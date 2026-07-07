import "./styles/Login.css"
import Input from "../components/Input"
import logoImg from "../assets/logo.png"
import { Link } from "react-router-dom"
import { useState } from "react"
import { authentication } from "../services/authentication"

function Login() {
    
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

            if(token){
                localStorage.setItem("token", respostaServidor.token);
            }
            alert(`Bem-vindo de volta, ${dadosUsuario?.name || 'usuário'}!`);
            
        } catch(erro){
            // tratar cada erro melhor
            const mensagemErro = erro.response?.data?.message || "E-mail ou senha incorretos.";
            alert(mensagemErro);
        }
        // console.log("Dados enviados: ", formData);
    }

    return (
        <div className="login-pai">
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
import React from 'react'
import "./styles/SignIn.css"
import Input from "../components/Input"
import AddressInput from "../components/AddressInput"
import logoImg from "../assets/logo.png"
import { Link } from "react-router-dom"
import { useState } from "react"
import { authentication } from '../services/authentication'


function SignIn() {
    const [showPopup, setShowPopup] = useState(false);
    const [formData, setFormData] = useState({
        email: "",
        password: "",
        birthdate: "",
        name: ""
    })

    const handleChange = (e) => {
        console.log(e)
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value
        }))
    }

    const handleAvancar =  async (e) => {
        e.preventDefault();

        if(!formData.email || !formData.password || !formData.birthdate || !formData.name){
            alert("Por favor, preencha todos os dados pessoais antes de continuar");
            return;
        }
        setShowPopup(true);

    }

  return (
      <div className="signin-pai">
          <section className="image-section signin-section-g"></section>
          <section className="signin-section signin-section-g">
            {showPopup && (
                <div className='box-pop-out-wrapper'>
                    <div className='box-pop-out'>
                        <AddressInput
                            dadosPessoais={formData}
                            FecharPopup={()=> setShowPopup(false)}
                        
                        />
                    </div>
                </div>
            )
            }
              <div className="head">
                  <div className="title-box">
                      <div className="title">
                          <span>Sign In</span>
                          <Link to="/" className="logo-link">
                              <img className="logo" src={logoImg} alt="Logo"></img>
                          </Link>
                      </div>
                      <p className="title-description">comece já a aproveitar!</p>
                  </div>

                  <form className="form-class">
                      <Input type="text" value={formData.email} label="Email" placeholder="Digite seu e-mail" name="email" onChange={handleChange} />
                      <Input type="text" value={formData.name} label="Apelido" placeholder="Digite seu username" name="name" onChange={handleChange} />
                      <Input type="password" value={formData.password} label="Senha" placeholder="Digite sua senha" name="password" onChange={handleChange} />
                      <Input type="date" value={formData.birthdate} label="dataNascimento" name="birthdate" onChange={handleChange} />
                  </form>

                  <button type="submit" className="signin-button" onClick={handleAvancar}>Fazer sign in</button>


                  <div className="fazer-login">
                      <p>Já possui uma conta?</p>
                        <Link to="/login">
                              <p className="link-login">Fazer login agora!</p>
                        </Link>
                      {/* <p className="link-login">Fazer login agora!</p> */}
                  </div>
              </div>
          </section>
      </div>
  )
}

export default SignIn
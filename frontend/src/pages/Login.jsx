import "./Login.css"

function Login() {
    return (
        <div className="login-pai">
            <section className="image-section login-section-g"></section>
            <section className="login-section login-section-g">
                <div className="head">
                    <div className="title-box">
                        <p className="title">Login</p>
                        <p className="title-description">comece já a aproveitar!</p>
                    </div>

                    <div className="input-box">
                        <label className="input-name" htmlFor="email">Email</label>
                        <input className="input-insert" id="email" type="text" name="Email" placeholder="Digite seu e-mail"/>
                    </div>

                    <div className="input-box">
                        <label className="input-name" htmlFor="senha">Senha</label>
                        <input className="input-insert" id="senha" type="password" name="Senha" placeholder="Digite sua senha" />
                    </div>
                    <button className="login-button">Fazer login</button>

                    <div className="criar-conta">
                        <p>Ainda não tem uma conta?</p>
                        <p className="link-criar">Criar agora!</p>
                    </div>
                </div>
            </section>
        </div>
    )
}

export default Login
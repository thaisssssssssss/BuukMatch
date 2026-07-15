import Logo from "../assets/logo.png"
import "./styles/ToastMatch.css"

function ToastMatch({message}) {
    return (
        <div className="toast-container">
            <img className="toast-logo" src={Logo} alt="Logo" />
            <p className="toast-description">{message}</p>
        </div>
    )
}

export default ToastMatch
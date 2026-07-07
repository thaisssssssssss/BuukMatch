import "./styles/Input.css"

function Input({label, onChange, value,...props}) {
    return (
        <div className="input-box">
            <label className="input-name" htmlFor={label}>{label}</label>
            <input className="input-insert" id={label} {...props} value={value} onChange={onChange} autoComplete="off" required/>
        </div>
    )
}

export default Input
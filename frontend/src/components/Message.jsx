import "./styles/Message.css"

function Message({ message: {owner, content, hour}, isRemetente}) {  
    return (
        <div className={`message-container ${isRemetente ? 'enviada' : 'recebida'}`}>
            <div className="message-box">
                <p className={`message-user ${isRemetente ? 'enviada' : 'recebida'}`}>{ owner }</p>
                <p className="message-content">{ content }</p>
                <p className="message-hour">{ hour }</p>
            </div>
        </div>
    )
}

export default Message
import "./styles/Message.css"

function Message({ message: {id, receiveDate, content, sentByMe, remetenteName}}) { 
    
    const hour = receiveDate.split("T")[1].slice(0, 8);
    
    return (
        <div className={`message-container ${sentByMe ? 'enviada' : 'recebida'}`}>
            <div className="message-box">
                <p className={`message-user ${sentByMe ? 'enviada' : 'recebida'}`}>{ remetenteName }</p>
                <p className="message-content">{ content }</p>
                <p className="message-hour">{ hour }</p>
            </div>
        </div>
    )
}

export default Message
import "./styles/Message.css"
import { formatData } from "../utils/FormatData"

function Message({ message: {id, receiveDate, content, sentByMe, remetenteName}}) { 
    
    return (
        <div className={`message-container ${sentByMe ? 'enviada' : 'recebida'}`}>
            <div className="message-box">
                <p className={`message-user ${sentByMe ? 'enviada' : 'recebida'}`}>{ remetenteName }</p>
                <p className="message-content">{ content }</p>
                <p className="message-hour">{ formatData(receiveDate) }</p>
            </div>
        </div>
    )
}

export default Message
import "./styles/MainChatRender.css"
import Message from "../components/Message"
import Input from "../components/Input"
import { SendHorizonal, Loader2 } from "lucide-react";
import { useState, useEffect, useRef } from 'react'
import { chatService } from '../services/chat'

function MainChatRender({ chat }) {
    if (!chat) return null;

    const [messages, setMessages] = useState([])
    const [inputContent, setInputContent] = useState({
        conteudo: ""
    })

    // Para enviar 1 mensagem por vez (Enquanto nao ha web socket)
    // Vai remover condicoes de corrida
    const [enviando, setEnviando] = useState(false)
    const idMensagemEnviadaRef = useRef(null)


    const token = localStorage.getItem("token")

    // Mantem o array de mensagens atualizado para o setInterval
    const messageRef = useRef(messages)
    useEffect(() => {
        messageRef.current = messages
    }, [messages])

    useEffect(() => {
        const buscarMensagensIniciais = async () => {
            const token = localStorage.getItem("token")
            const chat_id = chat.chatId
            try {
                const resposta_servidor = await chatService.listChatMessages(token, chat_id)
                setMessages(resposta_servidor)
            } catch(erro) {
                console.log(`Houve um erro: ${erro}`)
            }
        }

        buscarMensagensIniciais()
    }, [chat.chatId])

    const INTERVALO_BUSCA = 3000

    // Busca no backend 3s
    useEffect(() => {
        const buscarNovasMensagens = async () => {
            const listaAtual = messageRef.current
            const lastMessageAtual = listaAtual.length > 0 ?
                                        listaAtual.at(-1).id : 0

            try {
                const novasMensagens = await chatService.listNewMessages(token, chat.chatId, lastMessageAtual)
                if(novasMensagens && novasMensagens.length > 0) {
                    setMessages(prevMessages => [...prevMessages, ...novasMensagens])
                    
                    // Para verificar se a mensagem enviada ja voltou (terminar o wait)
                    const mensagemChegou = novasMensagens.some(
                        (msg) => msg.id === idMensagemEnviadaRef.current
                    )

                    if(mensagemChegou) {
                        setEnviando(false)
                        idMensagemEnviadaRef.current = null
                    }
                
                }
            } catch(erro) {
                console.log(`Houve um erro: ${erro}`)
            }
        }

        const intervalo = setInterval(buscarNovasMensagens, INTERVALO_BUSCA)

        return () =>  {
            clearInterval(intervalo)
            setEnviando(false)
            idMensagemEnviadaRef.current = null
        }
    }, [chat.chatId])

    const handleSendMessage = async (e) => {
        e.preventDefault()

        if(!inputContent.conteudo.trim()) return;

        setEnviando(true)

        try {
            // Retorna o id da mensagem enviada
            const resposta = await chatService.sendNewMessage(token, chat.chatId, inputContent)
            idMensagemEnviadaRef.current = resposta.id
            setInputContent({conteudo: ""})
        } catch(erro) {
            console.log(`Houve um erro: ${erro}`)
            setEnviando(false)
            idMensagemEnviadaRef.current = null
        }

    }

    return (
        <div className="mainchat-container">
            <ul className="mainchat-message-list">
                {messages.map((message) => (
                    <li key={message.id} className="mainchat-message-container">
                        <Message message={message} />
                    </li>
                ))}
            </ul>
            
            <div className="mainchat-input-container">
                <input className="mainchat-input" value={inputContent.conteudo} type="text" placeholder={enviando ? "Aguardando envio..." : "Digite aqui"} onChange={(e) => setInputContent({ conteudo: e.target.value })} disabled={enviando}/>
                <button className="mainchat-send-button" onClick={e => handleSendMessage(e)}>
                    {enviando ? (
                        <Loader2 className="mainchat-send-icon animate-spin" color="white" />
                    ) : (
                        <SendHorizonal color="white" className="mainchat-send-icon" />
                    )}
                </button>
            </div>
        </div>
    )

}

export default MainChatRender
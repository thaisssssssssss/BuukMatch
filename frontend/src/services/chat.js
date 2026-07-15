import api from './api'

export const chatService = {
    listChatsService: async (token) => {
        const resposta = await api.get(
            'chats/listar',
            {
                headers: {
                    Authorization: token
                }
            }
        )

        return resposta.data
    },

    listChatMessages: async (token, chatId) => {
        const resposta = await api.get(
            `chats/${chatId}/mensagens/listar`,
            {
                headers: {
                    Authorization: token
                }
            }
        )

        return resposta.data
    },

    listNewMessages: async (token, chatId, lastMessageId) => {
        const resposta = await api.get(
            `chats/${chatId}/mensagens/novas`,
            {
                headers: {
                    Authorization: token
                },
                params: {
                    lastMessageId: lastMessageId
                }
            }
        )

        return resposta.data
    },

    sendNewMessage: async (token, chatId, content) => {
        const resposta = await api.post(
            `chats/${chatId}/mensagens/enviar`,
            content,
            {
                headers: {
                    Authorization: token
                },
                
            }
        )

        return resposta.data
    }
}
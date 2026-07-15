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
        console.log(chatId)
        const resposta = await api.get(
            `/${chatId}/mensagens/listar`,
            {
                headers: {
                    Authorization: token
                }
            }
        )

        return resposta.data
    }
}
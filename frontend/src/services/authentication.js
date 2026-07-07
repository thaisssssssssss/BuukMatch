import api from './api';

export const authentication = {
    cadastrar: async(dadosUsuario) => {
        const response = await api.post('users', dadosUsuario);
        return response.data; 
    },
    login: async(dadosUsuario) => {
        const response = await api.post('users/login', dadosUsuario)
        return response.data;
    }
}
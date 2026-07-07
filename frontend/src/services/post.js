import api from './api';

export const bookService = {
    obterFeed: async (token) => {
   
    const resposta = await api.get('posts', {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
    return resposta.data;
  }
};
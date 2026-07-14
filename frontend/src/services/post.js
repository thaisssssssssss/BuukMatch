import api from './api';

// export const bookService = {
//     obterFeed: async (token) => {
   
//     const resposta = await api.get('posts', {
//       headers: {
//         'Authorization': `Bearer ${token}`
//       }
//     });
//     return resposta.data;
//   }
// };

export const postService = {
    publishPostService: async (token, dataForms) => { 
      const resposta = await api.post(
        'post', 
        dataForms, 
        {
          headers: {
            Authorization: token,
            "Content-Type": "multipart/form-data" 
          }
        }
      );
      return resposta.data;
    }
};
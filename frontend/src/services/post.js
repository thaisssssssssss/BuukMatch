import { data } from 'react-router-dom';
import api from './api';

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
    },
    registerPostLove: async (token, dataForms) => {
      const resposta = await api.post(
        'loves', 
        dataForms,
        {
          headers: {
            Authorization: token
          }
        }
      );
      return resposta.data;

    },
    registerPostLike: async (token, dataForms) => {
      const resposta = await api.post(
        'likes', 
        dataForms,
        {
          headers: {
            Authorization: token
          }
        }
      );
      return resposta.data;
    },
    listUserPosts: async (token) => {
      const response = await api.get(
        'post', 
        {
          headers: {
            Authorization: token
          }
        }
      );

      return response.data
    },

    listUnseenPost: async (token) => {
        const resposta = await api.get(
          '/post/nao-vistos', 
          {
            headers:{
              Authorization: token
            }
          }
        )

        return resposta.data;
      },
      viewPost: async (token, postId) => {
        const response = await api.post(
          `post/${postId}/visualizar`,
          null,
          {
            headers:{
              Authorization: token
            }
          }
        )

        return response.data;
      },
      listNotificationsNotRead: async (token) => {
        const response = await api.get(
          'notification',
          {
            headers:{
              Authorization: token
            }
          }
        )
        return response.data;
      }

};
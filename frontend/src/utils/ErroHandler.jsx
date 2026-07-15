import {toast, Bounce } from 'react-toastify'

export function catchErro(erro) {
    let mensagemErro = "Ocorreu um erro inesperado.";
    if(erro.response){ // ocorreu resposta do servidor
        mensagemErro = erro.response?.data?.message || `Erro do servidor: ${erro.response.status}`;
    }
    else if(erro.request){
        mensagemErro = "Erro ao se conectar com o servidor";
    }
    
    // tratar cada erro melhor
    toast.error(mensagemErro, {
        position: "top-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: false,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "light",
        transition: Bounce,
    });

}

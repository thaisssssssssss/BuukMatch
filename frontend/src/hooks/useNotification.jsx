import React, {useState, useEffect} from "react";
import { catchErro } from "../utils/ErroHandler";
import { postService } from "../services/post";

export function useNotification(){
    const [notifications, setNotifications] = useState([]);
    const FetchUnreadNotification = async () => {
        try {
            console.log("1. Disparando busca de notificações no Front-end...");

            const token = localStorage.getItem('token');
            const response = await postService.listNotificationsNotRead(token);
            const listNots = response || [];
            setNotifications(listNots);

            listNots.forEach((not) => {
                console.log("conteudo de not: ",not)
                alert(not.message || "erro")
            });

        }
        catch(erro){
            console.error("Erro ao processar notificações no Front-end:", erro);
            catchErro(erro);
        }
    }
    useEffect(() => {
            FetchUnreadNotification();
        }, []
    );

    return {
        notifications,
        FetchUnreadNotification
    }
}
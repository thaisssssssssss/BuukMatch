import React, {useState, useEffect} from "react";
import { catchErro } from "../utils/ErroHandler";
import { postService } from "../services/post";
import { toast, Bounce } from "react-toastify";
import ToastMatch from "../components/ToastMatch";

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
                toast(<ToastMatch message={not.message} />, {
                    position: "top-center",
                    pauseOnHover: true,
                    draggable: true,
                    theme: "light",
                    transition: Bounce,
                    icon: false,
                })
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
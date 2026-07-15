import React, {useState, useEffect, useMemo, createRef} from "react";
import { postService } from "../services/post";
import { catchErro } from "../utils/ErroHandler";

export function useFeed(){
    /*Variables for handling with fetch posts*/
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    /*Variables for tinder cards*/
    const [showHearts, setShowHearts] = useState(false);
    const [showTrash, setShowTrash] = useState(false);
    const [history, setHistory] = useState([])
    const [currentIndex, setCurrentIndex] = useState(-1);
    
    const fetchUnseenPosts = async () => {
        setLoading(true);
        setError(null);
        try {
            
            const token = localStorage.getItem('token'); 

            const response = await postService.listUnseenPost(token);

            setPosts(response || []); 
            setCurrentIndex((response ? response.length : 0) - 1); // mas e se tiver coisas ainda 
        } catch (err) {
            console.error("Erro ao carregar o feed:", err);
            setError("Não foi possível carregar os posts.");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
            fetchUnseenPosts();
    
            const interval = setInterval(() => {
                fetchUnseenPosts();
            }, 10000);
    
            return () => clearInterval(interval);
    }, []);

    const childRefs = useMemo(
        () => Array(posts.length).fill(0).map((i) => createRef()),
    [posts.length]
    );

    const onSwipe = (direction, post)  => {
        if (direction === 'left') {
            console.log(`Rejeitou: ${post.title}`);
        } else if (direction === 'right') {
            console.log(`Aceitou: ${post.title}`);
        }    
        setHistory((prev) => [...prev, post.id]);
        setCurrentIndex((prev) => prev - 1);
    }

    const handleLike = async () => {
        if (currentIndex < 0) return;
        try{
            const token = localStorage.getItem("token");
            const currentPostId = posts[currentIndex].id;
            

            const finalData ={
                idPost: currentPostId
            }
            await postService.registerPostLike(token, finalData);
            // alert("Pos
            setPosts((prevPosts) =>
                prevPosts.map((post) =>
                    post.id === currentPostId ? { ...post, liked: !post.liked } : post
                )
            )
        }
        catch(erro){
            catchErro(erro);
        }

    }

    const handleLove = async () => {
        // Se currentIndex for menor que 0, acabaram os cards
        if (currentIndex < 0) return;
        
        try{
            
            const token = localStorage.getItem("token");
            const currentPost = posts[currentIndex];

            const finalData ={
                idPost: currentPost.id
            }
            const resposta = await postService.registerPostLove(token, finalData);
            // alert("Post criado com sucesso!");
            // toast.success(resposta.message); ///TOAST AQUI RONALD
            alert(resposta.message);


            const response = await postService.viewPost(token, currentPost.id);
            setShowHearts(true);
            setTimeout(() => setShowHearts(false), 1000);
            
            setTimeout( async () => {
                if (childRefs[currentIndex] && childRefs[currentIndex].current) {
                    await childRefs[currentIndex].current.swipe('right');
                }     
            }, 500)
    
   
        }
        catch(erro){
            catchErro(erro);
        }
    }

    const handleReject = async () => {
        if (currentIndex < 0) return;
        const currentPost = posts[currentIndex];
        const token = localStorage.getItem("token");
        
        const response = await postService.viewPost(token, currentPost.id);
        
        setShowTrash(true);
        setTimeout(() => setShowTrash(false), 1000);

        setTimeout( async () => {
            if (childRefs[currentIndex] && childRefs[currentIndex].current) {
                await childRefs[currentIndex].current.swipe('left');
            }
        }, 500)
    };

    return {
        posts,
        showHearts,
        showTrash,
        childRefs,
        onSwipe,
        handleLove,
        handleLike,
        handleReject,
        currentIndex,

    }
}
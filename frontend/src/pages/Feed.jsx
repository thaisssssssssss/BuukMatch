import React, { useState, createRef, useMemo } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';

// Import Swiper styles
import 'swiper/css';
import 'swiper/css/effect-cards';
import './styles/Swipe.css'
import './styles/Feed.css'

// import required modules
import { EffectCards } from 'swiper/modules';
import NavBarApp from '../components/NavBarApp'
import Logo from '../assets/logo.png'

import TinderCard from 'react-tinder-card'

import Card from '../components/Card'


function Feed() {

    const initialPosts = [
            {
                "id": 1,
                "title": "The Alchemist",
                "author": "Paulo Coelho",
                "description": "A mystical story about following your dreams and listening to your heart, following the journey of a young shepherd named Santiago.",
                "pageCount": 208,
                "publicationYear": 1988,
                "publisher": "HarperOne",
                "imgSrc": "/books/the_alchemist.jpg"
            },
            {
                "id": 2,
                "title": "To Kill a Mockingbird",
                "author": "Harper Lee",
                "description": "A gripping, heart-wrenching, and wholly remarkable tale of coming-of-age in a South poisoned by virulent prejudice.",
                "pageCount": 324,
                "publicationYear": 1960,
                "publisher": "J. B. Lippincott & Co.",
                "imgSrc": "/books/to_kill_a_mockingbird.jpg"
            },
            {
                "id": 3,
                "title": "1984",
                "author": "George Orwell",
                "description": "A dystopian social science fiction novel and cautionary tale about the dangers of totalitarianism, mass surveillance, and repressive regimentation.",
                "pageCount": 328,
                "publicationYear": 1949,
                "publisher": "Secker & Warburg",
                "imgSrc": "/books/1984.jpeg"

            },
            {
                "id": 4,
                "title": "The Hobbit",
                "author": "J.R.R. Tolkien",
                "description": "A classic fantasy novel following the quest of home-loving hobbit Bilbo Baggins to win a share of the treasure guarded by Smaug the dragon.",
                "pageCount": 310,
                "publicationYear": 1937,
                "publisher": "George Allen & Unwin",
                "imgSrc": "/books/the_hobbit.jpeg"
            }
        ]
    
    const [posts, setPosts] = useState(initialPosts)

    const [history, setHistory] = useState([])

    // Nn entendi isso ainda
    // Cria um array de referências (refs) persistentes para cada card usando useMemo
    const childRefs = useMemo(
        () => Array(initialPosts.length).fill(0).map((i) => createRef()),
        []
    );


    const onSwipe = (direction, post) => {
        if (direction === 'left') {
            console.log(`Rejeitou: ${post.title}`);
        } else if (direction === 'right') {
            console.log(`Aceitou: ${post.title}`);
        }    
        
        setHistory((prev) => [...prev, post.id]);
    }

    const handleUndo = async () => {
        // Se não tiver nada no histórico, não faz nada
        if (history.length === 0) {
            console.log("Nenhum card para desfazer.");
            return;
        }

        // Pega o ID do último card que saiu da tela
        const lastSwipedId = history[history.length - 1];
        
        // Remove esse ID do histórico
        setHistory((prev) => prev.slice(0, -1));

        // Encontra o índice correspondente no array original para achar a referência do card
        const cardIndex = initialPosts.findIndex(p => p.id === lastSwipedId);
        
        // Dispara a função nativa do react-tinder-card para puxar o card de volta animado!
        if (childRefs[cardIndex] && childRefs[cardIndex].current) {
            await childRefs[cardIndex].current.restoreCard();
        }
    };

    const onCardLeftScreen = (myIdentifier) => {
        console.log(myIdentifier + ' left the screen')
    }

    return (
        <div className='container-feed'>
            <NavBarApp />
            <section 
                className='feed-swiper-container'
                
                >
                <div className='title-feed-box'>
                    <div className='title-feed'>
                        <p>Buuusque seu próximo livro</p>
                        <img src={Logo} className='logo-busca-feed' alt="" />
                    </div>
                    <p className='title-description-feed'>curta para dar match ou descarte para passar.</p>
                </div>
                
                <div className="card-stack-container">
                    {
                        posts.map((post, index) => (
                            <TinderCard
                            ref={childRefs[index]}
                            className='swipe-card' 
                            key={post.id}
                            onSwipe={(dir) => onSwipe(dir, post)}
                            preventSwipe={['up', 'down']}
                            >
                                <Card post={post} />
                            </TinderCard>
                        ))
                    }
                </div>
            </section>

            <div className='return-feed'>
                <p>Pulou? <span onClick={handleUndo}>Desfazer</span></p>
            </div>

        </div>
    )
}

export default Feed
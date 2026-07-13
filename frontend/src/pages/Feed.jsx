import React, { useState } from 'react';

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

import Card from '../components/Card'

import FloatingIcons from '../components/FloatingIcons';


function Feed() {

    const posts = [
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
    
    const [swiperInstance, setSwiperInstance] = useState(null);
    const [showHearts, setShowHearts] = useState(false);
    const [showTrash, setShowTrash] = useState(false);

    // Desfazer
    const handleUndo = () => {
        if (swiperInstance) {
            swiperInstance.allowSlidePrev = true;
            swiperInstance.slidePrev();           
            swiperInstance.allowSlidePrev = false; 
        }
    }

    const handleAccept = () => {
        if(swiperInstance) {
            const currentPost = posts[swiperInstance.activeIndex];
            console.log("Aceitou:", currentPost.title);
            
            // chamada pra api aqui

            setShowHearts(true);

            setTimeout(() => {
                setShowHearts(false);
            }, 1000);

            swiperInstance.slideNext(800);
        }
    }

    const handleReject = () => {
        if (swiperInstance) {
            const currentPost = posts[swiperInstance.activeIndex];
            console.log("Rejeitou:", currentPost.title);

            // chamada pra api aqui
            setShowTrash(true);

            setTimeout(() => {
                setShowTrash(false);
            }, 1000);

            swiperInstance.slideNext(800);
        }
    };

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
                <Swiper
                effect={'cards'}
                grabCursor={true}
                modules={[EffectCards]}
                allowSlidePrev={false}
                onSwiper={setSwiperInstance}
                grabCursor={false}
                allowTouchMove={false}
                >
                    {
                        posts.map((post) => (
                            <SwiperSlide key={post.id}>
                                <Card post={post} />
                            </SwiperSlide>
                        ))
                    }
                </Swiper>
                <div className="swipe-buttons-container">
                    <button onClick={handleReject} className='swipe-reject-button swipe-button'>
                        Rejeitar
                        <span>{showTrash && <FloatingIcons icon="❌" />}</span>

                    </button>
                    <button onClick={handleAccept} className='swipe-accept-button swipe-button'>
                        Match
                        <span>{showHearts && <FloatingIcons icon="💖" />}</span>
                    </button>
                </div>
            </section>

            <div className='return-feed'>
                <p>Pulou? <span onClick={handleUndo}>Desfazer</span></p>
            </div>

        </div>
    )
}

export default Feed
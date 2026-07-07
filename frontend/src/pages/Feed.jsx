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


function Feed() {

    const posts = [
            {
                "id": 1,
                "title": "The Alchemist",
                "author": "Paulo Coelho",
                "description": "A mystical story about following your dreams and listening to your heart, following the journey of a young shepherd named Santiago.",
                "pageCount": 208,
                "publicationYear": 1988,
                "publisher": "HarperOne"
            },
            {
                "id": 2,
                "title": "To Kill a Mockingbird",
                "author": "Harper Lee",
                "description": "A gripping, heart-wrenching, and wholly remarkable tale of coming-of-age in a South poisoned by virulent prejudice.",
                "pageCount": 324,
                "publicationYear": 1960,
                "publisher": "J. B. Lippincott & Co."
            },
            {
                "id": 3,
                "title": "1984",
                "author": "George Orwell",
                "description": "A dystopian social science fiction novel and cautionary tale about the dangers of totalitarianism, mass surveillance, and repressive regimentation.",
                "pageCount": 328,
                "publicationYear": 1949,
                "publisher": "Secker & Warburg"
            },
            {
                "id": 4,
                "title": "The Hobbit",
                "author": "J.R.R. Tolkien",
                "description": "A classic fantasy novel following the quest of home-loving hobbit Bilbo Baggins to win a share of the treasure guarded by Smaug the dragon.",
                "pageCount": 310,
                "publicationYear": 1937,
                "publisher": "George Allen & Unwin"
            }
        ]
    
    const [swiperInstance, setSwiperInstance] = useState(null);
    
    // Desfazer
    const handleUndo = () => {
        if (swiperInstance) {
            swiperInstance.allowSlidePrev = true;
            swiperInstance.slidePrev();           
            swiperInstance.allowSlidePrev = false; 
        }
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
                <Swiper
                effect={'cards'}
                grabCursor={true}
                modules={[EffectCards]}
                // allowSlidePrev={false}
                onSwiper={setSwiperInstance}
                >
                    {
                        posts.map((post) => (
                            <SwiperSlide key={post.id}>
                                <Card post={post} />
                            </SwiperSlide>
                        ))
                    }
                </Swiper>
            </section>

            <div className='return-feed'>
                <p>Pulou? <span onClick={handleUndo}>Desfazer</span></p>
            </div>

        </div>
    )
}

export default Feed
import React, { useState, useEffect, createRef } from 'react';

import { Swiper, SwiperSlide } from 'swiper/react';

// Import Swiper styles
import 'swiper/css';
import 'swiper/css/effect-cards';
import './styles/Swipe.css'
import './styles/Feed.css'

// import required modules
import { EffectCards } from 'swiper/modules';
import { Heart, Trash } from 'lucide-react';
import NavBarApp from '../components/NavBarApp'
import Logo from '../assets/logo.png'

import Card from '../components/Card'

import FloatingIcons from '../components/FloatingIcons';
import TinderCard from 'react-tinder-card'
import { postService } from '../services/post';
import { useNotification } from '../hooks/useNotification';
import { useFeed } from '../hooks/useFeed';
import { ToastContainer, Bounce, toast } from 'react-toastify';
import ToastMatch from '../components/ToastMatch';

function Feed() {

    const {posts, showHearts, showTrash, childRefs, onSwipe,handleLove, handleLike, handleReject, currentIndex} = useFeed();
    const {notifications} = useNotification();
    
    return (
        <div className='container-feed'>
            <ToastContainer
                position="top-center"
                autoClose={2000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick={false}
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
                theme="light"
                transition={Bounce}
            />
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
                    {currentIndex < 0 ? (
                        <div className="no-more-posts">
                            <h2>Ops...</h2>
                            <p>Estamos trabalhando em novos posts! 📚 ✨</p>
                        </div>
                    ) : (
                        posts.map((post, index) => (
                            <TinderCard
                                ref={childRefs[index]}
                                className='swipe-card' 
                                key={post.id}
                                onSwipe={(dir) => onSwipe(dir, post)}
                                preventSwipe={['up', 'down', 'left', 'right']}
                            >
                                <Card post={post} />
                            </TinderCard>
                        ))
                    )}
                </div>
                <div className="swipe-buttons-container" style={{ display: currentIndex < 0 ? 'none' : 'flex' }}>
                    <button onClick={handleReject} className='swipe-reject-button swipe-button'>
                        Rejeitar <Trash fill='black'/>
                        <span>{showTrash && <FloatingIcons icon="❌" />}</span>
                    </button>
                    <button onClick={handleLike} className='swipe-like-button swipe-button'>
                        Curtir <Heart fill={`${((currentIndex > 0) && posts[currentIndex].liked) ? "#E54F81" : "white"}`} />
                    </button>
                    <button onClick={handleLove} className='swipe-accept-button swipe-button'>
                        Match <Heart fill='white' />
                        <span>{showHearts && <FloatingIcons icon="💖" />}</span>
                    </button>
                </div>
            </section>
        </div>
    )
}

export default Feed
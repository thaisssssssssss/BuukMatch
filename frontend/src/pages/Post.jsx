import React from 'react'
import "./styles/Post.css"
import Input from "../components/Input"
import AddressInput from "../components/AddressInput"
import logoImg from "../assets/logo.png"
import { Link, useNavigate } from "react-router-dom"
import { useState } from "react"
import { authentication } from '../services/authentication'
import Perfil from '../components/Perfil'
import FileUploader from '../components/FileUploader'
import {postService} from '../services/post' 
import NavBarApp from '../components/NavBarApp'

function Post() {
    const trocarNavegacao = useNavigate();  
    const [formData, setFormData] = useState({
        legend: "",
        title: "",
        numberOfPages: "",
        author: "",
        publicationYear:""
    })

    // guarda o estado do FileUploader
    const[file, setFile] = useState(null);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value
        }))
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        if(!file){
            alert("Por favor, selecione uma imagem!");
            return;
        }
        const finalData = new FormData();
        finalData.append('legend', formData.legend);
        finalData.append('book.title', formData.title);
        finalData.append('book.numberOfPages', formData.numberOfPages);
        finalData.append('book.author', formData.author);
        finalData.append('book.publicationYear', formData.publicationYear);
        finalData.append('file', file);
        
        // fazer ainda o fetch
        try{
            const token = localStorage.getItem("token");
            await postService.publishPostService(token, finalData);
            alert("Post criado com sucesso!");
        } catch(error){
            console.error(error);
            if (error.response) {
            alert(`Erro ${error.response.status}: ${error.response.data}`);
            } else {
                alert("Não foi possível conectar ao servidor.");
            }
        }
    }

    return (
        <div className='post-page'>
            <NavBarApp/>
            <div className="post-pai">
                    <section className="post-load-section login-section-g">
                    <FileUploader onFileSelect={setFile}/>
                    </section>

                    <section className="post-input-section login-section-g">
                        <div className='post-input-center'>
                            <h1 className="post-title">Criar novo Post</h1>
                            <form className="post-form" onSubmit={handleSubmit}>
                                    <h2 className='post-title-dados-livro'>Dados do Livro:</h2>
                                    <Input type="text" value={formData.title} label="Título" placeholder="Digite o título do livro" name="title" onChange={handleChange} />
                                    <Input type="text" value={formData.numberOfPages} label="Número de páginas" placeholder="Digite o número de páginas do livro" name="numberOfPages" onChange={handleChange} />
                                    <Input type="text" value={formData.author} label="Autor" placeholder="Digite o autor do livro" name="author" onChange={handleChange} />
                                    <Input type="text" value={formData.publicationYear} label="Ano de Publicação" placeholder="Digite o ano de publicação do livro" name="publicationYear" onChange={handleChange} />
                                    <h2 className='post-title-description'>Adicione uma descrição:</h2>

                                    <div className="input-long-box">
                                        <label className="input-long-name" htmlFor="legend"></label>
                                        <textarea className="input-long-insert" type="text"name="legend" id='legend' placeholder="Digite descrição"  value={formData.legend} onChange={handleChange} autoComplete="off" required/>
                                    </div>


                                    {/* <Input type="text" value={formData.legend}  placeholder="Digite sua descrição" name="legend" onChange={handleChange} /> */}
                                    <button type="submit" className="post-form-button"  >Enviar</button>
                            </form>
                            </div>
                    </section>

                        
            </div>
        </div>
            
    )
}

export default Post
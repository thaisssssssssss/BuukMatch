import "./styles/AddressInput.css"
import Input from "./Input"
import { useState } from "react";
import { authentication } from '../services/authentication'

function AddressInput({dadosPessoais, fecharPopup}) {
    
    const [formData, setFormData] = useState({
            street: "",
            district: "",
            city: "",
            number: ""
        })
    
    const handleChange = (e) => {
        console.log(e)
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value
        }))
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            const dadosCompletos = {
                ...dadosPessoais,
                address: {
                    ...formData,
                    number: Number(formData.number)
                }
            };
            console.log(dadosCompletos);
            await authentication.cadastrar(dadosCompletos);

            alert("Conta criada com sucesso com o seu endereço!");
            fecharPopup();

        } catch (erro){
            console.log(erro);
            console.log(erro.response);
            console.log(erro.response?.data);

            const mensagemErro =
                erro.response?.data?.message ||
                erro.message ||
                "Erro ao salvar os dados cadastrais.";
            
            
            // const mensagemErro = erro.response?.data?.message || "Erro ao salvar os dados cadastrais.";
            alert(mensagemErro);
        }

        console.log("Dados enviados: ", formData);
    }
    return (
        <div className="wrapper">
            <h1 className="tittle-1">Quase Lá...</h1>
            <h2 className="tittle-2">Digite seu Endereço</h2>
            <form className="adress-input-box">  
                <Input type="text" value={formData.street} label="Rua" placeholder="Digite sua rua" name="street" onChange={handleChange} />
                <Input type="text" value={formData.district} label="Bairro" placeholder="Digite sua bairro" name="district" onChange={handleChange} />
                <Input type="text" value={formData.city} label="Cidade" placeholder="Digite sua cidade" name="city" onChange={handleChange} />
                <Input type="text" value={formData.number} label="Numero" placeholder="Digite seu número" name="number" onChange={handleChange} />
                <button type="submit" className="finalizar-button" onClick={handleSubmit}>Finalizar</button>
            </form>
        </div>
    )
}

export default AddressInput
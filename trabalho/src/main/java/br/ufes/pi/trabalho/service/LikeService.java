package br.ufes.pi.trabalho.service;

import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.ufes.pi.trabalho.domain.Like;
import br.ufes.pi.trabalho.dto.LikeData;
import br.ufes.pi.trabalho.domain.Postagem;
import br.ufes.pi.trabalho.domain.Usuario;
import br.ufes.pi.trabalho.repository.LikeRepository;
import br.ufes.pi.trabalho.repository.UsuarioRepository;
import br.ufes.pi.trabalho.repository.PostagemRepository;


@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UsuarioRepository usuarioRepository;
    private final PostagemRepository postagemRepository;

    public LikeService(LikeRepository likeRepository, UsuarioRepository usuarioRepository, PostagemRepository postagemRepository){
        this.likeRepository = likeRepository;
        this.usuarioRepository = usuarioRepository;
        this.postagemRepository = postagemRepository;
    }
    

    // registra um novo like no banco de dados
    public Like registrar(LikeData likeData) {
        Usuario u = usuarioRepository
        .findById(likeData.getIdUsuario())
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,"Usuario não existe"));
        
        Postagem p = postagemRepository
        .findById(likeData.getIdPostagem())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Postagem não existe"));
        

        // !!!!!! tem que buscar se existe o like inverso
        // se existe cria um match também e coloca no banco de dados
        // >>>> matchService.registrar(usuarioAtual, donoDoPost); <<<<<
        // ai teria que chamar o serviço que cria o match
        Like novo_like = new Like(u, p);
        return likeRepository.save(novo_like);
    }

} 

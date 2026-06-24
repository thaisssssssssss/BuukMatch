package br.ufes.pi.trabalho.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.pi.trabalho.domain.LikeData;
import br.ufes.pi.trabalho.domain.Like;
import br.ufes.pi.trabalho.service.LikeService;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;
    
    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }

    @PostMapping //um usuario realizar um like em uma postagem
    public Like darLikePostagem(@RequestBody LikeData likeData){
        return likeService.registrar(likeData);
    }
}

package br.ufes.pi.trabalho.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.pi.trabalho.service.LikeService;
import br.ufes.pi.trabalho.dto.LikeRequest;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;
    // tem que criar no post controller algo que retorne o numero de likes de um post
    // tem que impedir que se alguem ja deu like, de novo
    public LikeController(LikeService likeService){
        this.likeService = likeService;
    }

    @PostMapping //um User realizar um Love em uma Post
    public ResponseEntity<Void> registerLikeOnPost(@RequestBody LikeRequest request){
        likeService.registerLikeOnPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}

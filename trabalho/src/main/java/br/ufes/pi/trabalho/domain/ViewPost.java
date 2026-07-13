package br.ufes.pi.trabalho.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity                      // só pode vizualizar um post uma única vez
@Table(name = "viewed_post", uniqueConstraints = { @UniqueConstraint(columnNames = {"user_id", "post_id"})})
public class ViewPost {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "post_id")
    private Post post;
    
    private LocalDateTime viewedDateTime;

    public ViewPost(User user, Post post){
        setUser(user);
        setPost(post);
        setViewedDateTime(LocalDateTime.now());
    }

    public LocalDateTime getViewedDateTime() {
        return viewedDateTime;
    }

    public void setViewedDateTime(LocalDateTime viewedDateTime) {
        this.viewedDateTime = viewedDateTime;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

package br.ufes.pi.trabalho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufes.pi.trabalho.domain.Post;
import br.ufes.pi.trabalho.domain.User;

public interface PostRepository extends CrudRepository<Post, Long>{
    List<Post> findByOwner(User owner);

    // p.user.id <> :userId não mostra aos usuários os próprios posts
     @Query("""
        SELECT p
        FROM Post p
        WHERE p.owner.id <> :userId 
          AND NOT EXISTS (
              SELECT vp.id
              FROM ViewPost vp
              WHERE vp.post = p
                AND vp.user.id = :userId
          )
        ORDER BY p.publicationDate DESC
    """)
    List<Post> findPostsNotViewedByUser(
            @Param("userId") Long userId
    );
}

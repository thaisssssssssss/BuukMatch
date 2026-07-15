package br.ufes.pi.trabalho.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufes.pi.trabalho.domain.Notification;
import br.ufes.pi.trabalho.domain.User;

public interface NotificationRepository extends CrudRepository<Notification, Long>{
    
    @Query("SELECT n FROM Notification n WHERE n.user = :user AND n.read = false")
    List<Notification> findByUserAndReadFalse(@Param("user") User user);
    
}

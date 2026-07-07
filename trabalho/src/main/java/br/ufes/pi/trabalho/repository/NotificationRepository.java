package br.ufes.pi.trabalho.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufes.pi.trabalho.domain.Notification;
import br.ufes.pi.trabalho.domain.User;

public interface NotificationRepository extends CrudRepository<Notification, Long>{
    List<Notification> findByUserAndReadFalse(User user);
    
}

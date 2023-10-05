package br.net.silva.daniel.skipedlocked.job;

import br.net.silva.daniel.skipedlocked.model.User;
import br.net.silva.daniel.skipedlocked.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@Component
public class Job {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EntityManager entityManager;



    @Scheduled(cron = "0/3 * * * * *")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void execute() throws InterruptedException {
        System.out.println("Job executed!");
        List<User> usuarios = repository.findByName("daniel", PageRequest.of(0, 1));
        User user = usuarios.get(0);
        user.setName("daniel 2");
        repository.save(user);
        Thread.sleep(10000);

//        List<User> users = entityManager.createQuery("select u from User u where u.name = 'daniel'", User.class)
//                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
//                .setMaxResults(1)
//                .setHint("javax.persistence.lock.timeout", LockOptions.SKIP_LOCKED)
//                .getResultList();
//
//        System.out.println("Trabalhando ........");
//
//        users.forEach(user -> {
//            var user2 = new User();
//            user2.setName("Fulano de Tal");
//            entityManager2.persist(user2);
//            entityManager2.flush();
//            System.out.println("Deletando ........");
//            user.setName("daniel 2");
//            entityManager2.persist(user);
//            System.out.println("Pronto ........");
//            entityManager2.flush();
//        });
//        System.out.println("Finalizado ........");
    }
}

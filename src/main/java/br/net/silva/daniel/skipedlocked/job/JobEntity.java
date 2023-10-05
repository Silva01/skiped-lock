package br.net.silva.daniel.skipedlocked.job;

import br.net.silva.daniel.skipedlocked.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import org.hibernate.LockOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class JobEntity {

    @Autowired
    private EntityManager entityManager;

//    @Scheduled(cron = "0/4 * * * * *")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void execute() {

        List<User> users = entityManager.createQuery("select u from User u where u.status = true", User.class)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .setMaxResults(1)
                .setHint("javax.persistence.lock.timeout", LockOptions.SKIP_LOCKED)
                .getResultList();

        System.out.println("Trabalhando ........");

        users.forEach(user -> {
            System.out.println("Criando ........");
            var user2 = new User();
            user2.setName("Fulano de Tal");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        System.out.println("Finalizado ........");
    }
}

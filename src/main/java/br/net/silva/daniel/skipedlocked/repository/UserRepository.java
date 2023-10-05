package br.net.silva.daniel.skipedlocked.repository;

import br.net.silva.daniel.skipedlocked.model.User;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.hibernate.LockOptions;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = AvailableSettings.JPA_LOCK_TIMEOUT, value = LockOptions.SKIP_LOCKED + "")})
    List<User> findByStatus(Boolean status, Pageable page);
}

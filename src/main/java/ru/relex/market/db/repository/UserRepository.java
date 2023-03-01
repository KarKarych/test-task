package ru.relex.market.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.market.db.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

  boolean existsByEmail(String email);

  boolean existsByUsername(String username);

  User getBySecretKey(String secretKey);
}

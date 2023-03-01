package ru.relex.market.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.relex.market.db.entity.User;
import ru.relex.market.db.entity.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {

  @Query("""
    select  t.user
     from   VerificationToken t
     where  t.token = :token
    """)
  Optional<User> findUserByToken(String token);
}

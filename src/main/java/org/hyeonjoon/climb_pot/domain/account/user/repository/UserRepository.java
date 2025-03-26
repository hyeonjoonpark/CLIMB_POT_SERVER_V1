package org.hyeonjoon.climb_pot.domain.account.user.repository;

import org.hyeonjoon.climb_pot.domain.account.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByName(String username);
}

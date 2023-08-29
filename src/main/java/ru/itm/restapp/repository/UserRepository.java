package ru.itm.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itm.restapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
   
   @Query(value = "select u from User u join fetch u.authorities where u.username = :username")
   User findUserByUsername(@Param("username") String username);
}

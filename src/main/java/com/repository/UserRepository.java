package com.repository;

import com.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "roles")
    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);
    @Query("select u from User u join fetch u.roles ")
    List<User> getUsers();

}

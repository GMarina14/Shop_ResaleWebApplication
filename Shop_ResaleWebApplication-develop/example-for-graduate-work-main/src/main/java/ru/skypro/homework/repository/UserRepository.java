package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * получение пользователя по login
     * @param userName
     * @return User
     */
//    @Query(value = "SELECT u FROM User u WHERE u.email = :userName")
//    Optional<User> findUserByUserName(String userName);

    @Query(value = "SELECT u FROM User u WHERE u.email = :username")
    Optional<User> findUserByUserName(String username);

}

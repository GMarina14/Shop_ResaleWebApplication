package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdRepository extends JpaRepository<Ad, Integer> {
    @Query(value = "SELECT u FROM Ad u WHERE u.pk = :id")
    Optional <Ad> findAdById(Integer id);


    List<Ad> findAllByAuthor(User user);

}

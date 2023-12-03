package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

@Repository
public interface ImageRepository extends JpaRepository<Image,Integer> {
    /**
     * получение фотографии по id
     * @param id
     * @return Image
     */
    @Query(value = "SELECT i FROM Image i WHERE i.id = :id")
   Image findImageById(Integer id);





}

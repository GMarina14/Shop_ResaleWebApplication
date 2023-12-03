package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Comment;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    /**
     * Получаем список комментариев из таблицы "comment" по id объявления (внешний ключ)
     */
    /*@Query(value = "SELECT * FROM comment WHERE ad_id = :adId", nativeQuery = true)
    List<Comment> getCommentsByAdIdIs(@Param("adId") Integer adId);
*/
    List<Comment> findCommentsByAd_Pk(Integer adId);


    void deleteCommentByAd_PkAndId(Integer pk, Integer id);

    /*@Query(value = "DELETE FROM comment WHERE ad_id = :adId AND id =:id", nativeQuery = true)
    void deleteCommentAdIdAndCommentId(Integer adId, Integer id);*/

    @Query(value = "DELETE FROM comment WHERE ad_id = :adId", nativeQuery = true)
    void deleteCommentsByAdId(Integer adId);

}

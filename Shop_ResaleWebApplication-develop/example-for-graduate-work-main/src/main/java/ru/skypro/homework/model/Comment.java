package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  //id комментария

    private Integer createdAt; //время создания объвления
    private String text; //текст объявления

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user; //внешний ключ id пользователя, который размести комментарий и стал автором

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad; //внешний ключ id объявления, к которому разместили комментарии*/ // видимо это тоже лишнее, мы можем добраться через юзера
}

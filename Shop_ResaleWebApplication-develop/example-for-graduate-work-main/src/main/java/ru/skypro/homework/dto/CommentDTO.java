package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Integer pk; //id комментария
    private Integer createdAt; //дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
    private String text; //текст комментария
    private Integer author; //id автора комментария
    private String authorImage; //ссылка на аватар комментария
    private String authorFirstName; //имя создателя комментария


}

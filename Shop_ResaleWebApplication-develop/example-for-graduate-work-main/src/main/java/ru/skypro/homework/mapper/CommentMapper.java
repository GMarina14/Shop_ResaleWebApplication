package ru.skypro.homework.mapper;

import org.mapstruct.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    @Named("authorToInt")
    default Integer authorToInt(User user) {
        return user.getId();
    }

    @Named("imageToString")
    default String imageToString(Image image){
        //****** OR
        String s = image.getId().toString();


       /* //****** OR
        byte[] array = image.getImage();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            str.append(array[i]);
        }*/
        return null;//str.toString();
    }

    //добавление комментария (DTO)
    @Mapping(target = "author", source = "user", qualifiedByName = "authorToInt")
    @Mapping(target = "pk", source = "comment.id")
    @Mapping(target = "authorImage", source = "ad.image", qualifiedByName = "imageToString")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    CommentDTO entityToDTO(Comment comment);

    //Получение списка комментариев
    List<CommentDTO> commentsDTOToList(List<CommentDTO> comments);

    //создание или обновление комментария

    Comment CreateOrUpdateCommentDTOToEntity(CreateOrUpdateCommentDTO createOrUpdateCommentDTO);

}

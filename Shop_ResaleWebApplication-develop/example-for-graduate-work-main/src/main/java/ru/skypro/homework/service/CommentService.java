package ru.skypro.homework.service;

import ru.skypro.homework.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<Comment> getComments(Integer adId);

    Comment addComment(Integer adId, Comment comment);

    void deleteComment(Integer adId, Integer commentId);

    Comment patchComment(Integer adId, Integer commentId, String text);

}

package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    @Override
    public List<Comment> getComments(Integer adId) {
        List<Comment> commentsByAdPk = commentRepository.findCommentsByAd_Pk(adId);
        return commentsByAdPk;
    }

    @Override
    public Comment addComment(Integer adId, Comment comment) {

        Optional<Ad> adById = adRepository.findAdById(adId);
        if (!adById.isPresent()) {
            return null;
        } else {
            comment.setAd(adById.get());
            comment.setUser(userRepository.findById(adById.get().getAuthor().getId()).get());
            return commentRepository.save(comment);
        }
    }

    @Override
    @Transactional
    public void deleteComment(Integer adId, Integer commentId) {
        commentRepository.deleteCommentByAd_PkAndId(adId, commentId);

    }

    @Override
    public Comment patchComment(Integer adId, Integer commentId, String text) {
        Optional<Ad> adById = adRepository.findAdById(adId);
        Optional<Comment> commentById = commentRepository.findById(commentId);

        if (!adById.isPresent() || !commentById.isPresent()) {
            return null;
        } else {
            commentById.get().setText(text);
        }
        Comment comment = commentById.get();
        commentRepository.save(comment);
        return comment;

    }
}

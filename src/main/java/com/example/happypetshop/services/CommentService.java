package com.example.happypetshop.services;

import com.example.happypetshop.models.dtos.CommentDTO;
import com.example.happypetshop.models.entities.CommentEntity;
import com.example.happypetshop.models.entities.UserEntity;
import com.example.happypetshop.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserService userService;

    public CommentService(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }


    public List<CommentEntity> getAllComments() {
        return this.commentRepository.findAll();
    }

    public void saveComment(CommentDTO commentModel, Long id) {
        CommentEntity comment = new CommentEntity();
        comment.setComment(commentModel.getComment());

        UserEntity userById = this.userService.getUserById(id);

        if (userById == null) {
            throw new RuntimeException("User not found");
        }

        comment.setUsername(userById.getEmail());

        this.commentRepository.save(comment);

    }
}

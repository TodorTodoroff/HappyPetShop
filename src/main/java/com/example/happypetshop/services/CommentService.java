package com.example.happypetshop.services;

import com.example.happypetshop.models.dtos.CommentDTO;
import com.example.happypetshop.models.dtos.CommentDisplayViewDTO;
import com.example.happypetshop.models.entities.CommentEntity;
import com.example.happypetshop.models.entities.UserEntity;
import com.example.happypetshop.models.mapper.CommentMapper;
import com.example.happypetshop.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserService userService;

    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, UserService userService, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.commentMapper = commentMapper;
    }


    public List<CommentDisplayViewDTO> getAllComments() {
        return this.commentRepository.findAll().stream().map(this.commentMapper::foodEntityToFoodDto).toList();
    }

    public void saveComment(CommentDTO commentModel, Long id) {
        CommentEntity comment = new CommentEntity();
        comment.setComment(commentModel.getComment());

        UserEntity userById = this.userService.getUserById(id);

        comment.setUsername(userById.getEmail());
        this.commentRepository.save(comment);

    }
}

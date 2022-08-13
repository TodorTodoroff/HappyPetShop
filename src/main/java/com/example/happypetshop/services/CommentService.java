package com.example.happypetshop.services;

import com.example.happypetshop.models.entities.CommentEntity;
import com.example.happypetshop.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

private CommentRepository commentRepository;

public CommentService(CommentRepository commentRepository){
    this.commentRepository = commentRepository;
}


    public List<CommentEntity> getAllComments() {
    return this.commentRepository.findAll();
    }
}

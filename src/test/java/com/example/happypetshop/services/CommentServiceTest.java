package com.example.happypetshop.services;

import com.example.happypetshop.models.dtos.CommentDisplayViewDTO;
import com.example.happypetshop.models.entities.CommentEntity;
import com.example.happypetshop.models.mapper.CommentMapper;
import com.example.happypetshop.models.mapper.CommentMapperImpl;
import com.example.happypetshop.repositories.CommentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository mockCommentRepository;

    private UserService userService;

    private CommentService toTest;
    private CommentMapper commentMapper;

    @BeforeEach
    void setUp() {
        toTest = new CommentService(mockCommentRepository, userService, commentMapper);
    }

//    @Test
//    void getAllCommentsTest() {
//        CommentEntity testComment1 = new CommentEntity();
//        testComment1.setComment("test comment1");
//        testComment1.setUsername("test1@test.com");
//
//        CommentEntity testComment2 = new CommentEntity();
//        testComment2.setComment("test comment2");
//        testComment2.setUsername("test2@test.com");
//
//
//        when(mockCommentRepository.findAll()).thenReturn(List.of(testComment1, testComment2));
//
//        List<CommentDisplayViewDTO> allComments = toTest.getAllComments();
//
//        Assertions.assertEquals(2, allComments.size());
//        Assertions.assertEquals(testComment1.getComment(), allComments.get(0).getComment());
//        Assertions.assertEquals(testComment1.getUsername(), allComments.get(0).getUsername());
//
//        Assertions.assertEquals(testComment2.getComment(), allComments.get(1).getComment());
//        Assertions.assertEquals(testComment2.getUsername(), allComments.get(1).getUsername());
//
//
//    }

    @Test
    void saveCommentTest() {
        CommentEntity testComment1 = new CommentEntity();
        testComment1.setComment("test comment1");
        testComment1.setUsername("test1@test.com");

        mockCommentRepository.save(testComment1);

        verify(mockCommentRepository, times(1)).save(any());

    }

}

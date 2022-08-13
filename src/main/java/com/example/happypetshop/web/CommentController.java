package com.example.happypetshop.web;

import com.example.happypetshop.services.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.rowset.serial.SerialStruct;

@Controller
public class CommentController {

    private final CommentService commentService;

    public CommentController (CommentService commentService){
        this.commentService = commentService;
    }


    @GetMapping("/comments")
    public String getComments(
            Model model
    ){

        model.addAttribute("comments", this.commentService.getAllComments());

        return "comments";
    }

}

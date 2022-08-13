package com.example.happypetshop.web;

import com.example.happypetshop.models.dtos.CommentDTO;
import com.example.happypetshop.models.dtos.UserRegisterDTO;
import com.example.happypetshop.services.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ModelAttribute("commentModel")
    public CommentDTO initUserModel() {
        return new CommentDTO();
    }

    @GetMapping
    public String getComments(
            Model model
    ) {

        model.addAttribute("comments", this.commentService.getAllComments());

        return "comments";
    }

    @PostMapping("/submit/{id}")
    public String submitComment(
            @PathVariable("id") Long id,
            @Valid CommentDTO commentModel,
            BindingResult commentBR,
            RedirectAttributes redirectAttributes
    ) {

        if (commentBR.hasErrors()) {
            redirectAttributes.addFlashAttribute("noComment", true);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentModel", commentBR);

            return "redirect:/comments";
        }

        this.commentService.saveComment(commentModel,id);

        return "redirect:/comments";
    }

}

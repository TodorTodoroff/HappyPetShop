package com.example.happypetshop.web;

import com.example.happypetshop.models.dtos.CommentDTO;
import com.example.happypetshop.models.dtos.UserRegisterDTO;
import com.example.happypetshop.services.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/submit")
    public String submitComment(
            @Valid CommentDTO commentDTO,
            BindingResult commentBR,
            RedirectAttributes redirectAttributes
    ) {

        if (commentBR.hasErrors()) {
            redirectAttributes.addFlashAttribute("noComment", true);

            return "redirect:/comments";
        }

        return "redirect:/comments";
    }

}

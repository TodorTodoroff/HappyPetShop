package com.example.happypetshop.models.dtos;

import javax.validation.constraints.NotEmpty;

public class CommentDTO {

    @NotEmpty
    private String comment;

    public CommentDTO() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

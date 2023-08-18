package com.tkxel.microknowledgesystem.dtos;

import com.tkxel.microknowledgesystem.models.Comment;
import com.tkxel.microknowledgesystem.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MicroKnowledgeDto {
    private String id;
    private String keyword;
    private String content;
    private Integer numberOfStars=0;
    private LocalDate lastEditTime;
    private Set<Comment> containedComment;
    private UserModel userStarModel;
    private UserModel userContainModel;
}
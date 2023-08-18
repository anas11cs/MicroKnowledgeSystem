package com.tkxel.microknowledgesystem.dtos;

import com.tkxel.microknowledgesystem.models.Comment;
import com.tkxel.microknowledgesystem.models.MicroKnowledge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;
    private String userPassword;
    private String userName;
    private Set<MicroKnowledge> staredMicroknowledge;
    private Set<Comment> readertoComment;
    private Set<MicroKnowledge> containedMicroknowledge;
}

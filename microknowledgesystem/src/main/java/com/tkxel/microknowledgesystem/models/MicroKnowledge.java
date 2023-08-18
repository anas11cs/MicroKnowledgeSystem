package com.tkxel.microknowledgesystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "microknowledge")
public class MicroKnowledge {
    @Id
    private String id;

    private String keyword;
    private String content;
    private Integer numberOfStars=0;
    private LocalDate lastEditTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "commenttoMicroknowledge")
    private Set<Comment> containedComment;

    @ManyToOne()
    @JoinColumn(name = "user_star_id")
    @JsonIgnore
    private UserModel userStarModel;

    @ManyToOne()
    @JoinColumn(name = "user_contain_id")
    @JsonIgnore
    private UserModel userContainModel;
}

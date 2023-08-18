package com.tkxel.microknowledgesystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private LocalDate writingTime;

    @ManyToOne
    @JoinColumn(name = "micro_id")
    @JsonIgnore
    private MicroKnowledge commenttoMicroknowledge;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserModel commenttoReader;
}

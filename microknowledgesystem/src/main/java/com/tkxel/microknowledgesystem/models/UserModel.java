package com.tkxel.microknowledgesystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usermodel")
public class UserModel {
    @Id
    private String id;

    private String userPassword;

    @Column(unique = true)
    private String userName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userStarModel")
    private Set<MicroKnowledge> staredMicroknowledge;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commenttoReader")
    private Set<Comment> readertoComment;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userContainModel")
    private Set<MicroKnowledge> containedMicroknowledge;
}
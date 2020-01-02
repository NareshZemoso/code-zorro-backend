package com.zemoso.codezorro.taskSetService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "question",schema = "PUBLIC")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Question {

    @Id
    @Column(name = "qid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qid;

    @NotNull
    private String qname;

    @NotNull
    private String description;

    @NotNull
    private String level;

    @NotNull
    @Column(name = "prob_stmt")
    private String probStmt;

    @NotNull
    @Column(name = "func_desc")
    private String funcDesc;

    @NotNull
    @Column(name = "in_fmt")
    private String inFmt;

    @NotNull
    @Column(name = "out_fmt")
    private String outFmt;

    @NotNull
    @Column(name = "sam_in")
    private String samIn;

    @NotNull
    @Column(name = "sam_out")
    private String samOut;

    @NotNull
    private String explanation;

    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "questions")
    private Set<Test> tests = new HashSet<>();

    @OneToMany(mappedBy = "question",fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            })
    private Set<TestCase> testCases = new HashSet<>();
}

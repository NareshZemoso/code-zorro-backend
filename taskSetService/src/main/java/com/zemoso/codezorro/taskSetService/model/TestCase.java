package com.zemoso.codezorro.taskSetService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "testcase", schema = "PUBLIC")
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class TestCase {

    @Id
    @Column(name = "tcid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tcid;

    @NotNull
    private String input;

    @NotNull
    private String output;

    @NotNull
    private int weightage;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @JoinColumn(name = "qid")
    private Question question;
}

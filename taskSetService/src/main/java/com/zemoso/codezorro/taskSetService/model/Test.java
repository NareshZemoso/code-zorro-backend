package com.zemoso.codezorro.taskSetService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "test",schema = "PUBLIC")
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Test {

    @Id
    @Column(name = "tid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;

    @NotNull
    @NaturalId
    private String tname;

    @NotNull
    private String category;

    @NotNull
    private Time time;

    @NotNull
    @Column(name = "test_link")
    private String testLink="";

    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "test_question",
            joinColumns = { @JoinColumn(name = "test_tid") },
            inverseJoinColumns = { @JoinColumn(name = "question_qid") })
    private Set<Question> questions = new HashSet<>();
}

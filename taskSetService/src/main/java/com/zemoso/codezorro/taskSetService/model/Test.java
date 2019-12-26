package com.zemoso.codezorro.taskSetService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "test")
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;

    @NotNull
    private String tname;

    @NotNull
    private String description;

    @NotNull
    private Time time;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "Test_Question",
            joinColumns = { @JoinColumn(name = "test_tid") },
            inverseJoinColumns = { @JoinColumn(name = "question_qid") })
    private Set<Question> questions = new HashSet<>();
}

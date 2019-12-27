package com.zemoso.codezorro.sessionmanagement.entities;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name="audit")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long auditId;

    @OneToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    @Column(name="test_id")
    private long testId;

    @Column(name="question_id")
    private long questionId;

    @Column(name="code")
    private Blob code;
}

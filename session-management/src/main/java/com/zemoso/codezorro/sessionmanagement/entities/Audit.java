package com.zemoso.codezorro.sessionmanagement.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name="audit")
@Data
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    @OneToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    @Column(name="test_id")
    private long testId;

    @Column(name="question_id")
    private long questionId;

    @Column(name="code")
    @Lob
    private byte[] code;
}

package com.zemoso.codezorro.sessionmanagement.entities;

import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="session")
@Data
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="session_id")
    private long sessionId;

    @OneToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    @Column(name="recruiter_id")
    private String recruiterId;

    @Column(name="test_id")
    private long testId;

    @Column(name = "start_session", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startSession;

    @Column(name = "end_session", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endSession;
}

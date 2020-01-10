package com.zemoso.codezorro.sessionmanagement.entities;
import lombok.Data;
import javax.persistence.*;
import java.sql.Blob;

@Data
@Entity
@Table(name="candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long candidateId;

    @Column(name="candidate_name")
    private String candidateName;

    @Column(name="candidate_address")
    private String candidateAddress;

    @Column(name="resume")
    @Lob
    private byte[] resume;

    @Column(name="candidate_email")
    private String candidateEmail;
}

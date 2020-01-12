package com.zemoso.codezorro.taskSetService.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Table(name = "accesslink")
@EntityListeners(AuditingEntityListener.class)
public class AccessLink implements Model{
    @Id
    @Column(name = "linkid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String testlink;

    @NotNull
    String accesskey=randomAlphaNumeric(10);

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

}

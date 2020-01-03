package com.zemoso.codezorro.usermanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="recruiter")
@Getter
@Setter
public class Recruiter  extends User{
    @NotNull
    private String name;

    @NotNull
    private RoleType role;
}

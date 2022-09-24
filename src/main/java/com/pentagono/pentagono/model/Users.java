package com.pentagono.pentagono.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false)
    private Long idUser;

    @ManyToOne
    @JoinColumn(name = "user_enterprise", referencedColumnName = "idEnterprise")
    private Enterprise enterprise;

    @Column(name="name",length=50, nullable = false)
    private String name;

    @Column(name="password",length=8, nullable = false)
    private String password;

    @Column(name="role",nullable=false)
    private RoleName rolename;

    private boolean enabled;
}

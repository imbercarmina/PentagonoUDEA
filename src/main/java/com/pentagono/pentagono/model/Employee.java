package com.pentagono.pentagono.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idEmployee;

    @ManyToOne
    @JoinColumn(name = "employee_enterprise", referencedColumnName = "idEnterprise")
    private Enterprise enterprise;

    @Column(name="identification",length=80,nullable = false)
    private String identification;

    @Column(name="email",length=80,nullable = false, unique = true)
    @Email
    private String email;

    @Column(name = "phone",length=30)
    private String phone;

    @Column(name="name",length=80,nullable = false)
    private String name;

    @Column(name = "image")
    private String image;

    private String rol;

    private String password;

    private Boolean estado;

    @Column(name="createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name="updateAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    private boolean enabled;


    public Employee get() {
        return null;
    }
}

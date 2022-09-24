package com.pentagono.pentagono.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long idTransaction;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<TransactionDetail> details;

    @ManyToOne
    @JoinColumn(name = "employee_transaction", nullable = false, referencedColumnName = "idEmployee")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "enterpriseMovements", nullable = false, referencedColumnName = "idEnterprise")
    private Enterprise enterprise;

    @Column(name="createdAt",nullable = false)
    private Date createdAt;

    @Column(name="updateAt",nullable = false)
    private Date updatedAt;

    @Column(precision=8, scale=2, nullable = false)
    private float amount;/*LINA*/

    @Column(name="concept", length=255, nullable = false)
    private String concept;/*LINA*/

    @Column(name="balance",length=50)
    private long balance;

    @Column(nullable = false)
    private boolean enabled;
}

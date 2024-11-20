package com.website.admin.log.entity;

import com.website.entity.AbstractEntity;
import com.website.entity.Customer;
import com.website.admin.user.entity.Admin;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "customer_block_log")
public class CustomerBlockLog extends AbstractEntity {
    @Column(name="remarks")
    private String remarks;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="admin", nullable=false, referencedColumnName = "id")
    private Admin admin;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="customer", nullable=false, referencedColumnName = "id")
    private Customer customer;

    @Column(name = "blocked_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date blockedDate;
}

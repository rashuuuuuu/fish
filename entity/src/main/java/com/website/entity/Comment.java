package com.website.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="comment")
public class Comment extends AbstractEntity{

    @Column(name="subject", nullable = false)
    private String subject;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name = "code",nullable = false,unique = true)
    private String code;

    @Column(name = "is_replied",nullable = false)
    private boolean isReplied;

    @ManyToOne(optional = false)
    @JoinColumn(name="customer", referencedColumnName = "id")
    private Customer customer;

    @Column(name="recorded_date", nullable = false)
    private Date recordedDate;
}


package com.website.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment_response")
public class CommentResponse extends AbstractEntity{

    @Column(name = "message")
    private String message;

    @Column(name = "code",nullable = false,unique = true)
    private String code;

    @Column(name="response_date",nullable = false)
    private Date responseDate;

    @OneToOne(optional = false)
    @JoinColumn(name = "comment", referencedColumnName = "id")
    private Comment comment;
}

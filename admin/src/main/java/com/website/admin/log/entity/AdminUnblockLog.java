package com.website.admin.log.entity;

import com.website.entity.AbstractEntity;
import com.website.admin.user.entity.Admin;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "admin_unblock_log")
public class AdminUnblockLog extends AbstractEntity {
    @Column(name="remarks",nullable = false)
    private String remarks;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="admin", nullable=false, referencedColumnName = "id")
    private Admin admin;

    @Column(name="unblocked_by", nullable = false)
    private String unblockedBy;

    @Column(name = "unblocked_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date unblockedDate;
}

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
@Table(name = "admin_block_log")
public class AdminBlockLog extends AbstractEntity {

    @Column(name="remarks", nullable = false)
    private String remarks;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="admin", nullable=false, referencedColumnName = "id")
    private Admin admin;

    @Column(name="blocked_by", nullable = false)
    private String blockedBy;

    @Column(name = "blocked_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date blockedDate;

}

package com.website.admin.log.entity;

import com.website.admin.accessgroup.entity.AccessGroup;
import com.website.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "accessgroup_unblock_log")
public class AccessGroupUnblockLog extends AbstractEntity {

    @Column(name="remarks",nullable = false)
    private String remarks;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="accessgroup", nullable=false, referencedColumnName = "id")
    private AccessGroup accessGroup;

    @Column(name="unblocked_by", nullable = false)
    private String unblockedBy;

    @Column(name = "unblocked_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date unblockedDate;
}

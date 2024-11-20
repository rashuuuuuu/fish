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
@Table(name = "accessgroup_block_log")
public class AccessGroupBlockLog extends AbstractEntity {

    @Column(name="remarks", nullable = false)
    private String remarks;

    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="accessgroup", nullable=false, referencedColumnName = "id")
    private AccessGroup accessGroup;

    @Column(name="blocked_by", nullable = false)
    private String blockedBy;

    @Column(name = "blocked_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date blockedDate;
}

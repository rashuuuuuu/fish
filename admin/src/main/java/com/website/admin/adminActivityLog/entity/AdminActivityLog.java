package com.website.admin.adminActivityLog.entity;

import com.website.entity.AbstractEntity;
import com.website.admin.user.entity.Admin;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "admin_activity_log")
public class AdminActivityLog extends AbstractEntity {
    @ManyToOne(optional=true, fetch = FetchType.LAZY)
    @JoinColumn(name="admin", nullable=false, referencedColumnName = "id")
    private Admin admin;

    @Column(name = "action")
    private String action;

    @Column(name = "object")
    private String object;

    @Column(name = "object_identifier")
    private String objectIdentifier;

    @Column(name = "activity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime activityDate;
}

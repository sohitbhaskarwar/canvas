package com.example.test.canvas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "workflow_state")
    private String workflowState;

    @Column(name = "parent_account_id")
    private Integer parentAccountId;

    @Column(name = "root_account_id")
    private Integer rootAccountId;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "default_storage_quota_mb")
    private Integer defaultStorageQuotaMb;

    @Column(name = "default_user_storage_quota_mb")
    private Integer defaultUserStorageQuotaMb;

    @Column(name = "default_group_storage_quota_mb")
    private Integer defaultGroupStorageQuotaMb;

    @Column(name = "default_time_zone")
    private String defaultTimeZone;

}

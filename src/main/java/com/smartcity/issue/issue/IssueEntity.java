package com.smartcity.issue.issue;

import com.smartcity.models.IssueStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "issue")
public class IssueEntity {
    @Id
    @Column("id")
    private String id;
    @Column("name")
    private String name;
    @Column("description")
    private String description;
    @Column("created_by")
    private String createdBy;
    @Column("assigned_by")
    private String assignedBy;
    @Column("assigned_to")
    private String assignedTo;
    @Column("status")
    private IssueStatus status;
    @CreatedDate
    @Column("created_at")
    private Instant createdAt;
    @Column("updated_at")
    @LastModifiedDate
    private Instant updatedAt;
    @Version
    @Column("etag")
    private Long etag;
    @Column("longitude")
    private double longitude;
    @Column("latitude")
    private double latitude;
}

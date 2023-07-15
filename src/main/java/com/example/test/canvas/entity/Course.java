package com.example.test.canvas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "start_at")
    private String startAt;

    @Column(name = "grading_standard_id")
    private Integer gradingStandardId;

    @Column(name = "is_public")
    private boolean isPublic;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "default_view")
    private String defaultView;

    @Column(name = "root_account_id")
    private Integer rootAccountId;

    @Column(name = "enrollment_term_id")
    private Integer enrollmentTermId;

    @Column(name = "license")
    private String license;

    @Column(name = "end_at")
    private String endAt;

    @Column(name = "public_syllabus")
    private boolean publicSyllabus;

    @Column(name = "public_syllabus_to_auth")
    private boolean publicSyllabusToAuth;

    @Column(name = "storage_quota_mb")
    private int storageQuotaMb;

    @Column(name = "is_public_to_auth_users")
    private boolean isPublicToAuthUsers;

    @Column(name = "apply_assignment_group_weights")
    private boolean applyAssignmentGroupWeights;

    @Column(name = "calendar")
    private String calendar;

    @Column(name = "time_zone")
    private String timeZone;

    @Column(name = "blueprint")
    private boolean blueprint;

    @Column(name = "sis_course_id")
    private String sisCourseId;

    @Column(name = "sis_import_id")
    private String sisImportId;

    @Column(name = "integration_id")
    private String integrationId;

    @Column(name = "enrollments")
    private String enrollments;

    @Column(name = "hide_final_grades")
    private boolean hideFinalGrades;

    @Column(name = "workflow_state")
    private String workflowState;

    @Column(name = "restrict_enrollments_to_course_dates")
    private boolean restrictEnrollmentsToCourseDates;

    @Column(name = "overridden_course_visibility")
    private String overriddenCourseVisibility;
}



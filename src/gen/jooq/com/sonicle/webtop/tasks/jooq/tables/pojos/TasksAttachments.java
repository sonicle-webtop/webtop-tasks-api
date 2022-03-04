/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.tasks.jooq.tables.pojos;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TasksAttachments implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private java.lang.String       taskAttachmentId;
    private java.lang.String       taskId;
    private org.joda.time.DateTime revisionTimestamp;
    private java.lang.Short        revisionSequence;
    private java.lang.String       filename;
    private java.lang.Long         size;
    private java.lang.String       mediaType;

    public TasksAttachments() {}

    public TasksAttachments(TasksAttachments value) {
        this.taskAttachmentId = value.taskAttachmentId;
        this.taskId = value.taskId;
        this.revisionTimestamp = value.revisionTimestamp;
        this.revisionSequence = value.revisionSequence;
        this.filename = value.filename;
        this.size = value.size;
        this.mediaType = value.mediaType;
    }

    public TasksAttachments(
        java.lang.String       taskAttachmentId,
        java.lang.String       taskId,
        org.joda.time.DateTime revisionTimestamp,
        java.lang.Short        revisionSequence,
        java.lang.String       filename,
        java.lang.Long         size,
        java.lang.String       mediaType
    ) {
        this.taskAttachmentId = taskAttachmentId;
        this.taskId = taskId;
        this.revisionTimestamp = revisionTimestamp;
        this.revisionSequence = revisionSequence;
        this.filename = filename;
        this.size = size;
        this.mediaType = mediaType;
    }

    /**
     * Getter for <code>tasks.tasks_attachments.task_attachment_id</code>.
     */
    public java.lang.String getTaskAttachmentId() {
        return this.taskAttachmentId;
    }

    /**
     * Setter for <code>tasks.tasks_attachments.task_attachment_id</code>.
     */
    public void setTaskAttachmentId(java.lang.String taskAttachmentId) {
        this.taskAttachmentId = taskAttachmentId;
    }

    /**
     * Getter for <code>tasks.tasks_attachments.task_id</code>.
     */
    public java.lang.String getTaskId() {
        return this.taskId;
    }

    /**
     * Setter for <code>tasks.tasks_attachments.task_id</code>.
     */
    public void setTaskId(java.lang.String taskId) {
        this.taskId = taskId;
    }

    /**
     * Getter for <code>tasks.tasks_attachments.revision_timestamp</code>.
     */
    public org.joda.time.DateTime getRevisionTimestamp() {
        return this.revisionTimestamp;
    }

    /**
     * Setter for <code>tasks.tasks_attachments.revision_timestamp</code>.
     */
    public void setRevisionTimestamp(org.joda.time.DateTime revisionTimestamp) {
        this.revisionTimestamp = revisionTimestamp;
    }

    /**
     * Getter for <code>tasks.tasks_attachments.revision_sequence</code>.
     */
    public java.lang.Short getRevisionSequence() {
        return this.revisionSequence;
    }

    /**
     * Setter for <code>tasks.tasks_attachments.revision_sequence</code>.
     */
    public void setRevisionSequence(java.lang.Short revisionSequence) {
        this.revisionSequence = revisionSequence;
    }

    /**
     * Getter for <code>tasks.tasks_attachments.filename</code>.
     */
    public java.lang.String getFilename() {
        return this.filename;
    }

    /**
     * Setter for <code>tasks.tasks_attachments.filename</code>.
     */
    public void setFilename(java.lang.String filename) {
        this.filename = filename;
    }

    /**
     * Getter for <code>tasks.tasks_attachments.size</code>.
     */
    public java.lang.Long getSize() {
        return this.size;
    }

    /**
     * Setter for <code>tasks.tasks_attachments.size</code>.
     */
    public void setSize(java.lang.Long size) {
        this.size = size;
    }

    /**
     * Getter for <code>tasks.tasks_attachments.media_type</code>.
     */
    public java.lang.String getMediaType() {
        return this.mediaType;
    }

    /**
     * Setter for <code>tasks.tasks_attachments.media_type</code>.
     */
    public void setMediaType(java.lang.String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public String toString() {
        java.lang.StringBuilder sb = new java.lang.StringBuilder("TasksAttachments (");

        sb.append(taskAttachmentId);
        sb.append(", ").append(taskId);
        sb.append(", ").append(revisionTimestamp);
        sb.append(", ").append(revisionSequence);
        sb.append(", ").append(filename);
        sb.append(", ").append(size);
        sb.append(", ").append(mediaType);

        sb.append(")");
        return sb.toString();
    }
}

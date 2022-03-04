/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.tasks.jooq.tables.pojos;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TasksRecurrences implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private java.lang.String       taskId;
    private org.joda.time.DateTime start;
    private org.joda.time.DateTime until;
    private java.lang.String       rule;

    public TasksRecurrences() {}

    public TasksRecurrences(TasksRecurrences value) {
        this.taskId = value.taskId;
        this.start = value.start;
        this.until = value.until;
        this.rule = value.rule;
    }

    public TasksRecurrences(
        java.lang.String       taskId,
        org.joda.time.DateTime start,
        org.joda.time.DateTime until,
        java.lang.String       rule
    ) {
        this.taskId = taskId;
        this.start = start;
        this.until = until;
        this.rule = rule;
    }

    /**
     * Getter for <code>tasks.tasks_recurrences.task_id</code>.
     */
    public java.lang.String getTaskId() {
        return this.taskId;
    }

    /**
     * Setter for <code>tasks.tasks_recurrences.task_id</code>.
     */
    public void setTaskId(java.lang.String taskId) {
        this.taskId = taskId;
    }

    /**
     * Getter for <code>tasks.tasks_recurrences.start</code>.
     */
    public org.joda.time.DateTime getStart() {
        return this.start;
    }

    /**
     * Setter for <code>tasks.tasks_recurrences.start</code>.
     */
    public void setStart(org.joda.time.DateTime start) {
        this.start = start;
    }

    /**
     * Getter for <code>tasks.tasks_recurrences.until</code>.
     */
    public org.joda.time.DateTime getUntil() {
        return this.until;
    }

    /**
     * Setter for <code>tasks.tasks_recurrences.until</code>.
     */
    public void setUntil(org.joda.time.DateTime until) {
        this.until = until;
    }

    /**
     * Getter for <code>tasks.tasks_recurrences.rule</code>.
     */
    public java.lang.String getRule() {
        return this.rule;
    }

    /**
     * Setter for <code>tasks.tasks_recurrences.rule</code>.
     */
    public void setRule(java.lang.String rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        java.lang.StringBuilder sb = new java.lang.StringBuilder("TasksRecurrences (");

        sb.append(taskId);
        sb.append(", ").append(start);
        sb.append(", ").append(until);
        sb.append(", ").append(rule);

        sb.append(")");
        return sb.toString();
    }
}

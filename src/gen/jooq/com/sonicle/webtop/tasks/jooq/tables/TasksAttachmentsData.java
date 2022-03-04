/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.tasks.jooq.tables;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TasksAttachmentsData extends org.jooq.impl.TableImpl<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsDataRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>tasks.tasks_attachments_data</code>
     */
    public static final TasksAttachmentsData TASKS_ATTACHMENTS_DATA = new TasksAttachmentsData();

    /**
     * The class holding records for this type
     */
    @java.lang.Override
    public java.lang.Class<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsDataRecord> getRecordType() {
        return com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsDataRecord.class;
    }

    /**
     * The column <code>tasks.tasks_attachments_data.task_attachment_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsDataRecord, java.lang.String> TASK_ATTACHMENT_ID = createField(org.jooq.impl.DSL.name("task_attachment_id"), org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>tasks.tasks_attachments_data.bytes</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsDataRecord, byte[]> BYTES = createField(org.jooq.impl.DSL.name("bytes"), org.jooq.impl.SQLDataType.BLOB.nullable(false), this, "");

    private TasksAttachmentsData(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsDataRecord> aliased) {
        this(alias, aliased, null);
    }

    private TasksAttachmentsData(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsDataRecord> aliased, org.jooq.Field<?>[] parameters) {
        super(alias, null, aliased, parameters, org.jooq.impl.DSL.comment(""), org.jooq.TableOptions.table());
    }

    /**
     * Create an aliased <code>tasks.tasks_attachments_data</code> table reference
     */
    public TasksAttachmentsData(java.lang.String alias) {
        this(org.jooq.impl.DSL.name(alias), TASKS_ATTACHMENTS_DATA);
    }

    /**
     * Create an aliased <code>tasks.tasks_attachments_data</code> table reference
     */
    public TasksAttachmentsData(org.jooq.Name alias) {
        this(alias, TASKS_ATTACHMENTS_DATA);
    }

    /**
     * Create a <code>tasks.tasks_attachments_data</code> table reference
     */
    public TasksAttachmentsData() {
        this(org.jooq.impl.DSL.name("tasks_attachments_data"), null);
    }

    public <O extends org.jooq.Record> TasksAttachmentsData(org.jooq.Table<O> child, org.jooq.ForeignKey<O, com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsDataRecord> key) {
        super(child, key, TASKS_ATTACHMENTS_DATA);
    }

    @java.lang.Override
    public org.jooq.Schema getSchema() {
        return com.sonicle.webtop.tasks.jooq.Tasks.TASKS;
    }

    @java.lang.Override
    public org.jooq.UniqueKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsDataRecord> getPrimaryKey() {
        return com.sonicle.webtop.tasks.jooq.Keys.TASKS_ATTACHMENTS_DATA_PKEY;
    }

    @java.lang.Override
    public java.util.List<org.jooq.UniqueKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsDataRecord>> getKeys() {
        return java.util.Arrays.<org.jooq.UniqueKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsDataRecord>>asList(com.sonicle.webtop.tasks.jooq.Keys.TASKS_ATTACHMENTS_DATA_PKEY);
    }

    @java.lang.Override
    public java.util.List<org.jooq.ForeignKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsDataRecord, ?>> getReferences() {
        return java.util.Arrays.<org.jooq.ForeignKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsDataRecord, ?>>asList(com.sonicle.webtop.tasks.jooq.Keys.TASKS_ATTACHMENTS_DATA__TASKS_ATTACHMENTS_DATA_TASK_ATTACHMENT_ID_FKEY);
    }

    private transient com.sonicle.webtop.tasks.jooq.tables.TasksAttachments _tasksAttachments;

    public com.sonicle.webtop.tasks.jooq.tables.TasksAttachments tasksAttachments() {
        if (_tasksAttachments == null)
            _tasksAttachments = new com.sonicle.webtop.tasks.jooq.tables.TasksAttachments(this, com.sonicle.webtop.tasks.jooq.Keys.TASKS_ATTACHMENTS_DATA__TASKS_ATTACHMENTS_DATA_TASK_ATTACHMENT_ID_FKEY);

        return _tasksAttachments;
    }

    @java.lang.Override
    public TasksAttachmentsData as(java.lang.String alias) {
        return new TasksAttachmentsData(org.jooq.impl.DSL.name(alias), this);
    }

    @java.lang.Override
    public TasksAttachmentsData as(org.jooq.Name alias) {
        return new TasksAttachmentsData(alias, this);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public TasksAttachmentsData rename(java.lang.String name) {
        return new TasksAttachmentsData(org.jooq.impl.DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public TasksAttachmentsData rename(org.jooq.Name name) {
        return new TasksAttachmentsData(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @java.lang.Override
    public org.jooq.Row2<java.lang.String, byte[]> fieldsRow() {
        return (org.jooq.Row2) super.fieldsRow();
    }
}

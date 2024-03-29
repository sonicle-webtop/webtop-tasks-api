/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.tasks.jooq.tables;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TasksAttachments extends org.jooq.impl.TableImpl<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>tasks.tasks_attachments</code>
     */
    public static final TasksAttachments TASKS_ATTACHMENTS = new TasksAttachments();

    /**
     * The class holding records for this type
     */
    @java.lang.Override
    public java.lang.Class<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsRecord> getRecordType() {
        return com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsRecord.class;
    }

    /**
     * The column <code>tasks.tasks_attachments.task_attachment_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsRecord, java.lang.String> TASK_ATTACHMENT_ID = createField(org.jooq.impl.DSL.name("task_attachment_id"), org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>tasks.tasks_attachments.task_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsRecord, java.lang.String> TASK_ID = createField(org.jooq.impl.DSL.name("task_id"), org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>tasks.tasks_attachments.revision_timestamp</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsRecord, org.joda.time.DateTime> REVISION_TIMESTAMP = createField(org.jooq.impl.DSL.name("revision_timestamp"), org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false), this, "", new com.sonicle.jooq.jsr310.OffsetDateTimeJodaConverter());

    /**
     * The column <code>tasks.tasks_attachments.revision_sequence</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsRecord, java.lang.Short> REVISION_SEQUENCE = createField(org.jooq.impl.DSL.name("revision_sequence"), org.jooq.impl.SQLDataType.SMALLINT.nullable(false), this, "");

    /**
     * The column <code>tasks.tasks_attachments.filename</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsRecord, java.lang.String> FILENAME = createField(org.jooq.impl.DSL.name("filename"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>tasks.tasks_attachments.size</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsRecord, java.lang.Long> SIZE = createField(org.jooq.impl.DSL.name("size"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>tasks.tasks_attachments.media_type</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsRecord, java.lang.String> MEDIA_TYPE = createField(org.jooq.impl.DSL.name("media_type"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    private TasksAttachments(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsRecord> aliased) {
        this(alias, aliased, null);
    }

    private TasksAttachments(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsRecord> aliased, org.jooq.Field<?>[] parameters) {
        super(alias, null, aliased, parameters, org.jooq.impl.DSL.comment(""), org.jooq.TableOptions.table());
    }

    /**
     * Create an aliased <code>tasks.tasks_attachments</code> table reference
     */
    public TasksAttachments(java.lang.String alias) {
        this(org.jooq.impl.DSL.name(alias), TASKS_ATTACHMENTS);
    }

    /**
     * Create an aliased <code>tasks.tasks_attachments</code> table reference
     */
    public TasksAttachments(org.jooq.Name alias) {
        this(alias, TASKS_ATTACHMENTS);
    }

    /**
     * Create a <code>tasks.tasks_attachments</code> table reference
     */
    public TasksAttachments() {
        this(org.jooq.impl.DSL.name("tasks_attachments"), null);
    }

    public <O extends org.jooq.Record> TasksAttachments(org.jooq.Table<O> child, org.jooq.ForeignKey<O, com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsRecord> key) {
        super(child, key, TASKS_ATTACHMENTS);
    }

    @java.lang.Override
    public org.jooq.Schema getSchema() {
        return com.sonicle.webtop.tasks.jooq.Tasks.TASKS;
    }

    @java.lang.Override
    public java.util.List<org.jooq.Index> getIndexes() {
        return java.util.Arrays.<org.jooq.Index>asList(com.sonicle.webtop.tasks.jooq.Indexes.TASKS_ATTACHMENTS_AK1);
    }

    @java.lang.Override
    public org.jooq.UniqueKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsRecord> getPrimaryKey() {
        return com.sonicle.webtop.tasks.jooq.Keys.TASKS_ATTACHMENTS_PKEY;
    }

    @java.lang.Override
    public java.util.List<org.jooq.UniqueKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsRecord>> getKeys() {
        return java.util.Arrays.<org.jooq.UniqueKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsRecord>>asList(com.sonicle.webtop.tasks.jooq.Keys.TASKS_ATTACHMENTS_PKEY);
    }

    @java.lang.Override
    public java.util.List<org.jooq.ForeignKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsRecord, ?>> getReferences() {
        return java.util.Arrays.<org.jooq.ForeignKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksAttachmentsRecord, ?>>asList(com.sonicle.webtop.tasks.jooq.Keys.TASKS_ATTACHMENTS__TASKS_ATTACHMENTS_TASK_ID_FKEY);
    }

    private transient com.sonicle.webtop.tasks.jooq.tables.Tasks _tasks;

    public com.sonicle.webtop.tasks.jooq.tables.Tasks tasks() {
        if (_tasks == null)
            _tasks = new com.sonicle.webtop.tasks.jooq.tables.Tasks(this, com.sonicle.webtop.tasks.jooq.Keys.TASKS_ATTACHMENTS__TASKS_ATTACHMENTS_TASK_ID_FKEY);

        return _tasks;
    }

    @java.lang.Override
    public TasksAttachments as(java.lang.String alias) {
        return new TasksAttachments(org.jooq.impl.DSL.name(alias), this);
    }

    @java.lang.Override
    public TasksAttachments as(org.jooq.Name alias) {
        return new TasksAttachments(alias, this);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public TasksAttachments rename(java.lang.String name) {
        return new TasksAttachments(org.jooq.impl.DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public TasksAttachments rename(org.jooq.Name name) {
        return new TasksAttachments(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @java.lang.Override
    public org.jooq.Row7<java.lang.String, java.lang.String, org.joda.time.DateTime, java.lang.Short, java.lang.String, java.lang.Long, java.lang.String> fieldsRow() {
        return (org.jooq.Row7) super.fieldsRow();
    }
}

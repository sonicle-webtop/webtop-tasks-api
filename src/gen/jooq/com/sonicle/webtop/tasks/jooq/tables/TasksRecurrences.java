/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.tasks.jooq.tables;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TasksRecurrences extends org.jooq.impl.TableImpl<com.sonicle.webtop.tasks.jooq.tables.records.TasksRecurrencesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>tasks.tasks_recurrences</code>
     */
    public static final TasksRecurrences TASKS_RECURRENCES = new TasksRecurrences();

    /**
     * The class holding records for this type
     */
    @java.lang.Override
    public java.lang.Class<com.sonicle.webtop.tasks.jooq.tables.records.TasksRecurrencesRecord> getRecordType() {
        return com.sonicle.webtop.tasks.jooq.tables.records.TasksRecurrencesRecord.class;
    }

    /**
     * The column <code>tasks.tasks_recurrences.task_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.tasks.jooq.tables.records.TasksRecurrencesRecord, java.lang.String> TASK_ID = createField(org.jooq.impl.DSL.name("task_id"), org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>tasks.tasks_recurrences.start</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.tasks.jooq.tables.records.TasksRecurrencesRecord, org.joda.time.DateTime> START = createField(org.jooq.impl.DSL.name("start"), org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false), this, "", new com.sonicle.jooq.jsr310.OffsetDateTimeJodaConverter());

    /**
     * The column <code>tasks.tasks_recurrences.until</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.tasks.jooq.tables.records.TasksRecurrencesRecord, org.joda.time.DateTime> UNTIL = createField(org.jooq.impl.DSL.name("until"), org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false), this, "", new com.sonicle.jooq.jsr310.OffsetDateTimeJodaConverter());

    /**
     * The column <code>tasks.tasks_recurrences.rule</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.tasks.jooq.tables.records.TasksRecurrencesRecord, java.lang.String> RULE = createField(org.jooq.impl.DSL.name("rule"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    private TasksRecurrences(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.tasks.jooq.tables.records.TasksRecurrencesRecord> aliased) {
        this(alias, aliased, null);
    }

    private TasksRecurrences(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.tasks.jooq.tables.records.TasksRecurrencesRecord> aliased, org.jooq.Field<?>[] parameters) {
        super(alias, null, aliased, parameters, org.jooq.impl.DSL.comment(""), org.jooq.TableOptions.table());
    }

    /**
     * Create an aliased <code>tasks.tasks_recurrences</code> table reference
     */
    public TasksRecurrences(java.lang.String alias) {
        this(org.jooq.impl.DSL.name(alias), TASKS_RECURRENCES);
    }

    /**
     * Create an aliased <code>tasks.tasks_recurrences</code> table reference
     */
    public TasksRecurrences(org.jooq.Name alias) {
        this(alias, TASKS_RECURRENCES);
    }

    /**
     * Create a <code>tasks.tasks_recurrences</code> table reference
     */
    public TasksRecurrences() {
        this(org.jooq.impl.DSL.name("tasks_recurrences"), null);
    }

    public <O extends org.jooq.Record> TasksRecurrences(org.jooq.Table<O> child, org.jooq.ForeignKey<O, com.sonicle.webtop.tasks.jooq.tables.records.TasksRecurrencesRecord> key) {
        super(child, key, TASKS_RECURRENCES);
    }

    @java.lang.Override
    public org.jooq.Schema getSchema() {
        return com.sonicle.webtop.tasks.jooq.Tasks.TASKS;
    }

    @java.lang.Override
    public java.util.List<org.jooq.Index> getIndexes() {
        return java.util.Arrays.<org.jooq.Index>asList(com.sonicle.webtop.tasks.jooq.Indexes.TASKS_RECURRENCES_AK1);
    }

    @java.lang.Override
    public org.jooq.UniqueKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksRecurrencesRecord> getPrimaryKey() {
        return com.sonicle.webtop.tasks.jooq.Keys.TASKS_RECURRENCES_PKEY;
    }

    @java.lang.Override
    public java.util.List<org.jooq.UniqueKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksRecurrencesRecord>> getKeys() {
        return java.util.Arrays.<org.jooq.UniqueKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksRecurrencesRecord>>asList(com.sonicle.webtop.tasks.jooq.Keys.TASKS_RECURRENCES_PKEY);
    }

    @java.lang.Override
    public java.util.List<org.jooq.ForeignKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksRecurrencesRecord, ?>> getReferences() {
        return java.util.Arrays.<org.jooq.ForeignKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksRecurrencesRecord, ?>>asList(com.sonicle.webtop.tasks.jooq.Keys.TASKS_RECURRENCES__TASKS_RECURRENCES_TASK_ID_FKEY);
    }

    private transient com.sonicle.webtop.tasks.jooq.tables.Tasks _tasks;

    public com.sonicle.webtop.tasks.jooq.tables.Tasks tasks() {
        if (_tasks == null)
            _tasks = new com.sonicle.webtop.tasks.jooq.tables.Tasks(this, com.sonicle.webtop.tasks.jooq.Keys.TASKS_RECURRENCES__TASKS_RECURRENCES_TASK_ID_FKEY);

        return _tasks;
    }

    @java.lang.Override
    public TasksRecurrences as(java.lang.String alias) {
        return new TasksRecurrences(org.jooq.impl.DSL.name(alias), this);
    }

    @java.lang.Override
    public TasksRecurrences as(org.jooq.Name alias) {
        return new TasksRecurrences(alias, this);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public TasksRecurrences rename(java.lang.String name) {
        return new TasksRecurrences(org.jooq.impl.DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public TasksRecurrences rename(org.jooq.Name name) {
        return new TasksRecurrences(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @java.lang.Override
    public org.jooq.Row4<java.lang.String, org.joda.time.DateTime, org.joda.time.DateTime, java.lang.String> fieldsRow() {
        return (org.jooq.Row4) super.fieldsRow();
    }
}

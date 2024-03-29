/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.tasks.jooq.tables;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TasksIcalendars extends org.jooq.impl.TableImpl<com.sonicle.webtop.tasks.jooq.tables.records.TasksIcalendarsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>tasks.tasks_icalendars</code>
     */
    public static final TasksIcalendars TASKS_ICALENDARS = new TasksIcalendars();

    /**
     * The class holding records for this type
     */
    @java.lang.Override
    public java.lang.Class<com.sonicle.webtop.tasks.jooq.tables.records.TasksIcalendarsRecord> getRecordType() {
        return com.sonicle.webtop.tasks.jooq.tables.records.TasksIcalendarsRecord.class;
    }

    /**
     * The column <code>tasks.tasks_icalendars.task_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.tasks.jooq.tables.records.TasksIcalendarsRecord, java.lang.String> TASK_ID = createField(org.jooq.impl.DSL.name("task_id"), org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>tasks.tasks_icalendars.raw_data</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.tasks.jooq.tables.records.TasksIcalendarsRecord, java.lang.String> RAW_DATA = createField(org.jooq.impl.DSL.name("raw_data"), org.jooq.impl.SQLDataType.CLOB, this, "");

    private TasksIcalendars(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.tasks.jooq.tables.records.TasksIcalendarsRecord> aliased) {
        this(alias, aliased, null);
    }

    private TasksIcalendars(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.tasks.jooq.tables.records.TasksIcalendarsRecord> aliased, org.jooq.Field<?>[] parameters) {
        super(alias, null, aliased, parameters, org.jooq.impl.DSL.comment(""), org.jooq.TableOptions.table());
    }

    /**
     * Create an aliased <code>tasks.tasks_icalendars</code> table reference
     */
    public TasksIcalendars(java.lang.String alias) {
        this(org.jooq.impl.DSL.name(alias), TASKS_ICALENDARS);
    }

    /**
     * Create an aliased <code>tasks.tasks_icalendars</code> table reference
     */
    public TasksIcalendars(org.jooq.Name alias) {
        this(alias, TASKS_ICALENDARS);
    }

    /**
     * Create a <code>tasks.tasks_icalendars</code> table reference
     */
    public TasksIcalendars() {
        this(org.jooq.impl.DSL.name("tasks_icalendars"), null);
    }

    public <O extends org.jooq.Record> TasksIcalendars(org.jooq.Table<O> child, org.jooq.ForeignKey<O, com.sonicle.webtop.tasks.jooq.tables.records.TasksIcalendarsRecord> key) {
        super(child, key, TASKS_ICALENDARS);
    }

    @java.lang.Override
    public org.jooq.Schema getSchema() {
        return com.sonicle.webtop.tasks.jooq.Tasks.TASKS;
    }

    @java.lang.Override
    public org.jooq.UniqueKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksIcalendarsRecord> getPrimaryKey() {
        return com.sonicle.webtop.tasks.jooq.Keys.TASKS_ICALENDARS_PKEY;
    }

    @java.lang.Override
    public java.util.List<org.jooq.UniqueKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksIcalendarsRecord>> getKeys() {
        return java.util.Arrays.<org.jooq.UniqueKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksIcalendarsRecord>>asList(com.sonicle.webtop.tasks.jooq.Keys.TASKS_ICALENDARS_PKEY);
    }

    @java.lang.Override
    public java.util.List<org.jooq.ForeignKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksIcalendarsRecord, ?>> getReferences() {
        return java.util.Arrays.<org.jooq.ForeignKey<com.sonicle.webtop.tasks.jooq.tables.records.TasksIcalendarsRecord, ?>>asList(com.sonicle.webtop.tasks.jooq.Keys.TASKS_ICALENDARS__TASKS_ICALENDARS_TASK_ID_FKEY);
    }

    private transient com.sonicle.webtop.tasks.jooq.tables.Tasks _tasks;

    public com.sonicle.webtop.tasks.jooq.tables.Tasks tasks() {
        if (_tasks == null)
            _tasks = new com.sonicle.webtop.tasks.jooq.tables.Tasks(this, com.sonicle.webtop.tasks.jooq.Keys.TASKS_ICALENDARS__TASKS_ICALENDARS_TASK_ID_FKEY);

        return _tasks;
    }

    @java.lang.Override
    public TasksIcalendars as(java.lang.String alias) {
        return new TasksIcalendars(org.jooq.impl.DSL.name(alias), this);
    }

    @java.lang.Override
    public TasksIcalendars as(org.jooq.Name alias) {
        return new TasksIcalendars(alias, this);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public TasksIcalendars rename(java.lang.String name) {
        return new TasksIcalendars(org.jooq.impl.DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public TasksIcalendars rename(org.jooq.Name name) {
        return new TasksIcalendars(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @java.lang.Override
    public org.jooq.Row2<java.lang.String, java.lang.String> fieldsRow() {
        return (org.jooq.Row2) super.fieldsRow();
    }
}

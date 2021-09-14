/**
 * This class is generated by jOOQ
 */
package com.sonicle.webtop.tasks.jooq.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.3"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TasksIcalendarsRecord extends org.jooq.impl.UpdatableRecordImpl<com.sonicle.webtop.tasks.jooq.tables.records.TasksIcalendarsRecord> implements org.jooq.Record2<java.lang.String, java.lang.String> {

	private static final long serialVersionUID = 1293638803;

	/**
	 * Setter for <code>tasks.tasks_icalendars.task_id</code>.
	 */
	public void setTaskId(java.lang.String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>tasks.tasks_icalendars.task_id</code>.
	 */
	public java.lang.String getTaskId() {
		return (java.lang.String) getValue(0);
	}

	/**
	 * Setter for <code>tasks.tasks_icalendars.raw_data</code>.
	 */
	public void setRawData(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>tasks.tasks_icalendars.raw_data</code>.
	 */
	public java.lang.String getRawData() {
		return (java.lang.String) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.String> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field1() {
		return com.sonicle.webtop.tasks.jooq.tables.TasksIcalendars.TASKS_ICALENDARS.TASK_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return com.sonicle.webtop.tasks.jooq.tables.TasksIcalendars.TASKS_ICALENDARS.RAW_DATA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value1() {
		return getTaskId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getRawData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TasksIcalendarsRecord value1(java.lang.String value) {
		setTaskId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TasksIcalendarsRecord value2(java.lang.String value) {
		setRawData(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TasksIcalendarsRecord values(java.lang.String value1, java.lang.String value2) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached TasksIcalendarsRecord
	 */
	public TasksIcalendarsRecord() {
		super(com.sonicle.webtop.tasks.jooq.tables.TasksIcalendars.TASKS_ICALENDARS);
	}

	/**
	 * Create a detached, initialised TasksIcalendarsRecord
	 */
	public TasksIcalendarsRecord(java.lang.String taskId, java.lang.String rawData) {
		super(com.sonicle.webtop.tasks.jooq.tables.TasksIcalendars.TASKS_ICALENDARS);

		setValue(0, taskId);
		setValue(1, rawData);
	}
}

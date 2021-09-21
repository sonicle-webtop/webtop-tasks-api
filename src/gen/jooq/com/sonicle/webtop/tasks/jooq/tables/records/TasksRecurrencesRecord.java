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
public class TasksRecurrencesRecord extends org.jooq.impl.UpdatableRecordImpl<com.sonicle.webtop.tasks.jooq.tables.records.TasksRecurrencesRecord> implements org.jooq.Record4<java.lang.String, org.joda.time.DateTime, org.joda.time.DateTime, java.lang.String> {

	private static final long serialVersionUID = 570322742;

	/**
	 * Setter for <code>tasks.tasks_recurrences.task_id</code>.
	 */
	public void setTaskId(java.lang.String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>tasks.tasks_recurrences.task_id</code>.
	 */
	public java.lang.String getTaskId() {
		return (java.lang.String) getValue(0);
	}

	/**
	 * Setter for <code>tasks.tasks_recurrences.start</code>.
	 */
	public void setStart(org.joda.time.DateTime value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>tasks.tasks_recurrences.start</code>.
	 */
	public org.joda.time.DateTime getStart() {
		return (org.joda.time.DateTime) getValue(1);
	}

	/**
	 * Setter for <code>tasks.tasks_recurrences.until</code>.
	 */
	public void setUntil(org.joda.time.DateTime value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>tasks.tasks_recurrences.until</code>.
	 */
	public org.joda.time.DateTime getUntil() {
		return (org.joda.time.DateTime) getValue(2);
	}

	/**
	 * Setter for <code>tasks.tasks_recurrences.rule</code>.
	 */
	public void setRule(java.lang.String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>tasks.tasks_recurrences.rule</code>.
	 */
	public java.lang.String getRule() {
		return (java.lang.String) getValue(3);
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
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<java.lang.String, org.joda.time.DateTime, org.joda.time.DateTime, java.lang.String> fieldsRow() {
		return (org.jooq.Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<java.lang.String, org.joda.time.DateTime, org.joda.time.DateTime, java.lang.String> valuesRow() {
		return (org.jooq.Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field1() {
		return com.sonicle.webtop.tasks.jooq.tables.TasksRecurrences.TASKS_RECURRENCES.TASK_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.joda.time.DateTime> field2() {
		return com.sonicle.webtop.tasks.jooq.tables.TasksRecurrences.TASKS_RECURRENCES.START;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.joda.time.DateTime> field3() {
		return com.sonicle.webtop.tasks.jooq.tables.TasksRecurrences.TASKS_RECURRENCES.UNTIL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field4() {
		return com.sonicle.webtop.tasks.jooq.tables.TasksRecurrences.TASKS_RECURRENCES.RULE;
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
	public org.joda.time.DateTime value2() {
		return getStart();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.joda.time.DateTime value3() {
		return getUntil();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value4() {
		return getRule();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TasksRecurrencesRecord value1(java.lang.String value) {
		setTaskId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TasksRecurrencesRecord value2(org.joda.time.DateTime value) {
		setStart(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TasksRecurrencesRecord value3(org.joda.time.DateTime value) {
		setUntil(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TasksRecurrencesRecord value4(java.lang.String value) {
		setRule(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TasksRecurrencesRecord values(java.lang.String value1, org.joda.time.DateTime value2, org.joda.time.DateTime value3, java.lang.String value4) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached TasksRecurrencesRecord
	 */
	public TasksRecurrencesRecord() {
		super(com.sonicle.webtop.tasks.jooq.tables.TasksRecurrences.TASKS_RECURRENCES);
	}

	/**
	 * Create a detached, initialised TasksRecurrencesRecord
	 */
	public TasksRecurrencesRecord(java.lang.String taskId, org.joda.time.DateTime start, org.joda.time.DateTime until, java.lang.String rule) {
		super(com.sonicle.webtop.tasks.jooq.tables.TasksRecurrences.TASKS_RECURRENCES);

		setValue(0, taskId);
		setValue(1, start);
		setValue(2, until);
		setValue(3, rule);
	}
}
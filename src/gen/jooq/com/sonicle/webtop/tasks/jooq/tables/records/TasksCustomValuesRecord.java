/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.tasks.jooq.tables.records;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TasksCustomValuesRecord extends org.jooq.impl.UpdatableRecordImpl<TasksCustomValuesRecord> implements org.jooq.Record7<java.lang.String, java.lang.String, java.lang.String, java.lang.Double, java.lang.Boolean, org.joda.time.DateTime, java.lang.String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>tasks.tasks_custom_values.task_id</code>.
     */
    public void setTaskId(java.lang.String value) {
        set(0, value);
    }

    /**
     * Getter for <code>tasks.tasks_custom_values.task_id</code>.
     */
    public java.lang.String getTaskId() {
        return (java.lang.String) get(0);
    }

    /**
     * Setter for <code>tasks.tasks_custom_values.custom_field_id</code>.
     */
    public void setCustomFieldId(java.lang.String value) {
        set(1, value);
    }

    /**
     * Getter for <code>tasks.tasks_custom_values.custom_field_id</code>.
     */
    public java.lang.String getCustomFieldId() {
        return (java.lang.String) get(1);
    }

    /**
     * Setter for <code>tasks.tasks_custom_values.string_value</code>.
     */
    public void setStringValue(java.lang.String value) {
        set(2, value);
    }

    /**
     * Getter for <code>tasks.tasks_custom_values.string_value</code>.
     */
    public java.lang.String getStringValue() {
        return (java.lang.String) get(2);
    }

    /**
     * Setter for <code>tasks.tasks_custom_values.number_value</code>.
     */
    public void setNumberValue(java.lang.Double value) {
        set(3, value);
    }

    /**
     * Getter for <code>tasks.tasks_custom_values.number_value</code>.
     */
    public java.lang.Double getNumberValue() {
        return (java.lang.Double) get(3);
    }

    /**
     * Setter for <code>tasks.tasks_custom_values.boolean_value</code>.
     */
    public void setBooleanValue(java.lang.Boolean value) {
        set(4, value);
    }

    /**
     * Getter for <code>tasks.tasks_custom_values.boolean_value</code>.
     */
    public java.lang.Boolean getBooleanValue() {
        return (java.lang.Boolean) get(4);
    }

    /**
     * Setter for <code>tasks.tasks_custom_values.date_value</code>.
     */
    public void setDateValue(org.joda.time.DateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>tasks.tasks_custom_values.date_value</code>.
     */
    public org.joda.time.DateTime getDateValue() {
        return (org.joda.time.DateTime) get(5);
    }

    /**
     * Setter for <code>tasks.tasks_custom_values.text_value</code>.
     */
    public void setTextValue(java.lang.String value) {
        set(6, value);
    }

    /**
     * Getter for <code>tasks.tasks_custom_values.text_value</code>.
     */
    public java.lang.String getTextValue() {
        return (java.lang.String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @java.lang.Override
    public org.jooq.Record2<java.lang.String, java.lang.String> key() {
        return (org.jooq.Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @java.lang.Override
    public org.jooq.Row7<java.lang.String, java.lang.String, java.lang.String, java.lang.Double, java.lang.Boolean, org.joda.time.DateTime, java.lang.String> fieldsRow() {
        return (org.jooq.Row7) super.fieldsRow();
    }

    @java.lang.Override
    public org.jooq.Row7<java.lang.String, java.lang.String, java.lang.String, java.lang.Double, java.lang.Boolean, org.joda.time.DateTime, java.lang.String> valuesRow() {
        return (org.jooq.Row7) super.valuesRow();
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.String> field1() {
        return com.sonicle.webtop.tasks.jooq.tables.TasksCustomValues.TASKS_CUSTOM_VALUES.TASK_ID;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.String> field2() {
        return com.sonicle.webtop.tasks.jooq.tables.TasksCustomValues.TASKS_CUSTOM_VALUES.CUSTOM_FIELD_ID;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.String> field3() {
        return com.sonicle.webtop.tasks.jooq.tables.TasksCustomValues.TASKS_CUSTOM_VALUES.STRING_VALUE;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Double> field4() {
        return com.sonicle.webtop.tasks.jooq.tables.TasksCustomValues.TASKS_CUSTOM_VALUES.NUMBER_VALUE;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Boolean> field5() {
        return com.sonicle.webtop.tasks.jooq.tables.TasksCustomValues.TASKS_CUSTOM_VALUES.BOOLEAN_VALUE;
    }

    @java.lang.Override
    public org.jooq.Field<org.joda.time.DateTime> field6() {
        return com.sonicle.webtop.tasks.jooq.tables.TasksCustomValues.TASKS_CUSTOM_VALUES.DATE_VALUE;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.String> field7() {
        return com.sonicle.webtop.tasks.jooq.tables.TasksCustomValues.TASKS_CUSTOM_VALUES.TEXT_VALUE;
    }

    @java.lang.Override
    public java.lang.String component1() {
        return getTaskId();
    }

    @java.lang.Override
    public java.lang.String component2() {
        return getCustomFieldId();
    }

    @java.lang.Override
    public java.lang.String component3() {
        return getStringValue();
    }

    @java.lang.Override
    public java.lang.Double component4() {
        return getNumberValue();
    }

    @java.lang.Override
    public java.lang.Boolean component5() {
        return getBooleanValue();
    }

    @java.lang.Override
    public org.joda.time.DateTime component6() {
        return getDateValue();
    }

    @java.lang.Override
    public java.lang.String component7() {
        return getTextValue();
    }

    @java.lang.Override
    public java.lang.String value1() {
        return getTaskId();
    }

    @java.lang.Override
    public java.lang.String value2() {
        return getCustomFieldId();
    }

    @java.lang.Override
    public java.lang.String value3() {
        return getStringValue();
    }

    @java.lang.Override
    public java.lang.Double value4() {
        return getNumberValue();
    }

    @java.lang.Override
    public java.lang.Boolean value5() {
        return getBooleanValue();
    }

    @java.lang.Override
    public org.joda.time.DateTime value6() {
        return getDateValue();
    }

    @java.lang.Override
    public java.lang.String value7() {
        return getTextValue();
    }

    @java.lang.Override
    public TasksCustomValuesRecord value1(java.lang.String value) {
        setTaskId(value);
        return this;
    }

    @java.lang.Override
    public TasksCustomValuesRecord value2(java.lang.String value) {
        setCustomFieldId(value);
        return this;
    }

    @java.lang.Override
    public TasksCustomValuesRecord value3(java.lang.String value) {
        setStringValue(value);
        return this;
    }

    @java.lang.Override
    public TasksCustomValuesRecord value4(java.lang.Double value) {
        setNumberValue(value);
        return this;
    }

    @java.lang.Override
    public TasksCustomValuesRecord value5(java.lang.Boolean value) {
        setBooleanValue(value);
        return this;
    }

    @java.lang.Override
    public TasksCustomValuesRecord value6(org.joda.time.DateTime value) {
        setDateValue(value);
        return this;
    }

    @java.lang.Override
    public TasksCustomValuesRecord value7(java.lang.String value) {
        setTextValue(value);
        return this;
    }

    @java.lang.Override
    public TasksCustomValuesRecord values(java.lang.String value1, java.lang.String value2, java.lang.String value3, java.lang.Double value4, java.lang.Boolean value5, org.joda.time.DateTime value6, java.lang.String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TasksCustomValuesRecord
     */
    public TasksCustomValuesRecord() {
        super(com.sonicle.webtop.tasks.jooq.tables.TasksCustomValues.TASKS_CUSTOM_VALUES);
    }

    /**
     * Create a detached, initialised TasksCustomValuesRecord
     */
    public TasksCustomValuesRecord(java.lang.String taskId, java.lang.String customFieldId, java.lang.String stringValue, java.lang.Double numberValue, java.lang.Boolean booleanValue, org.joda.time.DateTime dateValue, java.lang.String textValue) {
        super(com.sonicle.webtop.tasks.jooq.tables.TasksCustomValues.TASKS_CUSTOM_VALUES);

        setTaskId(taskId);
        setCustomFieldId(customFieldId);
        setStringValue(stringValue);
        setNumberValue(numberValue);
        setBooleanValue(booleanValue);
        setDateValue(dateValue);
        setTextValue(textValue);
    }
}

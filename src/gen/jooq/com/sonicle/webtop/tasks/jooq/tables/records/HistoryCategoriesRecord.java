/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.tasks.jooq.tables.records;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class HistoryCategoriesRecord extends org.jooq.impl.UpdatableRecordImpl<HistoryCategoriesRecord> implements org.jooq.Record6<java.lang.Long, java.lang.String, java.lang.String, java.lang.Integer, org.joda.time.DateTime, java.lang.String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>tasks.history_categories.id</code>.
     */
    public void setId(java.lang.Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>tasks.history_categories.id</code>.
     */
    public java.lang.Long getId() {
        return (java.lang.Long) get(0);
    }

    /**
     * Setter for <code>tasks.history_categories.domain_id</code>.
     */
    public void setDomainId(java.lang.String value) {
        set(1, value);
    }

    /**
     * Getter for <code>tasks.history_categories.domain_id</code>.
     */
    public java.lang.String getDomainId() {
        return (java.lang.String) get(1);
    }

    /**
     * Setter for <code>tasks.history_categories.user_id</code>.
     */
    public void setUserId(java.lang.String value) {
        set(2, value);
    }

    /**
     * Getter for <code>tasks.history_categories.user_id</code>.
     */
    public java.lang.String getUserId() {
        return (java.lang.String) get(2);
    }

    /**
     * Setter for <code>tasks.history_categories.category_id</code>.
     */
    public void setCategoryId(java.lang.Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>tasks.history_categories.category_id</code>.
     */
    public java.lang.Integer getCategoryId() {
        return (java.lang.Integer) get(3);
    }

    /**
     * Setter for <code>tasks.history_categories.change_timestamp</code>.
     */
    public void setChangeTimestamp(org.joda.time.DateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>tasks.history_categories.change_timestamp</code>.
     */
    public org.joda.time.DateTime getChangeTimestamp() {
        return (org.joda.time.DateTime) get(4);
    }

    /**
     * Setter for <code>tasks.history_categories.change_type</code>.
     */
    public void setChangeType(java.lang.String value) {
        set(5, value);
    }

    /**
     * Getter for <code>tasks.history_categories.change_type</code>.
     */
    public java.lang.String getChangeType() {
        return (java.lang.String) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @java.lang.Override
    public org.jooq.Record1<java.lang.Long> key() {
        return (org.jooq.Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @java.lang.Override
    public org.jooq.Row6<java.lang.Long, java.lang.String, java.lang.String, java.lang.Integer, org.joda.time.DateTime, java.lang.String> fieldsRow() {
        return (org.jooq.Row6) super.fieldsRow();
    }

    @java.lang.Override
    public org.jooq.Row6<java.lang.Long, java.lang.String, java.lang.String, java.lang.Integer, org.joda.time.DateTime, java.lang.String> valuesRow() {
        return (org.jooq.Row6) super.valuesRow();
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Long> field1() {
        return com.sonicle.webtop.tasks.jooq.tables.HistoryCategories.HISTORY_CATEGORIES.ID;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.String> field2() {
        return com.sonicle.webtop.tasks.jooq.tables.HistoryCategories.HISTORY_CATEGORIES.DOMAIN_ID;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.String> field3() {
        return com.sonicle.webtop.tasks.jooq.tables.HistoryCategories.HISTORY_CATEGORIES.USER_ID;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Integer> field4() {
        return com.sonicle.webtop.tasks.jooq.tables.HistoryCategories.HISTORY_CATEGORIES.CATEGORY_ID;
    }

    @java.lang.Override
    public org.jooq.Field<org.joda.time.DateTime> field5() {
        return com.sonicle.webtop.tasks.jooq.tables.HistoryCategories.HISTORY_CATEGORIES.CHANGE_TIMESTAMP;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.String> field6() {
        return com.sonicle.webtop.tasks.jooq.tables.HistoryCategories.HISTORY_CATEGORIES.CHANGE_TYPE;
    }

    @java.lang.Override
    public java.lang.Long component1() {
        return getId();
    }

    @java.lang.Override
    public java.lang.String component2() {
        return getDomainId();
    }

    @java.lang.Override
    public java.lang.String component3() {
        return getUserId();
    }

    @java.lang.Override
    public java.lang.Integer component4() {
        return getCategoryId();
    }

    @java.lang.Override
    public org.joda.time.DateTime component5() {
        return getChangeTimestamp();
    }

    @java.lang.Override
    public java.lang.String component6() {
        return getChangeType();
    }

    @java.lang.Override
    public java.lang.Long value1() {
        return getId();
    }

    @java.lang.Override
    public java.lang.String value2() {
        return getDomainId();
    }

    @java.lang.Override
    public java.lang.String value3() {
        return getUserId();
    }

    @java.lang.Override
    public java.lang.Integer value4() {
        return getCategoryId();
    }

    @java.lang.Override
    public org.joda.time.DateTime value5() {
        return getChangeTimestamp();
    }

    @java.lang.Override
    public java.lang.String value6() {
        return getChangeType();
    }

    @java.lang.Override
    public HistoryCategoriesRecord value1(java.lang.Long value) {
        setId(value);
        return this;
    }

    @java.lang.Override
    public HistoryCategoriesRecord value2(java.lang.String value) {
        setDomainId(value);
        return this;
    }

    @java.lang.Override
    public HistoryCategoriesRecord value3(java.lang.String value) {
        setUserId(value);
        return this;
    }

    @java.lang.Override
    public HistoryCategoriesRecord value4(java.lang.Integer value) {
        setCategoryId(value);
        return this;
    }

    @java.lang.Override
    public HistoryCategoriesRecord value5(org.joda.time.DateTime value) {
        setChangeTimestamp(value);
        return this;
    }

    @java.lang.Override
    public HistoryCategoriesRecord value6(java.lang.String value) {
        setChangeType(value);
        return this;
    }

    @java.lang.Override
    public HistoryCategoriesRecord values(java.lang.Long value1, java.lang.String value2, java.lang.String value3, java.lang.Integer value4, org.joda.time.DateTime value5, java.lang.String value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached HistoryCategoriesRecord
     */
    public HistoryCategoriesRecord() {
        super(com.sonicle.webtop.tasks.jooq.tables.HistoryCategories.HISTORY_CATEGORIES);
    }

    /**
     * Create a detached, initialised HistoryCategoriesRecord
     */
    public HistoryCategoriesRecord(java.lang.Long id, java.lang.String domainId, java.lang.String userId, java.lang.Integer categoryId, org.joda.time.DateTime changeTimestamp, java.lang.String changeType) {
        super(com.sonicle.webtop.tasks.jooq.tables.HistoryCategories.HISTORY_CATEGORIES);

        setId(id);
        setDomainId(domainId);
        setUserId(userId);
        setCategoryId(categoryId);
        setChangeTimestamp(changeTimestamp);
        setChangeType(changeType);
    }
}

/**
 * This class is generated by jOOQ
 */
package com.sonicle.webtop.tasks.jooq.tables.pojos;

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
public class CategoryProps implements java.io.Serializable {

	private static final long serialVersionUID = 685705130;

	private java.lang.String  domainId;
	private java.lang.String  userId;
	private java.lang.Integer categoryId;
	private java.lang.Boolean hidden;
	private java.lang.String  color;
	private java.lang.String  sync;

	public CategoryProps() {}

	public CategoryProps(
		java.lang.String  domainId,
		java.lang.String  userId,
		java.lang.Integer categoryId,
		java.lang.Boolean hidden,
		java.lang.String  color,
		java.lang.String  sync
	) {
		this.domainId = domainId;
		this.userId = userId;
		this.categoryId = categoryId;
		this.hidden = hidden;
		this.color = color;
		this.sync = sync;
	}

	public java.lang.String getDomainId() {
		return this.domainId;
	}

	public void setDomainId(java.lang.String domainId) {
		this.domainId = domainId;
	}

	public java.lang.String getUserId() {
		return this.userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(java.lang.Integer categoryId) {
		this.categoryId = categoryId;
	}

	public java.lang.Boolean getHidden() {
		return this.hidden;
	}

	public void setHidden(java.lang.Boolean hidden) {
		this.hidden = hidden;
	}

	public java.lang.String getColor() {
		return this.color;
	}

	public void setColor(java.lang.String color) {
		this.color = color;
	}

	public java.lang.String getSync() {
		return this.sync;
	}

	public void setSync(java.lang.String sync) {
		this.sync = sync;
	}
}

package com.akg.catalog.entity;

import javax.persistence.*;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "category_attribute")
public class CategoryAttribute extends BaseEntity {

    @Id
    @Column(name = "ID")
    private int categoryAttributeId;

    @Column(name = "CATEGORY_ID")
    private int categoryId;

    @Column(name = "ATTRIBUTE_ID")
    private int attributeId;

    @Column(name = "ATTRIBUTE_NAME")
    private String attributeName;

    @Column(name = "ATTRIBUTE_VALUE")
    private String attributeValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", insertable = false, updatable = false)
    private Category category;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryAttributeId() {
        return categoryAttributeId;
    }

    public void setCategoryAttributeId(int categoryAttributeId) {
        this.categoryAttributeId = categoryAttributeId;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    @PrePersist
    private void generateId() {
        Integer code = ThreadLocalRandom.current().nextInt(100000, 1000000);
        categoryAttributeId = code;
    }
}

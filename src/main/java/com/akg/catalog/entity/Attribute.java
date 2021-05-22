package com.akg.catalog.entity;

import javax.persistence.*;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "attribute")
public class Attribute extends BaseEntity {

    @Id
    @Column(name = "ID")
    private int attributeId;

    @Column(name = "NAME")
    private String attributeName;

    @Column(name = "DESCRIPTION")
    private String attributeDescription;

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

    public String getAttributeDescription() {
        return attributeDescription;
    }

    public void setAttributeDescription(String attributeDescription) {
        this.attributeDescription = attributeDescription;
    }

    @PrePersist
    private void generateId() {
        if (attributeId == 0) {
            Integer code = ThreadLocalRandom.current().nextInt(100000, 1000000);
            attributeId = code;
        }
    }
}

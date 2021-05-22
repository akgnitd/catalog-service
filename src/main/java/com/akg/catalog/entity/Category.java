package com.akg.catalog.entity;

import javax.persistence.*;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {

    @Id
    @Column(name = "ID")
    private int categoryId;

    @Column(name = "NAME")
    private String categoryName;

    @Column(name = "DESCRIPTION")
    private String categoryDescription;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    @PrePersist
    private void generateId() {
        if (categoryId == 0) {
            Integer code = ThreadLocalRandom.current().nextInt(100000, 1000000);
            categoryId = code;
        }
    }
}

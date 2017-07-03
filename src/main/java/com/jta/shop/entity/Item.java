package com.jta.shop.entity;

import javax.persistence.*;

/**
 * @author azozello
 * @since  03.07.17.
 */

@Entity
@Table(name = "vouchers")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "images")
    private String images;

    @Column(name = "description")
    private String description;

    @Column(name = "reports")
    private String reports;

    @Column(name = "type", nullable = false)
    private String type;

    public Item() {}

    public Item(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReports() {
        return reports;
    }

    public void setReports(String reports) {
        this.reports = reports;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        return type != null ? type.equals(item.type) : item.type == null;
    }

    @Override
    public int hashCode() {
        return id/10;
    }

    @Override
    public String toString() {
        return name;
    }
}

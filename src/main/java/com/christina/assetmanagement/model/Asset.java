package com.christina.assetmanagement.model;

import org.hibernate.id.Assigned;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_asset")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "condition_note")
    private String conditionNote;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @CreatedDate
    @Column(name = "purchase_date")
    private Date purchaseDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "status")
    private Status status;

    public Asset() {
    }

    public Asset(long id, String name, String conditionNote, boolean isDeleted, Date purchaseDate, Category category, Employee employee, Status status) {
        this.id = id;
        this.name = name;
        this.conditionNote = conditionNote;
        this.isDeleted = isDeleted;
        this.purchaseDate = purchaseDate;
        this.category = category;
        this.employee = employee;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConditionNote() {
        return conditionNote;
    }

    public void setConditionNote(String conditionNote) {
        this.conditionNote = conditionNote;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", conditionNote='" + conditionNote + '\'' +
                ", isDeleted=" + isDeleted +
                ", purchaseDate=" + purchaseDate +
                ", category=" + category +
                ", employee=" + employee +
                ", status=" + status +
                '}';
    }
}

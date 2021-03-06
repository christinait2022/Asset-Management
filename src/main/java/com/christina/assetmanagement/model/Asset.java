package com.christina.assetmanagement.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * Asset.
 *
 * @author Christina
 * @date 2022/07/01
 */


@Entity
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_asset")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
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


    @Column(name = "status")
    private Status status;

    public Asset() {
    }


    /**
     * @param name
     * @param conditionNote
     * @param isDeleted
     * @param purchaseDate
     * @param category
     * @param status
     */
    public Asset(String name, String conditionNote, boolean isDeleted,
                 Date purchaseDate, Category category, Status status) {

        this.name = name;
        this.conditionNote = conditionNote;
        this.isDeleted = isDeleted;
        this.purchaseDate = purchaseDate;
        this.category = category;
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
                ", status=" + status +
                '}';
    }
}

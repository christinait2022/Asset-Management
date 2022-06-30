package com.christina.assetmanagement.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "tb_asset_status_change")
public class AssetStatusChange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee assetUser;

    @CreatedDate
    @Column(name = "create_date")
    private Date createdDate;

    @Column(name = "condition_note")
    private String conditionNote;

    @Column(name = "report_status")
    private Status reportStatus;

    public AssetStatusChange() {
    }

    public AssetStatusChange(long id, Asset asset, Employee assetUser, Date createdDate, String conditionNote, Status reportStatus) {
        this.id = id;
        this.asset = asset;
        this.assetUser = assetUser;
        this.createdDate = createdDate;
        this.conditionNote = conditionNote;
        this.reportStatus = reportStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Employee getAssetUser() {
        return assetUser;
    }

    public void setAssetUser(Employee assetUser) {
        this.assetUser = assetUser;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getConditionNote() {
        return conditionNote;
    }

    public void setConditionNote(String conditionNote) {
        this.conditionNote = conditionNote;
    }

    public Status getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Status reportStatus) {
        this.reportStatus = reportStatus;
    }

    @Override
    public String toString() {
        return "AssetStatusChange{" +
                "id=" + id +
                ", asset=" + asset +
                ", assetUser=" + assetUser +
                ", createdDate=" + createdDate +
                ", conditionNote='" + conditionNote + '\'' +
                ", reportStatus=" + reportStatus +
                '}';
    }
}

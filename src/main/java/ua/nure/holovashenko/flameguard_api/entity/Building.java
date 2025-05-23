package ua.nure.holovashenko.flameguard_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "building")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id", nullable = false)
    private Integer buildingId;

    @Size(max = 100)
    @Column(name = "building_name", length = 100)
    private String buildingName;

    @Size(max = 255)
    @Column(name = "building_description", length = 255)
    private String buildingDescription;

    @Size(max = 50)
    @NotNull
    @Column(name = "building_type", length = 50)
    private String buildingType;

    @Size(max = 50)
    @NotNull
    @Column(name = "building_condition", length = 50)
    private String buildingCondition;

    @Column(name = "creation_date", nullable = false, columnDefinition = "DATE NOT NULL DEFAULT (CURRENT_DATE)")
    private LocalDate creationDate = LocalDate.now();

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_account_id", nullable = false)
    private UserAccount userAccount;

    // Getters and Setters
    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingDescription() {
        return buildingDescription;
    }

    public void setBuildingDescription(String buildingDescription) {
        this.buildingDescription = buildingDescription;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public @Size(max = 50) @NotNull String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(@Size(max = 50) @NotNull String buildingType) {
        this.buildingType = buildingType;
    }

    public String getBuildingCondition() {
        return buildingCondition;
    }

    public void setBuildingCondition(String buildingCondition) {
        this.buildingCondition = buildingCondition;
    }

    @PrePersist
    public void validateUserRole() {
        if (!buildingCondition.matches("excellent|good|fair|poor|dangerous")) {
            throw new IllegalArgumentException("Invalid building condition: " + buildingCondition);
        }
    }
}

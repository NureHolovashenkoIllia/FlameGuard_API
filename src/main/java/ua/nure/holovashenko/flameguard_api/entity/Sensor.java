package ua.nure.holovashenko.flameguard_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sensor_id", nullable = false)
    private int sensorId;

    @Size(max = 100)
    @Column(name = "sensor_name", length = 100)
    private String sensorName;

    @NotNull
    @Size(max = 50)
    @Column(name = "sensor_type", length = 50, nullable = false)
    private String sensorType;

    @NotNull
    @Size(max = 20)
    @Column(name = "sensor_status", length = 20, nullable = false)
    private String sensorStatus;

    @Column(name = "last_data_received")
    private LocalDateTime lastDataReceived;

    @NotNull
    @Column(name = "date_added", nullable = false, columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateAdded = LocalDateTime.now();

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "building_id")
    private Building building;

    // Getters and Setters
    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getSensorStatus() {
        return sensorStatus;
    }

    public void setSensorStatus(String sensorStatus) {
        this.sensorStatus = sensorStatus;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @PrePersist
    public void validateSensorFields() {
        if (!sensorType.matches("temperature|gas|smoke|humidity")) {
            throw new IllegalArgumentException("Invalid sensor type: " + sensorType);
        }
        if (!sensorStatus.matches("enabled|faulty|disabled")) {
            throw new IllegalArgumentException("Invalid sensor status: " + sensorStatus);
        }
    }

    public LocalDateTime getLastDataReceived() {
        return lastDataReceived;
    }

    public void setLastDataReceived(LocalDateTime lastDataReceived) {
        this.lastDataReceived = lastDataReceived;
    }
}

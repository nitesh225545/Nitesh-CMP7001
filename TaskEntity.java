package com.baymotors.tasks;

import com.baymotors.users.MechanicProfile;
import com.baymotors.vehicles.VehicleEntity;

public class TaskEntity {
    private int taskId;
    private String description;
    private String priority;
    private String status;
    private VehicleEntity vehicle;
    private MechanicProfile mechanic;

    public TaskEntity(int taskId, String description, String priority, VehicleEntity vehicle, MechanicProfile mechanic) {
        this.taskId = taskId;
        this.description = description;
        this.priority = priority;
        this.status = "Pending"; // Default status
        this.vehicle = vehicle;
        this.mechanic = mechanic;
    }

    // Getter and Setter for Task ID
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    // Getter and Setter for Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and Setter for Priority
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    // Getter and Setter for Status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and Setter for Vehicle
    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }

    // Getter and Setter for Mechanic
    public MechanicProfile getMechanic() {
        return mechanic;
    }

    public void setMechanic(MechanicProfile mechanic) {
        this.mechanic = mechanic;
    }

    // Complete Task
    public void completeTask() {
        this.status = "Completed";
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "taskId=" + taskId +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                ", vehicle=" + vehicle +
                ", mechanic=" + mechanic +
                '}';
    }
}
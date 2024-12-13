package com.baymotors.users;

import com.baymotors.tasks.TaskEntity;

/**
 * Represents a manager in the system.
 */
public class ManagerProfile extends UserProfile {

    public ManagerProfile(int userId, String name, String email) {
        super(userId, name, email);
    }

    public void assignTask(TaskEntity task, MechanicProfile mechanic) {
        System.out.println("Task ID: " + task.getTaskId() + " assigned to Mechanic ID: " + mechanic.getUserId());
    }
}
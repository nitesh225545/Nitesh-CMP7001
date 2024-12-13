package com.baymotors.users;

import com.baymotors.tasks.TaskEntity;

/**
 * Represents a mechanic in the system.
 */
public class MechanicProfile extends UserProfile {

    public MechanicProfile(int userId, String name, String email) {
        super(userId, name, email);
    }

    public void workOnTask(TaskEntity task) {
        System.out.println("Mechanic ID: " + getUserId() + " is working on Task ID: " + task.getTaskId());
    }
}
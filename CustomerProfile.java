package com.baymotors.users;

/**
 * Represents a registered customer in the system.
 */
public class CustomerProfile extends UserProfile {
    private String membershipStatus;

    public CustomerProfile(int userId, String name, String email, String membershipStatus) {
        super(userId, name, email);
        this.membershipStatus = membershipStatus;
    }

    public String getMembershipStatus() {
        return membershipStatus;
    }

    public void upgradeMembership(String newStatus) {
        this.membershipStatus = newStatus;
    }

    @Override
    public String toString() {
        return super.toString() + ", membershipStatus='" + membershipStatus + "'";
    }
}
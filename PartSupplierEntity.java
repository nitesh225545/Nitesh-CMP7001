package com.baymotors.management;

/**
 * Represents a part supplier entity associated with a manufacturer.
 */
public class PartSupplierEntity {
    private int supplierId;
    private String name;
    private String contactDetails;

    public PartSupplierEntity(int supplierId, String name, String contactDetails) {
        this.supplierId = supplierId;
        this.name = name;
        this.contactDetails = contactDetails;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getName() {
        return name;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    @Override
    public String toString() {
        return "PartSupplierEntity{" +
                "supplierId=" + supplierId +
                ", name='" + name + '\'' +
                ", contactDetails='" + contactDetails + '\'' +
                '}';
    }
}
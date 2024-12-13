package com.baymotors.management;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a manufacturer entity with a list of part suppliers.
 */
public class ManufacturerEntity {
    private int manufacturerId;
    private String name;
    private String location;
    private List<PartSupplierEntity> partSuppliers;

    public ManufacturerEntity(int manufacturerId, String name, String location) {
        this.manufacturerId = manufacturerId;
        this.name = name;
        this.location = location;
        this.partSuppliers = new ArrayList<>();
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<PartSupplierEntity> getPartSuppliers() {
        return partSuppliers;
    }

    public void addPartSupplier(PartSupplierEntity supplier) {
        partSuppliers.add(supplier);
    }

    @Override
    public String toString() {
        return "ManufacturerEntity{" +
                "manufacturerId=" + manufacturerId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", partSuppliers=" + partSuppliers +
                '}';
    }
}
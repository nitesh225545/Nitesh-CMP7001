package com.baymotors.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.baymotors.exceptions.InvalidCustomerError;
import com.baymotors.exceptions.ManufacturerNotFoundError;
import com.baymotors.exceptions.MechanicNotFoundError;
import com.baymotors.exceptions.VehicleNotFoundError;
import com.baymotors.management.ManufacturerEntity;
import com.baymotors.management.NotificationHandler;
import com.baymotors.management.PartSupplierEntity;
import com.baymotors.tasks.TaskEntity;
import com.baymotors.users.CustomerProfile;
import com.baymotors.users.ManagerProfile;
import com.baymotors.users.MechanicProfile;
import com.baymotors.vehicles.VehicleEntity;

public class MotorsApplication {
    private static Map<Integer, CustomerProfile> customers = new HashMap<>();
    private static Map<Integer, VehicleEntity> vehicles = new HashMap<>();
    private static Map<Integer, MechanicProfile> mechanics = new HashMap<>();
    private static Map<Integer, TaskEntity> tasks = new HashMap<>();
    private static Map<Integer, ManufacturerEntity> manufacturers = new HashMap<>();
    private static ManagerProfile manager;

    public static void main(String[] args) {
        manager = new ManagerProfile(1, "John Doe", "manager@baymotors.com");
        mechanics.put(1, new MechanicProfile(1, "Jane Smith", "mechanic@baymotors.com"));

        // Load data from CSV files
        loadCustomersFromCSV();
        loadVehiclesFromCSV();
        loadTasksFromCSV();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to Bay Motors System!");
            System.out.println("1. Register Customer");
            System.out.println("2. Register Vehicle");
            System.out.println("3. Assign Task");
            System.out.println("4. Complete Task");
            System.out.println("5. Send Notification");
            System.out.println("6. Add Manufacturer");
            System.out.println("7. Add Part Supplier");
            System.out.println("8. View All Customers");
            System.out.println("9. View All Vehicles");
            System.out.println("10. View All Tasks");
            System.out.println("11. Save Data to CSV Files");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (option) {
                    case 1 -> registerCustomer(scanner);
                    case 2 -> registerVehicle(scanner);
                    case 3 -> assignTask(scanner);
                    case 4 -> completeTask(scanner);
                    case 5 -> sendNotification(scanner);
                    case 6 -> addManufacturer(scanner);
                    case 7 -> addPartSupplier(scanner);
                    case 8 -> viewAllCustomers();
                    case 9 -> viewAllVehicles();
                    case 10 -> viewAllTasks();
                    case 11 -> saveDataToCSV();
                    case 0 -> {
                        System.out.println("Exiting system...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void saveDataToCSV() {
        saveCustomersToCSV();
        saveVehiclesToCSV();
        saveTasksToCSV();
        System.out.println("Data saved to CSV files successfully.");
    }

    private static void saveCustomersToCSV() {
        try (FileWriter writer = new FileWriter("customers.csv")) {
            writer.write("CustomerID,Name,Email,MembershipStatus\n");
            for (CustomerProfile customer : customers.values()) {
                writer.write(customer.getUserId() + "," +
                        customer.getName() + "," +
                        customer.getEmail() + "," +
                        customer.getMembershipStatus() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving customers to CSV: " + e.getMessage());
        }
    }

    private static void saveVehiclesToCSV() {
        try (FileWriter writer = new FileWriter("vehicles.csv")) {
            writer.write("VehicleID,Make,Model,Year\n");
            for (VehicleEntity vehicle : vehicles.values()) {
                writer.write(vehicle.getVehicleId() + "," +
                        vehicle.getMake() + "," +
                        vehicle.getModel() + "," +
                        vehicle.getYear() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving vehicles to CSV: " + e.getMessage());
        }
    }

    private static void saveTasksToCSV() {
        try (FileWriter writer = new FileWriter("tasks.csv")) {
            writer.write("TaskID,Description,Priority,Status,MechanicID,VehicleID\n");
            for (TaskEntity task : tasks.values()) {
                writer.write(task.getTaskId() + "," +
                        task.getDescription() + "," +
                        task.getPriority() + "," +
                        task.getStatus() + "," +
                        task.getMechanic().getUserId() + "," +
                        task.getVehicle().getVehicleId() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to CSV: " + e.getMessage());
        }
    }

    private static void loadCustomersFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("customers.csv"))) {
            String line;
            reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String email = parts[2];
                String membership = parts[3];
                customers.put(id, new CustomerProfile(id, name, email, membership));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Customers file not found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading customers from CSV: " + e.getMessage());
        }
    }

    private static void loadVehiclesFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("vehicles.csv"))) {
            String line;
            reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String make = parts[1];
                String model = parts[2];
                int year = Integer.parseInt(parts[3]);
                vehicles.put(id, new VehicleEntity(id, make, model, year));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Vehicles file not found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading vehicles from CSV: " + e.getMessage());
        }
    }

    private static void loadTasksFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("tasks.csv"))) {
            String line;
            reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String description = parts[1];
                String priority = parts[2];
                String status = parts[3];
                int mechanicId = Integer.parseInt(parts[4]);
                int vehicleId = Integer.parseInt(parts[5]);

                MechanicProfile mechanic = mechanics.get(mechanicId);
                VehicleEntity vehicle = vehicles.get(vehicleId);

                if (mechanic != null && vehicle != null) {
                    TaskEntity task = new TaskEntity(id, description, priority, vehicle, mechanic);
                    task.setStatus(status);
                    tasks.put(id, task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Tasks file not found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading tasks from CSV: " + e.getMessage());
        }
    }

    private static void registerCustomer(Scanner scanner) throws InvalidCustomerError {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (customers.containsKey(id)) {
            throw new InvalidCustomerError("Customer ID " + id + " already exists.");
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        customers.put(id, new CustomerProfile(id, name, email, "Walk-in"));
        System.out.println("Customer registered successfully!");
    }

    private static void registerVehicle(Scanner scanner) throws VehicleNotFoundError {
        System.out.print("Enter Vehicle ID: ");
        int vehicleId = scanner.nextInt();
        scanner.nextLine();

        if (vehicles.containsKey(vehicleId)) {
            throw new VehicleNotFoundError("Vehicle ID " + vehicleId + " already exists.");
        }

        System.out.print("Enter Make: ");
        String make = scanner.nextLine();
        System.out.print("Enter Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Year: ");
        int year = scanner.nextInt();

        vehicles.put(vehicleId, new VehicleEntity(vehicleId, make, model, year));
        System.out.println("Vehicle registered successfully!");
    }

    private static void assignTask(Scanner scanner) {
        try {
            System.out.print("Enter Task ID: ");
            int taskId = scanner.nextInt();
            scanner.nextLine();
    
            System.out.print("Enter Description: ");
            String description = scanner.nextLine();
            System.out.print("Enter Priority (High/Medium/Low): ");
            String priority = scanner.nextLine();
    
            System.out.print("Enter Mechanic ID: ");
            int mechanicId = scanner.nextInt();
    
            MechanicProfile mechanic = mechanics.get(mechanicId);
            if (mechanic == null) {
                throw new MechanicNotFoundError("Mechanic ID " + mechanicId + " not found.");
            }
    
            System.out.print("Enter Vehicle ID: ");
            int vehicleId = scanner.nextInt();
    
            VehicleEntity vehicle = vehicles.get(vehicleId);
            if (vehicle == null) {
                throw new VehicleNotFoundError("Vehicle ID " + vehicleId + " not found.");
            }
    
            TaskEntity task = new TaskEntity(taskId, description, priority, vehicle, mechanic);
            tasks.put(taskId, task);
            manager.assignTask(task, mechanic);
            System.out.println("Task assigned successfully!");
        } catch (VehicleNotFoundError e) {
            System.out.println("Error: " + e.getMessage());
        } catch (MechanicNotFoundError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void completeTask(Scanner scanner) throws VehicleNotFoundError {
        System.out.print("Enter Task ID to complete: ");
        int taskId = scanner.nextInt();

        TaskEntity task = tasks.get(taskId);
        if (task == null) {
            throw new VehicleNotFoundError("Task ID " + taskId + " not found.");
        }

        task.completeTask();
        System.out.println("Task completed successfully!");
    }

    private static void sendNotification(Scanner scanner) throws InvalidCustomerError {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Notification Message: ");
        String message = scanner.nextLine();

        CustomerProfile customer = customers.get(customerId);
        if (customer == null) {
            throw new InvalidCustomerError("Customer ID " + customerId + " not found.");
        }

        NotificationHandler notificationHandler = new NotificationHandler();
        notificationHandler.sendNotification(customerId, message);
        System.out.println("Notification sent successfully!");
    }

    private static void addManufacturer(Scanner scanner) {
        System.out.print("Enter Manufacturer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Manufacturer Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();

        manufacturers.put(id, new ManufacturerEntity(id, name, location));
        System.out.println("Manufacturer added successfully!");
    }

    private static void addPartSupplier(Scanner scanner) throws ManufacturerNotFoundError {
        System.out.print("Enter Manufacturer ID: ");
        int manufacturerId = scanner.nextInt();
        scanner.nextLine();

        ManufacturerEntity manufacturer = manufacturers.get(manufacturerId);
        if (manufacturer == null) {
            throw new ManufacturerNotFoundError("Manufacturer ID " + manufacturerId + " not found.");
        }

        System.out.print("Enter Supplier ID: ");
        int supplierId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Supplier Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Contact Details: ");
        String contact = scanner.nextLine();

        manufacturer.addPartSupplier(new PartSupplierEntity(supplierId, name, contact));
        System.out.println("Part supplier added successfully!");
    }

    private static void viewAllCustomers() {
        System.out.println("Registered Customers:");
        customers.values().forEach(System.out::println);
    }

    private static void viewAllVehicles() {
        System.out.println("Registered Vehicles:");
        vehicles.values().forEach(System.out::println);
    }

    private static void viewAllTasks() {
        System.out.println("Assigned Tasks:");
        tasks.values().forEach(System.out::println);
    }
}
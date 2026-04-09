import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Parcel {
    private String trackingId;
    private String senderName;
    private String receiverName;
    private String status;

    public Parcel(String trackingId, String senderName, String receiverName) {
        this.trackingId = trackingId;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.status = "Registered"; // default status
    }

    public String getTrackingId() {
        return trackingId;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "Tracking ID='" + trackingId + '\'' +
                ", Sender='" + senderName + '\'' +
                ", Receiver='" + receiverName + '\'' +
                ", Status='" + status + '\'' +
                '}';
    }
}

public class CourierTrackingSystem {

    private static Map<String, Parcel> parcels = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== Courier Parcel Tracking System ===");
            System.out.println("1. Register Parcel");
            System.out.println("2. Update Delivery Status");
            System.out.println("3. Track Parcel");
            System.out.println("4. Show All Parcels");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> registerParcel();
                case 2 -> updateStatus();
                case 3 -> trackParcel();
                case 4 -> showAllParcels();
                case 5 -> System.out.println("Exiting system. Goodbye!");
                default -> System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 5);
    }

    private static void registerParcel() {
        System.out.print("Enter Tracking ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Sender Name: ");
        String sender = scanner.nextLine();
        System.out.print("Enter Receiver Name: ");
        String receiver = scanner.nextLine();

        Parcel parcel = new Parcel(id, sender, receiver);
        parcels.put(id, parcel);

        System.out.println("Parcel registered successfully!");
    }

    private static void updateStatus() {
        System.out.print("Enter Tracking ID to update: ");
        String id = scanner.nextLine();
        Parcel parcel = parcels.get(id);

        if (parcel != null) {
            System.out.print("Enter new status (e.g., In Transit, Delivered, Delayed): ");
            String status = scanner.nextLine();
            parcel.updateStatus(status);
            System.out.println("Status updated successfully!");
        } else {
            System.out.println("Parcel not found!");
        }
    }

    private static void trackParcel() {
        System.out.print("Enter Tracking ID to track: ");
        String id = scanner.nextLine();
        Parcel parcel = parcels.get(id);

        if (parcel != null) {
            System.out.println(parcel);
        } else {
            System.out.println("Parcel not found!");
        }
    }

    private static void showAllParcels() {
        if (parcels.isEmpty()) {
            System.out.println("No parcels registered.");
        } else {
            System.out.println("All Parcels:");
            parcels.values().forEach(System.out::println);
        }
    }
}
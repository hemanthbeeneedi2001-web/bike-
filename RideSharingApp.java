import java.util.*;

// ustom Exception for invalid ride type
class InvalidRideTypeException extends Exception {
    public InvalidRideTypeException(String message) {
        super(message);
    }
}

// bstract class (Base Class)
abstract class Ride {
    private String driverName;
    private String vehicleNumber;
    protected double distance;

    // Constructor
    public Ride(String driverName, String vehicleNumber, double distance) {
        this.driverName = driverName;
        this.vehicleNumber = vehicleNumber;
        this.distance = distance;
    }

    // Getters (Encapsulation)
    public String getDriverName() {
        return driverName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public double getDistance() {
        return distance;
    }

    // Abstract method
    public abstract double calculateFare();
}

// Subclass 1: Bike Ride
class BikeRide extends Ride {
    public BikeRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return distance * 10; // ₹10 per km
    }
}

// Subclass 2: Car Ride
class CarRide extends Ride {
    public CarRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return distance * 20; // ₹20 per km
    }
}

// ✅Main class
public class RideSharingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Input
            String rideType = sc.nextLine().trim().toLowerCase();  // <-- FIXED HERE
            double distance = sc.nextDouble();

            // Validation
            if (distance <= 0) {
                throw new IllegalArgumentException("Distance must be greater than 0.");
            }

            Ride ride;

            // Choosing ride type
            switch (rideType) {
                case "bike":
                    ride = new BikeRide("Rajesh", "AP09 BK 1234", distance);
                    break;
                case "car":
                    ride = new CarRide("Kiran", "TS07 CR 5678", distance);
                    break;
                default:
                    throw new InvalidRideTypeException("Invalid ride type: " + rideType);
            }

            // Output
            System.out.println("Driver: " + ride.getDriverName());
            System.out.println("Vehicle No: " + ride.getVehicleNumber());
            System.out.println("Distance: " + ride.getDistance() + " km");
            System.out.println("Fare: ₹" + ride.calculateFare());

        } catch (InvalidRideTypeException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input format.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }
    }
}

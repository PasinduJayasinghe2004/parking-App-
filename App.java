import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class App {
    // Global variables
    private static int[][] parkingStructure = null;
    private static final ArrayList<Owner> owners=new ArrayList<>();
    private static final String[] zoneNames={"Zone A","Zone B"};

    public static void main(String[] args) {
        System.out.println("Welcome to the Parking Lot Reservation App!");
        initialiseZones();
        runMenu();
    }

    public static void initialiseZones() {
        parkingStructure = new int[2][];
        parkingStructure[0] = new int[10]; // Zone A: Compact (10 slots)
        parkingStructure[1] = new int[14]; // Zone B: Standard (14 slots)
    }

    public static void runMenu() {
        int option;
        boolean cont = true;
        while (cont) {
            option = getOption();
            switch (option) {
                case 0:
                    cont = false;
                    break;
                case 1:
                    reserveSlot();
                    break;
                case 2:
                    showParkingArea();
                    break;
                case 3:
                    addZone();
                    break;
                case 4:
                    registerOwner();
                    break;
                case 5:
                    saveToFile();
                    break;
                default:
                    System.out.println("Option not available. Please select a valid option:");
            }
        }
    }

    private static int getOption() {
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        int option = -1;
        do {
            System.out.println();
            System.out.println("+---------------------------------------------+");
            System.out.println("|                 MAIN MENU                   |");
            System.out.println("+---------------------------------------------+");
            System.out.println("|  1) Reserve a parking slot                  |");
            System.out.println("|  2) Show parking area                       |");
            System.out.println("|  3) Add new parking zone                    |");
            System.out.println("|  4) Register vehicle owner                  |");
            System.out.println("|  5) Save information to file                |");
            System.out.println("|  0) Quit                                    |");
            System.out.println("+---------------------------------------------+");
            System.out.print("Please select an option: ");
            try {
                option = input.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("This option is not valid.");
                input.next(); // Clear buffer
            }
        } while (!valid);
        return option;
    }

    private static void reserveSlot() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter zone (A/B): ");
        String zoneInput = input.next().toUpperCase();
        int zoneIndex = zoneInput.equals("A") ? 0 : zoneInput.equals("B") ? 1 : -1;

        if (zoneIndex == -1) {
            System.out.println("Invalid zone. Please enter A or B.");
            return;
        }

        System.out.print("Enter slot number: ");
        int slot = input.nextInt() - 1;

        if (slot < 0 || slot >= parkingStructure[zoneIndex].length) {
            System.out.println("Invalid slot number.");
            return;
        }

        if (parkingStructure[zoneIndex][slot] == 0) {
            parkingStructure[zoneIndex][slot] = 1;
            System.out.println("Reservation successful.");
            showParkingArea();
        } else {
            System.out.println("This slot is already reserved.");
        }
    }

    private static void showParkingArea() {
        System.out.println("=".repeat(60));
        System.out.println("                PARKING LOT STATUS             ");
        System.out.println("=".repeat(60));

        String[] zoneLabels = {"Zone A (Compact)", "Zone B (Standard)"};
        for (int i = 0; i < parkingStructure.length; i++) {
            System.out.print(zoneLabels[i] + "  ");
            for (int j = 0; j < parkingStructure[i].length; j++) {
                if (parkingStructure[i][j] == 0) {
                    System.out.print("[O]");
                } else {
                    System.out.print("[X]");
                }
            }
            System.out.println();
        }

        System.out.println("=".repeat(60));
        System.out.println("LEGEND: [O] = Available | [X] = Reserved");
        System.out.println("=".repeat(60));
        System.out.println();
    }

    private static void addZone() {
        // method not implemented
        Scanner input=new Scanner(System.in);
        System.out.println("Enter new Zone letter: ");
        String zoneLabel=input.nextLine().toUpperCase();

        System.out.println("Enter slots: ");
        int slots=input.nextInt();

        int[][] newStructure=new int[parkingStructure.length+1][];
        String[] newZoneName=new String[zoneNames.length+1];

        for(int i=0;i< parkingStructure.length;i++){
            newStructure[i]=parkingStructure[i];
            newZoneName[i]=zoneNames[i];

        }
        newStructure[parkingStructure.length]=new int[slots];
        newZoneName[zoneNames.length]="Zones"+zoneLabel;

        parkingStructure=newStructure;
        System.out.println("Successfully added");

    }

    private static void registerOwner() {
        // method not implemented
        Scanner input=new Scanner(System.in);

        System.out.println("Enter name :");
        String name=input.nextLine();

        System.out.println("Enter Vehicle Number: ");
        int vehicleNumber=input.nextInt();

        System.out.println("Enter contact Number: ");
        int ContactNumber=input.nextInt();

        Owner newOwner=new Owner(name,vehicleNumber,ContactNumber);
        owners.add(newOwner);

        System.out.println("Owner registered Successfully");


    }

    private static void saveToFile() {
        try {
            FileWriter file = new FileWriter("ParkingData.txt");
            file.write("Data:\n");

            for(int i=0;i<parkingStructure.length;i++){
                String zone=zoneNames.length>i ? zoneNames[i]:"Zone"+(char)('A'+i);
                file.write(zone+":");

                for(int slot:parkingStructure[i]){
                    file.write((slot == 0 ? "[ ]" : "[X]") + " ");
                }
                file.write("\n");
            }
            file.write("\n===Registry Owners====\n");
            for(Owner owner:owners){
                file.write("Name : "+owner.getName()+"VehicleNumber: "+owner.getVehicleNumber()+"\n"+"ContactNumber: "+owner.getContactNumber());
            }
            // Implement save data here based on test task
            file.close();
            System.out.println("Data saved to file.");
        } catch (IOException exception) {
            System.out.println("Error while writing to file.");
        }
    }
}

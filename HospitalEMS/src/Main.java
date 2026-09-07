import java.util.Scanner;

/**
 * Main.java
 * Console-based Mini Hospital Emergency Management System.
 *
 * Ties together all four required data structures:
 *  - PatientBST      (Binary Search Tree)   -> patient records
 *  - EmergencyQueue   (Queue)                -> patients waiting for treatment
 *  - TreatmentStack   (Stack)                -> completed treatment history
 *  - VisitLinkedList  (Singly Linked List)   -> each patient's past visits
 */
public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static PatientBST patientBST = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentStack = new TreatmentStack();

    // simple auto-incrementing IDs for treatment records and visits
    private static int nextRecordId = 1;
    private static int nextVisitId = 1;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    patientMenu();
                    break;
                case 2:
                    queueMenu();
                    break;
                case 3:
                    treatmentMenu();
                    break;
                case 4:
                    visitHistoryMenu();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting Hospital Emergency Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }

    // ===================== MAIN MENU =====================
    private static void printMainMenu() {
        System.out.println("\n=========================================");
        System.out.println(" MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM");
        System.out.println("=========================================");
        System.out.println("1. Patient Records (Binary Search Tree)");
        System.out.println("2. Emergency Patient Queue (Queue)");
        System.out.println("3. Treatment History (Stack)");
        System.out.println("4. Patient Visit History (Singly Linked List)");
        System.out.println("0. Exit");
    }

    // ===================== 1. PATIENT BST MENU =====================
    private static void patientMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n----- Patient Records (BST) -----");
            System.out.println("1. Add New Patient");
            System.out.println("2. Search Patient by ID");
            System.out.println("3. Delete Patient");
            System.out.println("4. Display All Patients (In-Order)");
            System.out.println("0. Back to Main Menu");
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    searchPatient();
                    break;
                case 3:
                    deletePatient();
                    break;
                case 4:
                    patientBST.displayInOrder();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addPatient() {
        int id = readInt("Enter Patient ID: ");
        if (patientBST.search(id) != null) {
            System.out.println("A patient with this ID already exists.");
            return;
        }
        String name = readString("Enter Patient Name: ");
        int age = readInt("Enter Age: ");
        String contact = readString("Enter Contact Number: ");
        String condition = readString("Enter Medical Condition: ");

        Patient patient = new Patient(id, name, age, contact, condition);
        patientBST.insert(patient);
        System.out.println("Patient added successfully.");
    }

    private static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
        } else {
            System.out.println("Patient found: " + patient);
        }
    }

    private static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        boolean deleted = patientBST.delete(id);
        if (deleted) {
            System.out.println("Patient deleted successfully.");
        } else {
            System.out.println("No patient found with ID " + id);
        }
    }

    // ===================== 2. EMERGENCY QUEUE MENU =====================
    private static void queueMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n----- Emergency Patient Queue -----");
            System.out.println("1. Add Patient to Queue (Enqueue)");
            System.out.println("2. Call Next Patient for Treatment (Dequeue)");
            System.out.println("3. Display Waiting Queue");
            System.out.println("0. Back to Main Menu");
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    enqueuePatient();
                    break;
                case 2:
                    dequeuePatient();
                    break;
                case 3:
                    emergencyQueue.display();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void enqueuePatient() {
        int id = readInt("Enter Patient ID to add to the emergency queue: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id + ". Please register the patient first (option 1).");
            return;
        }
        emergencyQueue.enqueue(patient);
        System.out.println("Patient " + patient.getName() + " added to the emergency queue.");
    }

    private static void dequeuePatient() {
        Patient patient = emergencyQueue.dequeue();
        if (patient == null) {
            return; // EmergencyQueue already printed the "empty" message
        }
        System.out.println("Now treating: " + patient);

        String proceed = readString("Complete treatment and add to history now? (y/n): ");
        if (proceed.equalsIgnoreCase("y")) {
            String details = readString("Enter treatment details: ");
            TreatmentRecord record = new TreatmentRecord(
                    nextRecordId++, patient.getPatientId(), patient.getName(), details, "Today");
            treatmentStack.push(record);
            System.out.println("Treatment recorded and pushed to history.");

            String addVisit = readString("Also add this as a visit in the patient's history? (y/n): ");
            if (addVisit.equalsIgnoreCase("y")) {
                String doctor = readString("Enter Doctor Name: ");
                String diagnosis = readString("Enter Diagnosis: ");
                Visit visit = new Visit(nextVisitId++, "Today", doctor, diagnosis, details);
                patient.getVisitHistory().addVisit(visit);
                System.out.println("Visit added to patient's history.");
            }
        }
    }

    // ===================== 3. TREATMENT STACK MENU =====================
    private static void treatmentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n----- Treatment History (Stack) -----");
            System.out.println("1. Push New Treatment Record");
            System.out.println("2. Pop Most Recent Treatment Record");
            System.out.println("3. Display Treatment History");
            System.out.println("0. Back to Main Menu");
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    pushTreatment();
                    break;
                case 2:
                    popTreatment();
                    break;
                case 3:
                    treatmentStack.display();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void pushTreatment() {
        int id = readInt("Enter Patient ID for this treatment record: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
            return;
        }
        String details = readString("Enter treatment details: ");
        String date = readString("Enter completion date (e.g. 2026-09-07): ");
        TreatmentRecord record = new TreatmentRecord(nextRecordId++, id, patient.getName(), details, date);
        treatmentStack.push(record);
        System.out.println("Treatment record pushed to history.");
    }

    private static void popTreatment() {
        TreatmentRecord record = treatmentStack.pop();
        if (record != null) {
            System.out.println("Removed most recent record: " + record);
        }
    }

    // ===================== 4. VISIT HISTORY (LINKED LIST) MENU =====================
    private static void visitHistoryMenu() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
            return;
        }

        boolean back = false;
        while (!back) {
            System.out.println("\n----- Visit History for " + patient.getName() + " (ID: " + id + ") -----");
            System.out.println("1. Add New Visit");
            System.out.println("2. Remove Visit");
            System.out.println("3. Search Visit");
            System.out.println("4. Display Visit History");
            System.out.println("0. Back to Main Menu");
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addVisit(patient);
                    break;
                case 2:
                    removeVisit(patient);
                    break;
                case 3:
                    searchVisit(patient);
                    break;
                case 4:
                    patient.getVisitHistory().display();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addVisit(Patient patient) {
        String date = readString("Enter Visit Date (e.g. 2026-09-07): ");
        String doctor = readString("Enter Doctor Name: ");
        String diagnosis = readString("Enter Diagnosis: ");
        String treatment = readString("Enter Treatment: ");
        Visit visit = new Visit(nextVisitId++, date, doctor, diagnosis, treatment);
        patient.getVisitHistory().addVisit(visit);
        System.out.println("Visit added with Visit ID: " + visit.getVisitId());
    }

    private static void removeVisit(Patient patient) {
        int visitId = readInt("Enter Visit ID to remove: ");
        boolean removed = patient.getVisitHistory().removeVisit(visitId);
        System.out.println(removed ? "Visit removed successfully." : "No visit found with ID " + visitId);
    }

    private static void searchVisit(Patient patient) {
        int visitId = readInt("Enter Visit ID to search: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);
        System.out.println(visit == null ? "No visit found with ID " + visitId : "Visit found: " + visit);
    }

    // ===================== INPUT HELPERS =====================
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
}

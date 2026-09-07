/**
 * PatientBST.java
 * Binary Search Tree that stores Patient records keyed by Patient ID.
 *
 * Supports:
 *  - insert(Patient)
 *  - search(int patientId)
 *  - delete(int patientId)
 *  - displayInOrder()  -> prints patients in ascending order of Patient ID
 */
public class PatientBST {
    private BSTNode root;

    public PatientBST() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    // ---------- INSERT ----------
    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private BSTNode insertRec(BSTNode node, Patient patient) {
        if (node == null) {
            return new BSTNode(patient);
        }
        if (patient.getPatientId() < node.patient.getPatientId()) {
            node.left = insertRec(node.left, patient);
        } else if (patient.getPatientId() > node.patient.getPatientId()) {
            node.right = insertRec(node.right, patient);
        } else {
            // Duplicate ID - update existing record instead of inserting a duplicate node
            node.patient = patient;
        }
        return node;
    }

    // ---------- SEARCH ----------
    public Patient search(int patientId) {
        BSTNode result = searchRec(root, patientId);
        return (result == null) ? null : result.patient;
    }

    private BSTNode searchRec(BSTNode node, int patientId) {
        if (node == null || node.patient.getPatientId() == patientId) {
            return node;
        }
        if (patientId < node.patient.getPatientId()) {
            return searchRec(node.left, patientId);
        }
        return searchRec(node.right, patientId);
    }

    // ---------- DELETE ----------
    public boolean delete(int patientId) {
        if (search(patientId) == null) {
            return false; // nothing to delete
        }
        root = deleteRec(root, patientId);
        return true;
    }

    private BSTNode deleteRec(BSTNode node, int patientId) {
        if (node == null) {
            return null;
        }

        if (patientId < node.patient.getPatientId()) {
            node.left = deleteRec(node.left, patientId);
        } else if (patientId > node.patient.getPatientId()) {
            node.right = deleteRec(node.right, patientId);
        } else {
            // Node found - handle the three deletion cases

            // Case 1: no children
            if (node.left == null && node.right == null) {
                return null;
            }
            // Case 2: one child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            // Case 3: two children - replace with in-order successor
            // (smallest value in the right subtree)
            Patient successor = findMinPatient(node.right);
            node.patient = successor;
            node.right = deleteRec(node.right, successor.getPatientId());
        }
        return node;
    }

    private Patient findMinPatient(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.patient;
    }

    // ---------- IN-ORDER TRAVERSAL ----------
    public void displayInOrder() {
        if (isEmpty()) {
            System.out.println("No patient records found.");
            return;
        }
        System.out.println("----- Patient Records (Ascending Patient ID) -----");
        inOrderRec(root);
        System.out.println("---------------------------------------------------");
    }

    private void inOrderRec(BSTNode node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.println(node.patient);
            inOrderRec(node.right);
        }
    }
}

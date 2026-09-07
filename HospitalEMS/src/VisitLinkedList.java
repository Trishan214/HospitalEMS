/**
 * VisitLinkedList.java
 * Singly Linked List that stores a patient's previous hospital visits.
 *
 * Supports:
 *  - addVisit(Visit)          -> add a new visit to the end of the list
 *  - removeVisit(int visitId) -> remove a visit by its ID
 *  - searchVisit(int visitId) -> find a visit by its ID
 *  - display()                -> print all visits in order
 */
public class VisitLinkedList {
    private VisitNode head;

    public VisitLinkedList() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // ---------- ADD ----------
    public void addVisit(Visit visit) {
        VisitNode newNode = new VisitNode(visit);
        if (head == null) {
            head = newNode;
            return;
        }
        VisitNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // ---------- REMOVE ----------
    public boolean removeVisit(int visitId) {
        if (head == null) {
            return false;
        }
        if (head.visit.getVisitId() == visitId) {
            head = head.next;
            return true;
        }
        VisitNode current = head;
        while (current.next != null) {
            if (current.next.visit.getVisitId() == visitId) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false; // not found
    }

    // ---------- SEARCH ----------
    public Visit searchVisit(int visitId) {
        VisitNode current = head;
        while (current != null) {
            if (current.visit.getVisitId() == visitId) {
                return current.visit;
            }
            current = current.next;
        }
        return null; // not found
    }

    // ---------- DISPLAY ----------
    public void display() {
        if (isEmpty()) {
            System.out.println("No visit history found for this patient.");
            return;
        }
        VisitNode current = head;
        while (current != null) {
            System.out.println(current.visit);
            current = current.next;
        }
    }
}

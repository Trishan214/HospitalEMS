/**
 * EmergencyQueue.java
 * FIFO Queue (linked-list based) that manages patients waiting
 * in the emergency unit.
 *
 * Supports:
 *  - enqueue(Patient) -> add a patient to the back of the queue
 *  - dequeue()        -> remove and return the patient at the front
 *  - display()        -> list all waiting patients, front to back
 *  - isEmpty()
 */
public class EmergencyQueue {
    private QueueNode front;
    private QueueNode rear;
    private int size;

    public EmergencyQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    // ---------- ENQUEUE ----------
    public void enqueue(Patient patient) {
        QueueNode newNode = new QueueNode(patient);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // ---------- DEQUEUE ----------
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patient to call.");
            return null;
        }
        Patient patient = front.patient;
        front = front.next;
        if (front == null) {
            rear = null; // queue is now empty
        }
        size--;
        return patient;
    }

    // ---------- DISPLAY ----------
    public void display() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patients waiting.");
            return;
        }
        System.out.println("----- Patients Waiting (Front -> Rear) -----");
        QueueNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.patient);
            current = current.next;
            position++;
        }
        System.out.println("---------------------------------------------");
    }
}

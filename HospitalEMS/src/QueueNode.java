/**
 * QueueNode.java
 * A single node of the Emergency Patient Queue (linked-list based queue).
 */
public class QueueNode {
    Patient patient;
    QueueNode next;

    public QueueNode(Patient patient) {
        this.patient = patient;
        this.next = null;
    }
}

/**
 * StackNode.java
 * A single node of the Treatment History Stack (linked-list based stack).
 */
public class StackNode {
    TreatmentRecord record;
    StackNode next;

    public StackNode(TreatmentRecord record) {
        this.record = record;
        this.next = null;
    }
}

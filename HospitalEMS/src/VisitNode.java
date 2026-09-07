/**
 * VisitNode.java
 * A single node of the Singly Linked List used for patient visit history.
 */
public class VisitNode {
    Visit visit;
    VisitNode next;

    public VisitNode(Visit visit) {
        this.visit = visit;
        this.next = null;
    }
}

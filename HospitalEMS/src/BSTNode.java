/**
 * BSTNode.java
 * A single node of the Patient Binary Search Tree.
 */
public class BSTNode {
    Patient patient;
    BSTNode left;
    BSTNode right;

    public BSTNode(Patient patient) {
        this.patient = patient;
        this.left = null;
        this.right = null;
    }
}

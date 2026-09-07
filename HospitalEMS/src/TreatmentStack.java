/**
 * TreatmentStack.java
 * LIFO Stack (linked-list based) that stores completed treatment records.
 *
 * Supports:
 *  - push(TreatmentRecord) -> add a newly completed treatment record
 *  - pop()                 -> remove and return the most recent record
 *  - display()             -> list records from most recent to oldest
 *  - isEmpty()
 */
public class TreatmentStack {
    private StackNode top;
    private int size;

    public TreatmentStack() {
        top = null;
        size = 0;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    // ---------- PUSH ----------
    public void push(TreatmentRecord record) {
        StackNode newNode = new StackNode(record);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // ---------- POP ----------
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment history is empty. Nothing to remove.");
            return null;
        }
        TreatmentRecord record = top.record;
        top = top.next;
        size--;
        return record;
    }

    // ---------- DISPLAY ----------
    public void display() {
        if (isEmpty()) {
            System.out.println("Treatment history is empty.");
            return;
        }
        System.out.println("----- Treatment History (Most Recent First) -----");
        StackNode current = top;
        while (current != null) {
            System.out.println(current.record);
            current = current.next;
        }
        System.out.println("---------------------------------------------------");
    }
}

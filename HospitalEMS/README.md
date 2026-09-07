🏥 Mini Hospital Emergency Management System

A console-based Java application built for CIT300 – Data Structures and Algorithms that simulates how a hospital emergency unit manages patients — from registration, to waiting in the emergency queue, to treatment, to keeping a history of past visits.

Course: CIT300 - Data Structures and Algorithms
Assignment: Individual Mid Assignment - Mini Hospital Emergency Management System

**Overview**

The system is menu-driven and runs entirely in the console. It models a simplified emergency department workflow:

Register a patient into the hospital's records.
Add them to the emergency queue when they arrive.
Call the next patient for treatment (FIFO).
Record completed treatments in a history stack (LIFO).
Track each patient's past visits in their own visit history.

HospitalEMS/
├── src/
│   ├── Main.java              # Console menu / program entry point
│   ├── Patient.java           # Patient record model
│   ├── BSTNode.java           # BST node
│   ├── PatientBST.java        # Binary Search Tree (insert/search/delete/in-order)
│   ├── Visit.java             # Visit record model
│   ├── VisitNode.java         # Linked list node
│   ├── VisitLinkedList.java   # Singly Linked List (add/remove/search/display)
│   ├── QueueNode.java         # Queue node
│   ├── EmergencyQueue.java    # FIFO Queue (enqueue/dequeue/display)
│   ├── TreatmentRecord.java   # Treatment record model
│   ├── StackNode.java         # Stack node
│   └── TreatmentStack.java    # LIFO Stack (push/pop/display)
├── bin/                        # Compiled .class files go here
└── README.md
```

**How to Use**

On launch, you'll see the main menu:

=========================================
 MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM
=========================================
1. Patient Records (Binary Search Tree)
2. Emergency Patient Queue (Queue)
3. Treatment History (Stack)
4. Patient Visit History (Singly Linked List)
0. Exit
## Suggested Git Commit Sequence

To satisfy the "progressive commits" requirement, commit roughly in this order:

1. `Initial project structure`
2. `Add Patient model and BST node`
3. `Implement PatientBST insert and search`
4. `Implement PatientBST delete and in-order traversal`
5. `Implement EmergencyQueue (enqueue/dequeue/display)`
6. `Add TreatmentRecord model and TreatmentStack`
7. `Add Visit model and VisitLinkedList`
8. `Implement Main console menu`
9. `Manual testing and bug fixes`
10. `Update README`

Avoid committing the whole finished project in a single commit — commit
after each meaningful step above, ideally on separate days/sessions so the
commit history reflects genuine incremental development.

Author

Developed by Trishan Kaveesha as an individual assignment for CIT300 – Data Structures and Algorithms.

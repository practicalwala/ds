import java.util.Scanner;

public class BullyAlgorithm {
    static final int TOTAL_PROCESSES = 5;
    static boolean[] state = new boolean[TOTAL_PROCESSES]; 
    static int coordinator = TOTAL_PROCESSES;

    public static void bringProcessUp(int processId) {
        if (state[processId - 1]) {
            System.out.println("Process " + processId + " is already up.");
        } else {
            state[processId - 1] = true;
            System.out.println("Process " + processId + " is brought up.");
            if (processId > coordinator) {
                System.out.println("Process " + processId + " initiates an election.");
                election(processId);
            }
        }
    }

    public static void bringProcessDown(int processId) {
        if (!state[processId - 1]) {
            System.out.println("Process " + processId + " is already down.");
        } else {
            state[processId - 1] = false;
            System.out.println("Process " + processId + " is down.");
            if (processId == coordinator) {
                System.out.println("Coordinator (Process " + processId + ") is down.");
                for (int i = TOTAL_PROCESSES; i >= 1; i--) {
                    if (i != processId && state[i - 1]) {
                        election(i);
                        break;
                    }
                }
            }
        }
    }

    public static void sendMessage(int processId) {
        if (!state[processId - 1]) {
            System.out.println("Process " + processId + " is down. Cannot send message.");
        } else {
            if (coordinator == processId) {
                System.out.println("Coordinator (Process " + coordinator + ") received the message: OK");
            } else {
                if (!state[coordinator - 1]) {
                    System.out.println("Coordinator is down. Process " + processId + " initiates an election.");
                    election(processId);
                } else {
                    System.out.println("Process " + processId + " sends message to Coordinator (Process " + coordinator + ").");
                }
            }
        }
    }

    public static void election(int initiator) {
        System.out.println("Election initiated by Process " + initiator + ".");
        boolean newCoordinatorFound = false;
        for (int i = initiator + 1; i <= TOTAL_PROCESSES; i++) {
            if (state[i - 1]) {
                System.out.println("Process " + initiator + " sends election message to Process " + i + ".");
                newCoordinatorFound = true;
            }
        }

        if (!newCoordinatorFound) {
            coordinator = initiator;
            System.out.println("Process " + coordinator + " becomes the new coordinator.");
        } else {
            for (int i = TOTAL_PROCESSES; i > initiator; i--) {
                if (state[i - 1]) {
                    coordinator = i;
                    System.out.println("Process " + coordinator + " becomes the new coordinator.");
                    break;
                }
            }
        }

        System.out.println("Coordinator message sent from Process " + coordinator + " to all.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < TOTAL_PROCESSES; i++) {
            state[i] = true; 
        }

        System.out.println("Initial state: All " + TOTAL_PROCESSES + " processes are up.");
        System.out.println("Process " + coordinator + " is the initial coordinator.");

        int choice;
        do {
            System.out.println("\n----------------------");
            System.out.println("1. Bring up a process");
            System.out.println("2. Bring down a process");
            System.out.println("3. Send a message");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter process number to bring up (1-5): ");
                    int up = scanner.nextInt();
                    if (up >= 1 && up <= TOTAL_PROCESSES) {
                        bringProcessUp(up);
                    } else {
                        System.out.println("Invalid process number.");
                    }
                    break;
                case 2:
                    System.out.print("Enter process number to bring down (1-5): ");
                    int down = scanner.nextInt();
                    if (down >= 1 && down <= TOTAL_PROCESSES) {
                        bringProcessDown(down);
                    } else {
                        System.out.println("Invalid process number.");
                    }
                    break;
                case 3:
                    System.out.print("Enter process number to send message (1-5): ");
                    int msg = scanner.nextInt();
                    if (msg >= 1 && msg <= TOTAL_PROCESSES) {
                        sendMessage(msg);
                    } else {
                        System.out.println("Invalid process number.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting the simulation.");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 4);

        scanner.close();
    }
}

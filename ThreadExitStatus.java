import java.util.Scanner;

public class ThreadExitStatus {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter exit status for each task (0 for Success, 1 for Failure, 2 for Cancel):");

        System.out.print("Exit status for Task 1: ");
        int status1 = scanner.nextInt();

        System.out.print("Exit status for Task 2: ");
        int status2 = scanner.nextInt();

        System.out.print("Exit status for Task 3: ");
        int status3 = scanner.nextInt();

        MyThread thread1 = new MyThread(1, status1);
        MyThread thread2 = new MyThread(2, status2);
        MyThread thread3 = new MyThread(3, status3);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            System.out.println("Task 1 completed with status: " + thread1.getStatusMessage());

            thread2.join();
            System.out.println("Task 2 completed with status: " + thread2.getStatusMessage());

            thread3.join();
            System.out.println("Task 3 completed with status: " + thread3.getStatusMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyThread extends Thread {
        private final int taskId;
        private final int userStatus;
        private String exitStatusMessage;

        public MyThread(int taskId, int userStatus) {
            this.taskId = taskId;
            this.userStatus = userStatus;
        }

        @Override
        public void run() {
            System.out.println("Task " + taskId + " is running...");

            switch (userStatus) {
                case 0:
                    exitStatusMessage = "Success";
                    System.out.println("Task " + taskId + " completed successfully.");
                    break;
                case 1:
                    exitStatusMessage = "Failure";
                    System.out.println("Task " + taskId + " failed.");
                    break;
                case 2:
                    exitStatusMessage = "Cancelled";
                    System.out.println("Task " + taskId + " was cancelled.");
                    break;
                default:
                    exitStatusMessage = "Unknown Status";
                    System.out.println("Task " + taskId + " has an invalid status.");
            }
        }

        public String getStatusMessage() {
            return exitStatusMessage;
        }
    }
}

import java.util.concurrent.Semaphore;

public class BinarySemaphore {

    private static int sharedResource = 0;
    private static Semaphore binarySemaphore = new Semaphore(1);

    public static void main(String[] args) {
        
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("Thread 1: Trying to access the resource...");
                binarySemaphore.acquire();
                System.out.println("Thread 1: Accessing the resource...");
                sharedResource++;
                System.out.println("Thread 1: Incremented shared resource to " + sharedResource);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Thread 1: Releasing the resource...");
                binarySemaphore.release();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("Thread 2: Trying to access the resource...");
                binarySemaphore.acquire();
                System.out.println("Thread 2: Accessing the resource...");
                sharedResource--;
                System.out.println("Thread 2: Decremented shared resource to " + sharedResource);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Thread 2: Releasing the resource...");
                binarySemaphore.release();
            }
        });

        thread1.start();
        thread2.start();
    }
}

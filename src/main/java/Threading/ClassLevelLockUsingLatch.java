package Threading;
import java.util.concurrent.CountDownLatch;


public class ClassLevelLockUsingLatch {


    private static Object lock = new Object();
    private static int count = 0;

    ClassLevelLockUsingLatch() {
        increment();
    }

    private void increment() {
        synchronized (lock) {
            count += 1;
        }
    }

    public static int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        int numThreads = 10;
        MyRunnable myRunnable = new MyRunnable(numThreads);
        Thread[] arr = new Thread[numThreads];
        CountDownLatch latch = new CountDownLatch(numThreads); // creates latch with no of workers threads
        // that should be waited before main thread can end.

        for (int i = 0; i < numThreads; i++) {
            // using lambda to pass runnable object
            arr[i] = new Thread(() -> {
                myRunnable.proces();
                latch.countDown();
            });
            /**
             * to pass the RUnnable object
             *  arr[i] = new Thread(new MyRunnable(latch));
             * */
            arr[i].start();
        }

        try {
            arr[7].interrupt(); // Interrupt the 7th thread.
            latch.await(); // Wait for all threads to finish.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(getCount());
    }





    static class MyRunnable {
        private int numThreads;

        public MyRunnable(int numThreads) {
            this.numThreads = numThreads;
        }

        public void proces() {
            try {
                Thread.sleep(50);
                ClassLevelLockUsingLatch o = new ClassLevelLockUsingLatch();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " is interrupted");
            }
        }
    }


    class MyRunnable2 implements Runnable {
        private CountDownLatch latch;

        public MyRunnable2(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            proces();
        }

        void proces() {
            try {
                Thread.sleep(50);
                ClassLevelLock o = new ClassLevelLock();
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " is interrupted");
            } finally {
                latch.countDown();
            }
        }
    }
}


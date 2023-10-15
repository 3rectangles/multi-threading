package Threading;

// class level lock is imnmplemented using static object that is shared among all instances of class
public class ClassLevelLock {

    private static Object lock = new Object();
    private static int count =0 ; //keeps record of no of objects created of this class
    ClassLevelLock(){
        //The class's constructor increments count every time an object is created.
        // Since you've implemented a class-level lock,
        // this means only one object can be created at a time (due to synchronization).
        increment();
    }

        private  void increment() { // private method, object cant call this.
            synchronized (lock) { // lock is static object hence lock can be taken by only one object at a time
                count +=1; // prevents race condition
            }
        }

    public static int getCount() {
        return count;
    }

    public static void main(String[] args) {
        MyRunnable myrunnable = new MyRunnable();
        Thread[] arr = new Thread[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = new Thread(myrunnable);
            arr[i].start();
        }
        try{
            arr[7].interrupt(); // interrupts the 7th thread
            Thread.sleep(600); // this is important else main function thread will close and flow of control
            // will come out and other thread wont have made all the objects and we wil get 0 as ans
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(getCount()); // prints 9 bec 7th thread was interruoted
    }
}

class  MyRunnable implements Runnable{
    @Override
    public void run() {
        proces();
    }
    public void proces(){
        try{
            Thread.sleep(50);
            ClassLevelLock o =new ClassLevelLock();
            System.out.println(Thread.currentThread().getName()); // prints name of the current

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + "is interrupted");
        }

    }
}


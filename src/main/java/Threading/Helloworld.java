package Threading;

public class Helloworld {
    public static void main(String[] args) {
    //using classes
    printHelloWorld p = new printHelloWorld();
    Thread t = new Thread(p);
    t.start();
    System.out.println(Thread.currentThread().getName());

    // using anonymous classes can be use with classes and interfaces

        Runnable c1= new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world " + Thread.currentThread().getName());
            }
        };
        Thread t1 = new Thread(c1);
        t1.start();

    // using lambda function, can be use with functional interface.
        Runnable run = ()-> System.out.println("hello world from lambda function "+Thread.currentThread().getName());
        Thread t3 = new Thread( run);
        t3.start();
    }
}

class printHelloWorld implements  Runnable{
    @Override
    public void run() {
        System.out.println("hello world " + Thread.currentThread().getName());
    }
}
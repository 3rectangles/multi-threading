package Threading;

public class AddSubSynchronizedMethods {
    public static void main(String[] args) {
        ktimes(100); // every time the ans we get is 0

    }

    public static void ktimes(int k ){


        for (int i = 0; i < k; i++) {
            Count c = new Count();
            add adder = new add(c);
            sub subtractor = new sub(c);
            Thread t1 = new Thread(adder);
            Thread t2 = new Thread(subtractor);
            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(c.counter);  // Access counter using the synchronized getCounter method

        }


    }

    static class add implements Runnable {
        private Count count;
        add(Count c) {
            this.count = c;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                count.increment();
            }
        }
    }

    static class sub implements Runnable {
        private Count count;

        sub(Count c) {
            this.count = c;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                count.decrement();
            }
        }
    }

    static class Count {
        public int counter;

        public Count() {
            counter = 0;
        }

        public synchronized void increment() {
            counter++;
        }
        public synchronized void decrement() {
            counter--;
        }

    }
}

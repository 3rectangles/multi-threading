package Threading;

public class AddSub {
    public static void main(String[] args) {
        Count c= new Count();
        add adder = new add(c);
        sub subtractor = new sub(c);
        Thread t1 = new Thread(adder);

        Thread t2 = new Thread(subtractor);
        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(c.counter);  // value can be anythin bec of race condition


    }

   static class add implements Runnable{
        private Count count;
        add(Count c){
            this.count = c;
        }
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(10); //sleep this thread for 10 millisecs
                }
                catch ( InterruptedException e) // catch the interrupted exception
                {
                    System.out.println(e.getMessage());
                }

                count.counter +=1;
            }
        }
    }

    static class sub implements Runnable{
        private Count count;

        sub(Count c){
            this.count = c;
        }
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(10); //sleep this thread for 10 millisecs
                }
                catch ( InterruptedException e) // catch the interrupted exception
                {
                    System.out.println(e.getMessage());
                }

                count.counter -=1;
            }
        }
    }



    static class Count{
        public int counter;
        public Count(){
            counter =0;
        }
    }
}

package Threading;

public class PrintNum {
    public static void main(String[] args) {

        //usingAnonymousClass();
        // using normal class
        for (int i = 100; i < 200; i++) {
            number n = new number(i);
            Thread t = new Thread(n);
            t.start();
        }

    }

    static class number implements  Runnable{
        private  int i;
        public number(int i ){
            this.i = i;
        }
        @Override
        public void run(){
            System.out.println(i +"  "+  Thread.currentThread().getName() +"\n");

        }
    }

   static void usingAnonymousClass(){
        for (int i = 0; i < 100; i++) {
            int finalq = i;

            //creating task
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    // local variables referenced from an inner class must be final or effectively final
                    System.out.println(finalq + "  "+  Thread.currentThread().getName() +"\n");
                }
            };
            Thread t = new Thread(run);
            t.start();
        }
    }
}

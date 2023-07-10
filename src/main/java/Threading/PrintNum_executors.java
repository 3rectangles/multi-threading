package Threading;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class PrintNum_executors {
    public static void main(String[] args) {
        //defining thread pool  of 10 wprker threads
        ExecutorService ex= Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            number np =new number(i); //creating task
            ex.execute(np);  // giving task to executor
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
}

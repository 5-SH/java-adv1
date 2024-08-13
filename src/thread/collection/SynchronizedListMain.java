package thread.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class SynchronizedListMain {

    public static void main(String[] args) throws InterruptedException {
        List<String> list= Collections.synchronizedList(new ArrayList<>());
        test(list);
    }

    public static void test(List list) throws InterruptedException {
        log(list.getClass().getSimpleName());

        Runnable addA = new Runnable() {
            @Override
            public void run() {
                list.add("A");
                log("Thread-1: list.add(A)");
            }
        };

        Runnable addB = new Runnable() {
            @Override
            public void run() {
                list.add("B");
                log("Thread-2: list.add(B)");
            }
        };

        Thread thread1 = new Thread(addA, "Thread-1");
        Thread thread2 = new Thread(addB, "Thread-2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        log(list);
    }
}

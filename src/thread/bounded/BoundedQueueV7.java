package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static thread.util.MyLogger.log;

public class BoundedQueueV7 implements BoundedQueue {

    private BlockingQueue<String> queue;

    public BoundedQueueV7(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public String take() {
        try {
            return queue.poll(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void put(String data) {
        try {
            // 동작 확인을 위해 1 나노초로 설정
            boolean result = queue.offer(data, 1, TimeUnit.NANOSECONDS);
            log("저장 시도 결과 = " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}

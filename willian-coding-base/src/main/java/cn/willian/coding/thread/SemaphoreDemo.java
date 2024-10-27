package cn.willian.coding.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-06 22:30:33
 */
@Slf4j
public class SemaphoreDemo {

    public static void main(String[] args) {

        ParkingLot parkingLot = new ParkingLot(2);
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int carId = i;
            new Thread(() -> {
                parkingLot.in();
                log.info("车辆{},进入停车场", carId);
                try {
                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("车辆{},已经离开停车场", carId);
                parkingLot.out();
            }, "car" + i).start();
        }
    }

    static class ParkingLot {

        private final Semaphore semaphore;

        public ParkingLot(int maxCount) {
            // 车位数量
            semaphore = new Semaphore(maxCount);
        }

        public void in() {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public void out() {
            semaphore.release();
        }
    }
}

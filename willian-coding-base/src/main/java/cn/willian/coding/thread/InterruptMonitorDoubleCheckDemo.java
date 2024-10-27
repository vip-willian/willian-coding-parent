package cn.willian.coding.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-05 14:47:00
 */
@Slf4j
public class InterruptMonitorDoubleCheckDemo {

    public static void main(String[] args) throws InterruptedException {

        Monitor monitor = new Monitor();

        // 开启监控
        monitor.start();

        Thread.sleep(10000);

        // 停止监控
        monitor.stop();
    }

    static class Monitor {

        private Thread thread;

        // 开启监控
        public void start() {
            log.info("开始开启监控");
            thread = new Thread(() -> {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        log.info("监控线程已优雅退出");
                        break;
                    }
                    try {
                        log.info("正在监控中....");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // 重新设置打断标记
                        thread.interrupt();
                    }
                }
            });
            thread.start();
        }

        // 停止监控
        public void stop() {
            log.info("开始停止监控");
            thread.interrupt();
        }
    }
}

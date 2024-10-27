package cn.willian.coding.thread;

import java.lang.reflect.Field;

import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-07 14:16:11
 */
@Slf4j
public class ThreadLocalReferenceDemo {

    public static void main(String[] args) {

        new Thread(() -> set("aaa", false)).start();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            set("bbb", true);
        }).start();
    }

    private static void set(String value, boolean isGc) {
        // 这里new出来的ThreadLocal没有赋值给任意一个引用
        // new ThreadLocal<>().set(value);
         ThreadLocal<String> local = new ThreadLocal<>();
         local.set(value);
        if (isGc) {
            // GC调用后，key会被回收
            System.gc();
        }
        try {
            Thread thread = Thread.currentThread();
            // 获取字段
            Field threadLocalMapField = thread.getClass().getDeclaredField("threadLocals");
            threadLocalMapField.setAccessible(true);
            // 获取字段值
            Object threadLocalMap = threadLocalMapField.get(thread);

            Field threadLocalMapTableField = threadLocalMap.getClass().getDeclaredField("table");
            threadLocalMapTableField.setAccessible(true);
            Object[] entryTable = (Object[]) threadLocalMapTableField.get(threadLocalMap);

            for (Object entry : entryTable) {
                if (entry != null) {
                    Field valueField = entry.getClass().getDeclaredField("value");
                    Field referentField = entry.getClass().getSuperclass().getSuperclass().getDeclaredField("referent");
                    valueField.setAccessible(true);
                    referentField.setAccessible(true);
                    log.info("弱引用key:{},值:{}", referentField.get(entry), valueField.get(entry));
                }
            }
        } catch (Exception e) {
            // ignore
        }
    }
}

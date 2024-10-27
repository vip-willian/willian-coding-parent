package cn.willian.coding.thread;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:wangyan@cai-inc.com">沐风</a>
 * @datetime 2024-09-07 00:02:51
 */
public class TestDemo {

    private static final int COUNT_BITS = Integer.SIZE - 3;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    private final static AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    public static void main(String[] args) {
        System.out.println(ctl.get());
        System.out.println(COUNT_BITS);
        System.out.println(CAPACITY);
        System.out.println(RUNNING);
        System.out.println(SHUTDOWN);
        System.out.println(STOP);
        System.out.println(TIDYING);
        System.out.println(TERMINATED);

        int rs = runStateOf(ctl.get());
        System.out.println(rs);
        int wc = workerCountOf(ctl.get());
        System.out.println(wc);
    }

}

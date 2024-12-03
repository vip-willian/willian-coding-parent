package cn.willian.coding.leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-01 22:57:08
 */
public class BestArrange {

    public static void main(String[] args) {
    }

    // 举反例方式
    // 找到合适的贪心策略
    public static int bestArrange(Meeting[] meetings, long timePoint) {

        // 按会议结束时间排序
        Arrays.sort(meetings, new MeetingComparator());
        int result = 0;
        for (Meeting meeting : meetings) {
            if (timePoint <= meeting.getStartTime()) {
                result++;
                timePoint = meeting.getEndTime();
            }
        }
        return result;
    }

    @Data
    public static class Meeting {

        /**
         * 会议名称
         */
        private String name;
        /**
         * 会议开始时间
         */
        private long startTime;
        /**
         * 会议结束时间
         */
        private long endTime;

        public Meeting(String name, long startTime, long endTime) {
            this.name = name;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public static class MeetingComparator implements Comparator<Meeting> {

        @Override
        public int compare(Meeting o1, Meeting o2) {
            return (int)(o1.endTime - o2.endTime);
        }
    }

}

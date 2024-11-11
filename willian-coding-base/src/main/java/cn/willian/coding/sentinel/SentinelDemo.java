package cn.willian.coding.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.google.common.collect.Lists;

/**
 *  限流规则
 *  资源
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-02 11:00:21
 */
public class SentinelDemo {

    public static void main(String[] args) {

        initFlowRules();

        doRequest();
    }

    public static void doRequest(){

        while (true){
            try(Entry entry = SphU.entry("/api/user")) {
                System.out.println("开始执行任务");
            }catch (BlockException e){
                System.out.println("被限流啦");
            }
//            SphO.entry()
        }
    }

    private static void initFlowRules() {
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("/api/user");
        flowRule.setCount(10);
                flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        FlowRuleManager.loadRules(Lists.newArrayList(flowRule));
    }
}

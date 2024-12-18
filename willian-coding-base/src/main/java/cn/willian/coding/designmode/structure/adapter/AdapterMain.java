package cn.willian.coding.designmode.structure.adapter;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-17 23:12:39
 */
public class AdapterMain {
    public static void main(String[] args) {

        System.out.println("----对象适配器模式----");
        StandardAdapteePay standardAdapteePay = new StandardAdapteePay();
        TargetPay wxPay1 = new cn.willian.coding.designmode.structure.adapter.o.WxPayAdapter(standardAdapteePay);
        wxPay1.pay(10);
        TargetPay aliPay1 = new cn.willian.coding.designmode.structure.adapter.o.AliPayAdapter(standardAdapteePay);
        aliPay1.pay(10);

        System.out.println("----类适配器模式----");
        TargetPay wxPay2 = new cn.willian.coding.designmode.structure.adapter.e.WxPayAdapter();
        wxPay2.pay(10);
        TargetPay aliPay2 = new cn.willian.coding.designmode.structure.adapter.e.AliPayAdapter();
        aliPay2.pay(10);
    }
}

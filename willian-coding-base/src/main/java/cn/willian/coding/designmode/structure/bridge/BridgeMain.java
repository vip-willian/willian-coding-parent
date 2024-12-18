package cn.willian.coding.designmode.structure.bridge;

import cn.willian.coding.tools.XmlReadUtils;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 10:27:31
 */
public class BridgeMain {

    public static void main(String[] args) {

        String fileName = "system-image.xml";
        AbstractImage image = (AbstractImage)XmlReadUtils.getBean(fileName,"imageType");
        SystemImage system = (SystemImage)XmlReadUtils.getBean(fileName,"systemName");

        image.setSystemImage(system);

        image.parseFile("花");
    }
}

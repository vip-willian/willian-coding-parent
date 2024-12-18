package cn.willian.coding.designmode.structure.composite;

import cn.willian.coding.designmode.structure.composite.impl.ImageFile;
import cn.willian.coding.designmode.structure.composite.impl.TextFile;
import cn.willian.coding.designmode.structure.composite.impl.VideoFile;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 11:53:18
 */
public class CompositeMain {
    public static void main(String[] args) {

        FileComponent file1,file2,file3,file4,file5,folder1,folder2,folder3,folder4;

        folder1 = new FileHolder("Willian的资料");
        folder2 = new FileHolder("图像文件");
        folder3 = new FileHolder("文本文件");
        folder4 = new FileHolder("视频文件");

        file1 = new ImageFile("小龙女.jpg");
        file2 = new ImageFile("张无忌.gif");
        file3 = new TextFile("九阴真经.txt");
        file4 = new TextFile("葵花宝典.doc");
        file5 = new VideoFile("笑傲江湖.rmvb");

        folder2.add(file1);
        folder2.add(file2);
        folder3.add(file3);
        folder3.add(file4);
        folder4.add(file5);
        folder1.add(folder2);
        folder1.add(folder3);
        folder1.add(folder4);

        //从“Willian的资料”节点开始进行杀毒操作
        folder1.killVirus();
    }
}

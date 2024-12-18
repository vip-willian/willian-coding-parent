package cn.willian.coding.designmode.structure.composite.impl;

import cn.willian.coding.designmode.structure.composite.FileComponent;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 11:47:17
 */
public class ImageFile implements FileComponent {

    private final String name;

    public ImageFile(String name) {
        this.name = name;
    }

    @Override
    public void add(FileComponent fle) {
        System.out.println("暂不支持");
    }

    @Override
    public void remove(FileComponent fle) {
        System.out.println("暂不支持");
    }

    @Override
    public FileComponent get(int i) {
        System.out.println("暂不支持");
        return null;
    }

    @Override
    public void killVirus() {
        System.out.println("----对图片文件'" + name + "'进行杀毒");
    }
}

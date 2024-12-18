package cn.willian.coding.designmode.structure.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 11:50:15
 */
public class FileHolder implements FileComponent {

    private final String name;
    private final List<FileComponent> fileComponents;

    public FileHolder(String name) {
        this.name = name;
        this.fileComponents = new ArrayList<>();
    }

    @Override
    public void add(FileComponent fle) {
        fileComponents.add(fle);
    }

    @Override
    public void remove(FileComponent fle) {
        fileComponents.remove(fle);
    }

    @Override
    public FileComponent get(int i) {
        return fileComponents.get(i);
    }

    @Override
    public void killVirus() {
        System.out.println("对文件夹'" + name + "'进行查杀");
        fileComponents.forEach(FileComponent::killVirus);
    }
}

package cn.willian.coding.designmode.behavior.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-18 17:40:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Editor {

    /**
     * 编辑器文本内容
     */
    private String content;

    public void deleteSelection() {
        content = "";
    }

    public void insert(String addText) {
        content += addText;
    }
}

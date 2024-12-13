package cn.willian.coding.designmode.created.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.google.common.base.Throwables;

import lombok.Data;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-13 10:20:20
 */
@Data
public class WeekLog implements Cloneable, Serializable {

    private String name;
    private String date;
    private String content;
    private WeekAttachment attachment;

    @Override
    public WeekLog clone() {
        try {
            // 基于原型对象进行copy
            return (WeekLog)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public WeekLog deepClone() {

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(bos);
            outputStream.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream inputStream = new ObjectInputStream(bis);
            return (WeekLog)inputStream.readObject();
        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));
        }
        return null;
    }
}

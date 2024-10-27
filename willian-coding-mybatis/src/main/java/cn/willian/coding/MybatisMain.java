package cn.willian.coding;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-14 09:23:44
 */
public class MybatisMain {

    public static void main(String[] args) throws Exception {

        String resource = "mybatis-config.xml";
        // 默认从类的根路径下查找
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        sqlSession.insert("insertCar");

        // 手动提交
         sqlSession.commit();


    }
}

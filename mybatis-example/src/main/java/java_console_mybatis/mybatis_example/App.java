package java_console_mybatis.mybatis_example;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	SqlSession session = null;
        try {
            String resource = "mybatisConfig.xml";
            InputStream inputStream;
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            session = sqlSessionFactory.openSession();
            System.out.println(session.selectList("User.select"));

            User user = new User();
            user.setUsername("My name");
            session.insert("insertUser", user);
            session.commit();

            System.out.println(session.selectList("User.select"));

            Group group = new Group();
            group.setGroupname("group name");
            session.insert("insertGroup", group);
            session.commit();
            System.out.println(session.selectList("Group.select"));

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            session.close();
        }
        
        System.out.println( "Hello World!" );
    }
}

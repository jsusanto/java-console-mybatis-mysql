# java-console-mybatis-mysql
Java Console Mybatis MySQL using Eclipse IDE

# Step 1 - Add the following code in your App.java
<pre>
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
            String resource = "resources/mybatisConfig.xml";
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
</pre>

# Step 2 - Create Model and Mapper Classes
<pre>
package java_console_mybatis.mybatis_example;

public class Group {
    private int groupid;
    private String groupname;
    
    void setGroupid(int gid){
        groupid = gid;
    }
    
    int getGroupid(){
        return groupid;
    }

    void setGroupname(String gn){
        groupname = gn;
    }
    
    String getGroupname(){
        return groupname;
    }
}
</pre>
<hr/>
<pre>
package java_console_mybatis.mybatis_example;

public interface GroupMapper {
    Group select();
}
</pre>

<hr/>
<pre>
package java_console_mybatis.mybatis_example;

public class User {

    private int userid;
    private String username;
    private String email;
    private String tel;
    private String password;
    
    void setUserid(int uid){
        userid = uid;
    }
    
    void setUsername(String un){
        username = un;
    }
    
    void setEmail(String em){
        email = em;
    }
    
    void setTel(String tl){
        tel = tl;
    }
    
    void setPassword(String pw){
        password = pw;
    }
}
</pre>
<hr/>
<pre>
package java_console_mybatis.mybatis_example;

public interface UserMapper {
    User select();
    int insertUser(User user);
}
</pre>

# Set up configuration (mybatisConfig.xml)
```
<configuration>
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC"/>
      <dataSource type="POOLED">
        <property name="driver" value="com.mysql.jdbc.Driver"/>
	    <property name="url" value="jdbc:mysql://localhost:3306/test"/> 
        <property name="username" value="admin"/>
        <property name="password" value="admin1234"/>
      </dataSource>
    </environment>
  </environments>
  <mappers>
    <mapper resource="UserMapper.xml"/>
    <mapper resource="GroupMapper.xml"/>
  </mappers>
</configuration>
```

# Set up configuration (UserMapper.xml)
```
<mapper namespace="User">
  <select id="select" parameterType="java.util.HashMap" resultType="java.util.HashMap">
    SELECT
      USERID,
      USERNAME
    FROM
      USER
  </select>
  <select id="selectX" parameterType="String" resultType="String">
     SELECT
       USERNAME
     FROM
       USER
  </select>
  <insert
      id="insertUser"
      parameterType="java_console_mybatis.mybatis_example.User"
      flushCache="true"
      timeout="20">
  INSERT INTO USER (USERID, USERNAME)
  VALUES (#{userid}, #{username})

  </insert>
</mapper>
```

# Set up configuration (GroupMapper.xml)<h3></h3>
```
<mapper namespace="Group">
  <select id="select" parameterType="java.util.HashMap" resultType="java.util.HashMap">
    SELECT
       groupid,
       groupname
    FROM
      GROUPP
  </select>

  <insert id="insertGroup" 
          parameterType="java_console_mybatis.mybatis_example.Group"
          timeout="20">
    INSERT INTO GROUPP (GROUPID, GROUPNAME) VALUES(#{groupid}, #{groupname})

  </insert>

</mapper>
```

# Run the SQL Script
<pre>
CREATE TABLE USER (
  userid   INT NOT NULL	AUTO_INCREMENT,
  username VARCHAR(64) NOT NULL,
  PRIMARY KEY (userid));

CREATE TABLE GROUPP (
   groupid INT NOT NULL AUTO_INCREMENT,
   groupname VARCHAR(64) NOT NULL,
  PRIMARY KEY (groupid)
);
</pre>

# File Structure
![image](https://github.com/jsusanto/java-console-mybatis-mysql/assets/1523220/b16561da-f7b0-43ce-a28d-fb5cccedb1a8)

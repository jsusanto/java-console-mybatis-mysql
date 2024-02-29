package java_console_mybatis.mybatis_example;

public interface UserMapper {
    User select();
    int insertUser(User user);
}

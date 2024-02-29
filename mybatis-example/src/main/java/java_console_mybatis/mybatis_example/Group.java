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
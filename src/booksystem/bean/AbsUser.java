package booksystem.bean;
/**
 * 用户抽象类
 * 定义用户基本属性 用户名 用户id 用户角色
 */
public abstract class AbsUser {
    private int userId;
    private String role,username;
    public AbsUser(int userId, String role, String username) {
        this.userId = userId;
        this.role = role;
        this.username = username;
    }
    public int getUserId() { return userId; }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }
}

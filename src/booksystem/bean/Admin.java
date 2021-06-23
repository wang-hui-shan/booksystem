package booksystem.bean;

/** 管理员
 * 继承自抽象用户类User
 */
public class Admin extends AbsUser {
    public Admin(int userId, String role, String username) {
        super(userId, role, username);
    }
}

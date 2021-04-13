package booksystem.user;

/**
 * 普通用户
 * 继承自抽象用户类User
 */
public class User extends AbsUser {
    public User(int userId, String role, String username) {
        super(userId, role, username);
    }
}

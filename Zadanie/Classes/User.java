package Classes;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    private int id;
    private int group_id;
    private String userName;
    private String email;
    private String password;

    public User() {
    }

    public User(int group_id, String userName, String email, String password) {
        this.group_id = group_id;
        this.userName = userName;
        this.email = email;
        this.hashPassword(password);
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void hashPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
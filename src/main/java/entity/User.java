package entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class User {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String role;
    private int state;
    private String activationCode;
    private Timestamp updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Map<String, String> validation() {
        Map<String, String> map = new HashMap<>();
        if (username == null || username.trim().length() == 0) {
            map.put("register.username.error", "用户名不能为空");
        }
        if (password == null || password.trim().length() == 0) {
            map.put("register.password.error", "密码不能为空");
        }
        if (nickname == null || nickname.trim().length() == 0) {
            map.put("register.nickname.error", "昵称不能为空");
        }
        if (email == null || email.trim().length() == 0) {
            map.put("register.email.error", "邮箱不能为空");
        }
        return map;
    }
}

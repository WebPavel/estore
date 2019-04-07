package service;

import dao.UserDao;
import entity.User;
import exception.ActivateException;
import exception.RegisterException;
import utils.MailUtils;

import javax.mail.MessagingException;
import java.sql.SQLException;

public class UserService {
    private UserDao userDao = new UserDao();
    public void register(User user) throws RegisterException {
        try {
            userDao.add(user);
            // 向注册成功的用户发送激活邮件
            String emailMessage = "注册成功，请<a href='http://localhost:8080/user/activate?activationCode="+user.getActivationCode()+"'>激活</a>。激活码为：" + user.getActivationCode();
            MailUtils.sendMail(user.getEmail(), emailMessage);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RegisterException("注册失败");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void activate(String activationCode) {
        // 根据激活码查询用户，获取注册时间，判断激活码是否过期
        try {
            User user = userDao.findByActivationCode(activationCode);
            if (user != null) {
                long expires = System.currentTimeMillis() - user.getUpdateTime().getTime();
                System.out.println(expires);
                if (expires <= 1000*60*60*24) {
                    userDao.activate(activationCode);
                    return;
                }
            }
            throw new ActivateException();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ActivateException();
        }
    }
}

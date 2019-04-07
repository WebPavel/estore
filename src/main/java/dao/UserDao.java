package dao;

import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.DataSourceUtils;
import utils.MD5Utils;

import java.sql.SQLException;

public class UserDao {
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
    public int add(User user) throws SQLException {
        String sql = "insert into usr_user values(null, ?, ?, ?, ?, ?, ?, ?, null)";
        Object[] params = {user.getUsername(), MD5Utils.md5(user.getPassword()), user.getNickname(), user.getEmail(), "user", 0, user.getActivationCode()};
        return queryRunner.update(sql, params);
    }

    public User findByActivationCode(String activationCode) throws SQLException {
        String sql = "select * from usr_user where activationCode = ?";
        Object[] params = {activationCode};
        return queryRunner.query(sql, new BeanHandler<User>(User.class), params);
    }

    public void activate(String activationCode) throws SQLException {
        String sql = "update usr_user set state = ? where activationCode = ?";
        Object[] params = {1, activationCode};
        queryRunner.update(sql, params);
    }
}

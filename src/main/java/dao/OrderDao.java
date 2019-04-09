package dao;

import entity.Order;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.DataSourceUtils;
import utils.StringUtils;

import java.sql.SQLException;

public class OrderDao {
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

    public int add(Order order) throws SQLException {
        String sql = "insert into biz_order values(?, ?, ?, ?, null, ?)";
        Object[] params = {StringUtils.uuid(), order.getMoney(), order.getProfile(), order.getPayState(), order.getUserId()};
        return queryRunner.update(sql, params);
    }

    public Order findById(String id) throws SQLException {
        String sql = "select * from biz_order where id=?";
        Object[] params = {id};
        return queryRunner.query(sql, new BeanHandler<Order>(Order.class), params);
    }
}

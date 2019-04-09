package dao;

import entity.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DataSourceUtils;
import utils.StringUtils;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

    public int add(Product product) throws SQLException {
        String sql = "insert into biz_product values(?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {StringUtils.uuid(), product.getName(), product.getPrice(), product.getCategory(), product.getStock(), product.getImgUrl(), product.getDescription()};
        return queryRunner.update(sql, params);
    }

    public int update(Product product) throws SQLException {
        String sql = "update biz_product set name=?, price=?, category=?, stock=?, imgUrl=?, description=? where id = ?";
        Object[] params = {product.getName(), product.getPrice(), product.getCategory(), product.getStock(), product.getImgUrl(), product.getDescription(), product.getId()};
        return queryRunner.update(sql, params);
    }

    public Product findById(String id) throws SQLException {
        String sql = "select * from biz_product where id=?";
        Object[] params = {id};
        return queryRunner.query(sql, new BeanHandler<Product>(Product.class), params);
    }

    public List<Product> page(int pageIndex, int pageSize) throws SQLException {
        String sql = "select * from biz_product limit ?, ?";
        Object[] params = {(pageIndex-1)*pageSize, pageSize};
        return queryRunner.query(sql, new BeanListHandler<Product>(Product.class), params);
    }
    public long count() throws SQLException {
        String sql = "select count(*) from biz_product";
        return (Long) queryRunner.query(sql, new ScalarHandler<>());
    }
}

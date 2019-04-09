package service;

import dao.ProductDao;
import entity.Product;
import exception.ProductException;

import java.sql.SQLException;

public class ProductService {
    private ProductDao productDao = new ProductDao();
    public void add(Product product) throws ProductException {
        try {
            productDao.add(product);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ProductException("添加商品失败");
        }
    }
}

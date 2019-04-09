package entity;

import java.util.HashMap;
import java.util.Map;

public class Product {
    private String id;
    private String name;
    private Double price;
    private String category;
    private Integer stock;
    private String imgUrl;
    private String description;

    private Integer totalSales;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public Map<String, String> validation() {
        Map<String, String> map = new HashMap<>();
        if (name == null || name.trim().length() == 0) {
            map.put("product.name.error", "商品名称不能为空");
        }
        if (price == null || price < 0) {
            map.put("product.price.error", "价格不合格");
        }
        if (category == null || category.trim().length() == 0) {
            map.put("product.category.error", "分类不能为空");
        }
        if (stock == null || stock < 0) {
            map.put("product.stock.error", "库存不合格");
        }
        if (imgUrl == null || imgUrl.trim().length() == 0) {
            map.put("product.imgUrl.error", "图片路径不能为空");
        }
        if (description == null || description.trim().length() == 0) {
            map.put("product.description.error", "描述不能为空");
        }
        return map;
    }
}

package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.order.CheckoutData;
import com.codecool.shop.order.LineItem;
import com.codecool.shop.model.Product;

import java.util.List;

public class OrderService {
    private ProductDao productDao;
    private OrderDao orderDao;

    public OrderService(ProductDao productDao, OrderDao orderDao) {
        this.productDao = productDao;
        this.orderDao = orderDao;
    }


    public Product getProductById(int id) {
        return productDao.find(id);
    }

    public void addNewLineItem(LineItem product) {
        orderDao.add(product);
    }

    public int getCartSize() {
        return orderDao.getAll().size();
    }

    public List<LineItem> getAllItems() {
        return orderDao.getAll();
    }

    public LineItem getItemById(int id) {
        for (LineItem lineItem : orderDao.getAll()) {
            if (lineItem.getId() == id) {
                return lineItem;
            }
        }
        return null;
    }

    public void removeLineItem(int id) {
        orderDao.remove(id);
    }

    public void setQuantityForLineItem(int quantity, int id) {
        var item = orderDao.find(id);
        item.setQuantity(quantity);
    }

    public void setCheckoutData(CheckoutData checkoutData){
        orderDao.setCheckoutData(checkoutData);
    }
}

package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.order.CheckoutData;
import com.codecool.shop.order.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.order.Order;

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

    public void addNewOrder(Order order) {
        orderDao.add(order);
    }

    public void addNewLineItem(LineItem item, int orderId) {
        orderDao.find(orderId).add(item);
    }

    public int getCartSize(int orderId) {
        return orderDao.find(orderId).getAll().size();
    }

    public List<LineItem> getAllItems(int orderId) {
        return orderDao.find(orderId).getAll();
    }

    public LineItem getItemById(int ItemId, int orderId) {
        for (LineItem lineItem : orderDao.find(orderId).getAll()) {
            if (lineItem.getId() == ItemId) {
                return lineItem;
            }
        }
        return null;
    }

    public void removeLineItem(int ItemId, int orderId) {
        orderDao.find(orderId).remove(ItemId);
    }

    public void setQuantityForLineItem(int quantity, int ItemId, int orderId) {
        var item = orderDao.find(orderId).find(ItemId);
        item.setQuantity(quantity);
    }

    public void setCheckoutData(CheckoutData checkoutData, int orderId){
        orderDao.find(orderId).setCheckoutData(checkoutData);
    }

    public Order getOrder(int orderId) {
        return orderDao.find(orderId);
    }
}

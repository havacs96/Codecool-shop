package com.codecool.shop.servlet;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/add_to_cart"})
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var productDataStore = ProductDaoMem.getInstance();
        var orderDaoDataStore = OrderDaoMem.getInstance();
        var orderService = new OrderService(productDataStore, orderDaoDataStore);

        String id = req.getParameter("id");

        var product = orderService.getProductById(Integer.parseInt(id));

        try {
            var orderItem = orderService.getItemById(product.getId());
            orderItem.addLineItem();
        } catch (NullPointerException e) {
            var item = new LineItem(product);
            orderService.addNewLineItem(item);
        }

        int size = orderService.getCartSize();

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(size);
        out.flush();
    }
}

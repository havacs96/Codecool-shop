package com.codecool.shop.servlet;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.order.LineItem;
import com.codecool.shop.order.Order;
import com.codecool.shop.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/add_to_cart"})
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var productDataStore = ProductDaoMem.getInstance();
        var orderDaoDataStore = OrderDaoMem.getInstance();
        var orderService = new OrderService(productDataStore, orderDaoDataStore);
        Order order;
        HttpSession session = req.getSession(false);
        if (session != null) {
            int orderId = (int) session.getAttribute("orderId");
            order = orderService.getOrder(orderId);
        }
        else{
            session = req.getSession();
            order = new Order();
            orderService.addNewOrder(order);
            session.setAttribute("orderId", order.getId());
        }

        String id = req.getParameter("id");

        var product = orderService.getProductById(Integer.parseInt(id));

        try {
            var orderItem = orderService.getItemById(product.getId(), order.getId());
            orderItem.addLineItem();
        } catch (NullPointerException e) {
            var item = new LineItem(product);
            orderService.addNewLineItem(item, order.getId());
        }

        int size = orderService.getCartSize(order.getId());

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(size);
        out.flush();
    }
}

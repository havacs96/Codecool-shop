package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/cart_review"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var productDataStore = ProductDaoMem.getInstance();
        var orderDao = OrderDaoMem.getInstance();
        OrderService orderService = new OrderService(productDataStore, orderDao);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("items", orderService.getAllItems());
        context.setVariable("size", orderService.getAllItems().size());
        engine.process("product/cart_review.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var print = req.getParameterNames();
//        while ( print.hasMoreElements() ) {
//            Object o = print.nextElement();
//            System.out.println(o);
//            // Do whatever it is you want to do with o.
//        }
//        zip
//                firstname
//        cvv
//                address
//        city
//                cardname
//        state
//                cardnumber
//        sameadr
//                email


        resp.sendRedirect(req.getContextPath() + "/order");
    }
}
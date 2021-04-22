package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.mail.SendMail;
import com.codecool.shop.order.*;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.config.TemplateEngineUtil;
import com.google.gson.Gson;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileWriter;
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
        HttpSession session = req.getSession(false);
        if (session != null) {
            int orderId = (int) session.getAttribute("orderId");
            context.setVariable("items", orderService.getAllItems(orderId));
            context.setVariable("size", orderService.getAllItems(orderId).size());
        }
        engine.process("product/cart_review.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var productDataStore = ProductDaoMem.getInstance();
        var orderDao = OrderDaoMem.getInstance();
        OrderService orderService = new OrderService(productDataStore, orderDao);

        HttpSession session = req.getSession(false);
        if (session != null) {
            var name = req.getParameter("firstname");
            var email = req.getParameter("email");
            var address = req.getParameter("address");
            var country = req.getParameter("country");
            var city = req.getParameter("city");
            var zipcode = Integer.parseInt(req.getParameter("zip"));
            var sameAddress = req.getParameter("sameadr");

            var cardName = req.getParameter("cardname");
            var cardNumber = req.getParameter("cardnumber");
            var cvv = Integer.parseInt(req.getParameter("cvv"));
            Card card = new Card(cardNumber, cardName, cvv);
            BillingAddress billingAddress = new BillingAddress(country, zipcode, city, address);
            ShippingAddress shippingAddress;
            if (sameAddress.equals("off")) {
                var shipAddress = req.getParameter("saddress");
                var shipCountry = req.getParameter("scountry");
                var shipCity = req.getParameter("scity");
                var shipZipcode = Integer.parseInt(req.getParameter("szip"));
                shippingAddress = new ShippingAddress(shipCountry, shipZipcode, shipCity, shipAddress);
            } else {
                shippingAddress = new ShippingAddress(country, zipcode, city, address);
            }
            CheckoutData checkoutData = new CheckoutData(name, email, billingAddress, shippingAddress, card);
            int orderId = (int) session.getAttribute("orderId");
            orderService.getOrder(orderId).setCheckoutData(checkoutData);
            try (FileWriter file = new FileWriter("src/main/webapp/static/resources/" + orderId + ".json")) {
                String dataJson = new Gson().toJson(orderService.getOrder(orderId));
                file.write(dataJson);
            }
            SendMail.sendConformationMail(orderId);
        }
        resp.sendRedirect(req.getContextPath() + "/order");
    }
}
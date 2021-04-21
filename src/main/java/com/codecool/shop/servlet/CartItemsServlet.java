package com.codecool.shop.servlet;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.service.OrderService;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/cart_items"})
public class CartItemsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var productDataStore = ProductDaoMem.getInstance();
        var orderDaoDataStore = OrderDaoMem.getInstance();
        var orderService = new OrderService(productDataStore, orderDaoDataStore);

        var allItems = orderService.getAllItems();
        List<String> lineItemJson = new ArrayList<>();
        for (LineItem lineItem : allItems) {
            lineItemJson.add(new Gson().toJson(lineItem));
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(lineItemJson);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var productDataStore = ProductDaoMem.getInstance();
        var orderDaoDataStore = OrderDaoMem.getInstance();
        var orderService = new OrderService(productDataStore, orderDaoDataStore);

        var in = req.getReader();
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        JSONObject data = new JSONObject(content.toString());
        var quantity = data.getString("value");
        var id = data.getInt("current_id");

        var quantityInt = Integer.parseInt(quantity);

        if (quantityInt == 0) {
            orderService.removeLineItem(id);
        } else {
            orderService.setQuantityForLineItem(quantityInt, id);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("{\"log\": \"update done\"}");
        out.flush();
    }
}

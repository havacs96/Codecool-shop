package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var productDataStore = ProductDaoMem.getInstance();
        var productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        var supplierDao = SupplierDaoMem.getInstance();
        var orderDao = OrderDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore,supplierDao);
        OrderService orderService = new OrderService(productDataStore, orderDao);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("categories", productService.getAllProductCategories());
        context.setVariable("items", orderService.getAllItems());
        context.setVariable("size", orderService.getAllItems().size());
        context.setVariable("suppliers", productService.getAllProductSuppliers());
        context.setVariable("products", productService.getProductsForCategory(1));
        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());
    }
}

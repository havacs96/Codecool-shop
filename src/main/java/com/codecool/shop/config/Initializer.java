package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier razer = new Supplier("Razor", "Softwares and hardwares for your computer");
        supplierDataStore.add(razer);
        Supplier original = new Supplier("Original", "Special CodeCool products");
        supplierDataStore.add(original);

        //setting up a new product category
        ProductCategory hardware = new ProductCategory("Hardware", "Computer", "Hardware for your computer, that it deserves.");
        productCategoryDataStore.add(hardware);
        ProductCategory coolStuff = new ProductCategory("Cool stuff", "CodeCool", "Products that are going to say others: THIS GUY/GIRL KNOW PROGRAMMING!");
        productCategoryDataStore.add(coolStuff);
        ProductCategory shirt = new ProductCategory("shirt", "CodeCool", "Original CodeCool products");
        productCategoryDataStore.add(shirt);

        //setting up products and printing it
        productDataStore.add(new Product("Razor mouse", 28, "EUR", "Fantastic price. Beautiful white mouse with 3500 maximum DPI.", hardware, razer));
        productDataStore.add(new Product("CodeCoolest sunglass", 8, "EUR", "Comfortable sunglasses for hot summers.", coolStuff, original));
        productDataStore.add(new Product("CodeCoolers Backpack", 89, "EUR", "It is a perfect choice for you outdoor activities.", coolStuff, original));
        productDataStore.add(new Product("Terray shirt", 22, "EUR", "A marvelous shirt with our mentor's beautiful face on it. Good quality with handsomeness.", shirt, original));
        productDataStore.add(new Product("Casual CodeCool shirt", 18, "EUR", "A nice looking white shirt. Make your CodeCooler friends jealous wearing it!", shirt, original));
        productDataStore.add(new Product("CodeCool headset", 30, "EUR", "A black headset with microphone. You can hear your friends cleaner then ever!", hardware, razer));
    }
}

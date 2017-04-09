package com.altisource.pos.config;


import com.altisource.pos.domain.*;
import com.altisource.pos.exception.PosApplicationException;
import com.altisource.pos.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by rajeshkumar on 09/04/17.
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    TerritoryService territoryService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @Autowired
    BillService billService;

    @Override
    @Transactional
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        try {
            Territory bengaluruTerritory = new Territory(10, "Bengaluru", "Bengaluru Territory");
            Territory delhiTerritory = new Territory(20, "Delhi", "Delhi Territory");
            Territory mumbaiTerritory = new Territory(30, "Mumbai", "Mumbai Territory");

            Category applianceCareCategory = new Category(10, "Appliance", "Appliance Category");
            Category booksCategory = new Category(20, "Books", "Books Category");
            Category electronicsCategory = new Category(30, "Electronics", "Electronics Category");

            Product sandwichMaker = new Product("Sandwich Maker", "Black and Decker Sandwich Maker", 1000, applianceCareCategory);
            ProductOrder sandwichMakerProductOrder = new ProductOrder(sandwichMaker, 2);

            Product lotr = new Product("Lord Of the Rings", "Lord Of the Rings Book", 500, booksCategory);
            ProductOrder lotrProductOrder = new ProductOrder(lotr, 3);

            Product powerBank = new Product("MI Power Bank", "MI Power Bank", 2000, electronicsCategory);
            ProductOrder powerBankProductOrder = new ProductOrder(powerBank, 4);

            List<ProductOrder> productOrderList = new ArrayList<>();
            productOrderList.add(sandwichMakerProductOrder);
            productOrderList.add(lotrProductOrder);
            productOrderList.add(powerBankProductOrder);


            territoryService.createTerritory(delhiTerritory);
            territoryService.createTerritory(bengaluruTerritory);
            territoryService.createTerritory(mumbaiTerritory);

            categoryService.createCategory(applianceCareCategory);
            categoryService.createCategory(booksCategory);
            categoryService.createCategory(electronicsCategory);

            productService.createProduct(sandwichMaker);
            productService.createProduct(lotr);
            productService.createProduct(powerBank);

            Cart savedCart = cartService.createCart(new Cart(delhiTerritory, productOrderList));

            IntStream.range(0, 10).forEach(i -> {
                try {
                    billService.createBill(savedCart.getId());
                } catch (PosApplicationException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
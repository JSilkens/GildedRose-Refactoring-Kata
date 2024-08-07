package com.gildedrose.application;


import com.gildedrose.adapter.controller.InventoryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.gildedrose.adapter"})
public class Main implements CommandLineRunner {

    @Autowired
    private InventoryController inventoryController;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Welcome to Gilded Rose!");
        System.out.println("-----------------------");

        int days = 2;

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            inventoryController.updateSellin();
            inventoryController.updateAll();
        }

    }
}

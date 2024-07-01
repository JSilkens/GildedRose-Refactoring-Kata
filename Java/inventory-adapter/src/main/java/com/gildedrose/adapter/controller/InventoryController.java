package com.gildedrose.adapter.controller;


import com.gildedrose.usecase.UpdateInventoryUseCase;
import org.springframework.stereotype.Controller;

@Controller
public class InventoryController {

    private final UpdateInventoryUseCase updateInventoryUseCase;

    public InventoryController(UpdateInventoryUseCase updateInventoryUseCase) {
        this.updateInventoryUseCase = updateInventoryUseCase;
    }

    // CRUD methods

    public void updateAll() {
        this.updateInventoryUseCase.updateQuality();
    }


}

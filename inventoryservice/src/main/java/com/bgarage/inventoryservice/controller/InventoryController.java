package com.bgarage.inventoryservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bgarage.inventoryservice.aspect.LogExecutionTime;
import com.bgarage.inventoryservice.model.InventoryRestModel;
import com.bgarage.inventoryservice.repository.Inventory;
import com.bgarage.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    @LogExecutionTime
    public String addPart(@RequestBody InventoryRestModel inventoryRestModel) {
        Inventory inventory = inventoryService.create(inventoryRestModel);
        return "Part created with id : " + inventory.getId();
    }

    @GetMapping("/{supplier}")
    @LogExecutionTime
    public List<InventoryRestModel> getPartsBySupllier(@PathVariable("supplier") String supplier) {
        List<InventoryRestModel> inventoryList = inventoryService.getPartsBySupllier(supplier);
        return inventoryList;
    }

    @GetMapping("/{supplier}/{skucode}")
    @LogExecutionTime
    public InventoryRestModel getPartsBySupllierAndSkucode(@PathVariable("supplier") String supplier,@PathVariable("skucode") String skucode) {
        InventoryRestModel inventoryRestModel = inventoryService.getPartsBySupllierAndSkucode(supplier,skucode);
        return inventoryRestModel;
    }

    @GetMapping
    @LogExecutionTime
    public List<InventoryRestModel> getParts() {
        List<InventoryRestModel> inventoryList = inventoryService.getParts();
        return inventoryList;
    }

    @PutMapping
    @LogExecutionTime
    public InventoryRestModel updatePart(@RequestBody InventoryRestModel inventoryRestModel) {
        return inventoryService.update(inventoryRestModel);
    }

    @DeleteMapping("/{supplier}/{skucode}")
    @LogExecutionTime
    public String deletePart(@PathVariable("supplier") String supplier, @PathVariable("skucode") String skuCode) {
        inventoryService.delete(supplier, skuCode);
        return "Part deleted for supplier " + supplier + " with skucode : " + skuCode;
    }

}

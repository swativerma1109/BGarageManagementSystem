package com.bgarage.inventoryservice.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bgarage.inventoryservice.aspect.LogExecutionTime;
import com.bgarage.inventoryservice.dto.InventoryResponse;
import com.bgarage.inventoryservice.model.InventoryRestModel;
import com.bgarage.inventoryservice.repository.Inventory;
import com.bgarage.inventoryservice.repository.InventoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @LogExecutionTime
    public Inventory create(InventoryRestModel inventoryRestModel) {
        Inventory inventory = new Inventory();
        BeanUtils.copyProperties(inventoryRestModel, inventory);
        inventory = inventoryRepository.save(inventory);
        return inventory;
    }

    @LogExecutionTime
    public List<InventoryRestModel> getParts() {
        List<Inventory> inventoryList = new ArrayList<Inventory>();
        List<InventoryRestModel> inventoryRestList = new ArrayList<InventoryRestModel>();
        inventoryList = inventoryRepository.findAll();
        for (Inventory inventory : inventoryList) {
            InventoryRestModel inventoryRestModel = new InventoryRestModel();
            BeanUtils.copyProperties(inventory, inventoryRestModel);
            inventoryRestList.add(inventoryRestModel);
        }
        return inventoryRestList;
    }

    @LogExecutionTime
    public InventoryRestModel update(InventoryRestModel inventoryRestModel) {
        Inventory inventory = new Inventory();
        BeanUtils.copyProperties(inventoryRestModel, inventory);
        inventory = inventoryRepository.save(inventory);
        return inventoryRestModel;
    }

    @LogExecutionTime
    public void delete(String supplier, String skuCode) {
        inventoryRepository.deleteBySupplierAndSkuCode(supplier, skuCode);
    }

    @LogExecutionTime
    public List<InventoryRestModel> getPartsBySupllier(String supplier) {
        List<Inventory> inventoryList = new ArrayList<Inventory>();
        List<InventoryRestModel> inventoryRestList = new ArrayList<InventoryRestModel>();
        inventoryList = inventoryRepository.findBySupplier(supplier);
        for (Inventory inventory : inventoryList) {
            InventoryRestModel inventoryRestModel = new InventoryRestModel();
            BeanUtils.copyProperties(inventory, inventoryRestModel);
            inventoryRestList.add(inventoryRestModel);
        }
        return inventoryRestList;
    }

    @LogExecutionTime
    public InventoryRestModel getPartsBySupllierAndSkucode(String supplier, String skucode) {
        InventoryRestModel inventoryRestModel = new InventoryRestModel();
        Inventory inventory = inventoryRepository.findBySupplierAndSkuCode(supplier, skucode);
        BeanUtils.copyProperties(inventory, inventoryRestModel);
        return inventoryRestModel;

    }

}
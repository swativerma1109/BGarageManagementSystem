package com.bgarage.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findBySkuCodeIn(List<String> skuCode);

    void deleteBySupplierAndSkuCode(String supplier, String skuCode);

    List<Inventory> findBySupplier(String supplier);

    Inventory findBySupplierAndSkuCode(String supplier, String skucode);
}
package com.bgarage.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.bgarage.inventoryservice.repository.Inventory;
import com.bgarage.inventoryservice.repository.InventoryRepository;

@EnableDiscoveryClient
@SpringBootApplication
public class InventoryserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryserviceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("Part1");
			inventory1.setSupplier("Supplier-A");
			inventory1.setQuantity(1000);
			inventory1.setMinOrderQuantity(10);
			inventory1.setThresholdLimit(50);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("Part2");
			inventory2.setSupplier("Supplier-A");
			inventory2.setQuantity(5000);
			inventory2.setMinOrderQuantity(50);
			inventory2.setThresholdLimit(100);

			Inventory inventory3 = new Inventory();
			inventory3.setSkuCode("Part3");
			inventory3.setSupplier("Supplier-A");
			inventory3.setQuantity(100);
			inventory3.setMinOrderQuantity(2);
			inventory3.setThresholdLimit(5);

			Inventory inventory4 = new Inventory();
			inventory4.setSkuCode("Part1");
			inventory4.setSupplier("Supplier-B");
			inventory4.setQuantity(1000);
			inventory4.setMinOrderQuantity(10);
			inventory4.setThresholdLimit(50);

			Inventory inventory5 = new Inventory();
			inventory5.setSkuCode("Part2");
			inventory5.setSupplier("Supplier-B");
			inventory5.setQuantity(5000);
			inventory5.setMinOrderQuantity(50);
			inventory5.setThresholdLimit(100);

			Inventory inventory6 = new Inventory();
			inventory6.setSkuCode("Part3");
			inventory6.setSupplier("Supplier-B");
			inventory6.setQuantity(100);
			inventory6.setMinOrderQuantity(10);
			inventory6.setThresholdLimit(5);

			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
			inventoryRepository.save(inventory3);
			inventoryRepository.save(inventory4);
			inventoryRepository.save(inventory5);
			inventoryRepository.save(inventory6);
		};
	}

}

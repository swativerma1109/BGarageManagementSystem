package com.bgarage.orderservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.bgarage.orderservice.aspect.LogExecutionTime;
import com.bgarage.orderservice.exception.MinimumOrderQuantityException;
import com.bgarage.orderservice.exception.OutOfStockException;
import com.bgarage.orderservice.model.InventoryRestModel;
import com.bgarage.orderservice.model.OrderRestModel;
import com.bgarage.orderservice.repository.Order;
import com.bgarage.orderservice.repository.OrderRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class OrderService {

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final Environment env;
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    @LogExecutionTime
    public String placeOrder(OrderRestModel orderRestModel) {
        logger.trace(null);
        String supplier = orderRestModel.getSupplier();
        Order order = new Order();

        BeanUtils.copyProperties(orderRestModel, order);

        if (supplier.equals("Supplier-B") && !duringDiscountTime()) {
            return "Order should be placed between 12:00 AM to 01:00 AM";
        } else {
            // check the inventory for in stock order
            String skuCode = orderRestModel.getSkuCode();
            InventoryRestModel inventoryRestModel = webClientBuilder.build().get()
                    .uri(env.getProperty("inventoryservice.url"),
                            uriBuilder -> uriBuilder.path("{supplier}/{skucode}")
                                    .build(supplier, skuCode))
                    .retrieve()
                    .bodyToMono(InventoryRestModel.class)
                    .block();
            int availableQuantity = inventoryRestModel.getQuantity() - order.getQuantity();
            if (inventoryRestModel == null
                    || (inventoryRestModel != null && order.getQuantity() > inventoryRestModel.getQuantity())) {
                throw new OutOfStockException("Out of stock, please try again later...");
            } else if (inventoryRestModel != null && order.getQuantity() < inventoryRestModel.getMinOrderQuantity()) {
                throw new MinimumOrderQuantityException(
                        "Minimum order quantity for this part is : " + inventoryRestModel.getMinOrderQuantity());

            } else {
                orderRepository.save(order);
                if (availableQuantity <= inventoryRestModel.getThresholdLimit()) {
                    orderPartForMinimumQuantity(inventoryRestModel);

                }
                return "Order Placed successfully!!";
            }
        }

    }

    @LogExecutionTime
    private void orderPartForMinimumQuantity(InventoryRestModel inventoryRestModel) {
        // Logic for ordering part with minimum quantity

    }

    @LogExecutionTime
    private boolean duringDiscountTime() {
        // Logic to check if Order placed between 12:00 AM to 01:00 AM
        return true;
    }

}

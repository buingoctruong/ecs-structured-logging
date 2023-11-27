package github.io.truongbn.logstructura.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import github.io.truongbn.logstructura.entity.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {
    // Acting as the order database in the demonstration.
    private final Map<Long, Order> orders = new HashMap<>();
    @PostMapping("/place-order")
    public Order placeOrder(@RequestBody Order order) {
        if (StringUtils.isEmpty(order.getCustomerName()) || order.getTotalAmount() == null) {
            log.error("Order placement failed: Missing customer name or total amount.");
            return null;
        }
        long orderId = ThreadLocalRandom.current().nextLong(1000, 9999);
        if (orders.containsKey(orderId)) {
            log.error("Order with the same ID <{}> already exists.", orderId);
            return null;
        }
        order.setId(orderId);
        orders.put(orderId, order);
        log.info("Order placed: ID=<{}>, Customer=<{}>, TotalAmount=<{}>", orderId,
                order.getCustomerName(), order.getTotalAmount());
        return order;
    }
}

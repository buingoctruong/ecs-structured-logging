package github.io.truongbn.logstructura.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private String customerName;
    private BigDecimal totalAmount;
}

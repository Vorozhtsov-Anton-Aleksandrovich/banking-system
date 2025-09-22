package com.bankx.clientprocessing.kafka.event;

import com.bankx.clientprocessing.entity.enums.ProductKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientProductEvent {
    Long ClientId;
    Long ProductId;
    ProductKey productKey;
}

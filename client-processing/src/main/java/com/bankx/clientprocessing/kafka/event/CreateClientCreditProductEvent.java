package com.bankx.clientprocessing.kafka.event;

import com.bankx.clientprocessing.entity.enums.ProductKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// пока ивенты идентичны, тк в задании есть вопросы, но в последствии я спрошу и видоизмени, если вдруг что
@Data
@AllArgsConstructor
public class CreateClientCreditProductEvent {
    Long ClientId;
}

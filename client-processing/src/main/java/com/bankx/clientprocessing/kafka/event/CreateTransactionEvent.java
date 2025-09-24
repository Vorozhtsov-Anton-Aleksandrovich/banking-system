package com.bankx.clientprocessing.kafka.event;


import com.bankx.clientprocessing.entity.enums.TransactionStatus;
import lombok.*;

@Data
@NoArgsConstructor
@Getter @Setter
public class CreateTransactionEvent {
    private Long accountId;
    private Long cardId;
    private String type;
    private Double amount;
    private TransactionStatus transactionStatus;
}

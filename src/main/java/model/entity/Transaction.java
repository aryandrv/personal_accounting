package model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import enums.TypeEnum;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@ToString

public class Transaction {
    private int id;
    private TypeEnum type;
    private LocalDateTime transactionDate;



}

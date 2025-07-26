package model.entity;


import enums.TypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@ToString
public class TitleSummary {
    private int titleId;
    private String titleName;
    private double totalAmount;
    private int transactionCount;
    private TypeEnum type;

}
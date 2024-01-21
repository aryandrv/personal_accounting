package model.entity;

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

public class Account {
    private int id;
    private String name;
    private double balance;
    private User user;


}

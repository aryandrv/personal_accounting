package model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import enums.TypeEnum;


@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@ToString

public class Titles {
    private int id;
    private String name;
    private TypeEnum type;
}

package vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatsDto {
    private String Name;

    private Integer Age;

    private Integer ShelterId;
}

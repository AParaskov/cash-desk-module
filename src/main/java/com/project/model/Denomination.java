package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Denomination {
    @Min(1)
    private int quantity;
    @Pattern(regexp = "^(5|10|20|50|100) (BGN|EUR)")
    private String cashAmount;
}

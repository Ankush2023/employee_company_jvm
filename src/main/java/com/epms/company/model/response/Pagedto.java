package com.epms.company.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pagedto {
    private Long totalElement;
    private Object dataset;
    private Integer totalNumber;
    private Integer totalPages;
}

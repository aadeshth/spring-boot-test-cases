package com.springboot.crudoperation.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {
    String searchText;

}

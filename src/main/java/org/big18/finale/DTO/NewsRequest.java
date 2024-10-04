package org.big18.finale.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class NewsRequest {
    private List<String> titles;
    private String keyword;

}

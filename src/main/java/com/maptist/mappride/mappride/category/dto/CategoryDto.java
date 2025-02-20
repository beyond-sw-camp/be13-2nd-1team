package com.maptist.mappride.mappride.category.dto;

import com.maptist.mappride.mappride.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private String name;
    private boolean isPublic;

    public Category toCategory() {
        return Category.builder()
                .name(name)
                .isPublic(isPublic)
                .build();
    }


}


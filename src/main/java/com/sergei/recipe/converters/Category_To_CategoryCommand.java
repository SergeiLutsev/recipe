package com.sergei.recipe.converters;

import com.sergei.recipe.commands.CategoryCommand;
import com.sergei.recipe.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class Category_To_CategoryCommand implements Converter<Category, CategoryCommand> {

    @Override
    public CategoryCommand convert(Category category) {
        if(category==null) {
            return null;
        }
        final  CategoryCommand categoryCommand=new CategoryCommand();
        categoryCommand.setId(category.getId());
        categoryCommand.setDescription(category.getDescription());

        return categoryCommand;
    }
}

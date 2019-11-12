package com.sergei.recipe.converters;

import com.sergei.recipe.commands.UnitOfMeasureCommand;
import com.sergei.recipe.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasure_To_UnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {
    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if(source==null) {
            return null;
        }

        final UnitOfMeasureCommand uCom=new UnitOfMeasureCommand();
        uCom.setId(source.getId());
        uCom.setDescription(source.getDescription());

        return uCom;
    }
}

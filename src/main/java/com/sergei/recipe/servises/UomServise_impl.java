package com.sergei.recipe.servises;

import com.sergei.recipe.commands.UnitOfMeasureCommand;
import com.sergei.recipe.converters.UnitOfMeasure_To_UnitOfMeasureCommand;
import com.sergei.recipe.repositories.UnitOfMesureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UomServise_impl implements UomServise {
    final private UnitOfMesureRepository unitOfMesureRepository;
    final private UnitOfMeasure_To_UnitOfMeasureCommand unitOfMeasure_to_unitOfMeasureCommand;
    public UomServise_impl(UnitOfMesureRepository unitOfMesureRepository, UnitOfMeasure_To_UnitOfMeasureCommand unitOfMeasure_to_unitOfMeasureCommand) {
        this.unitOfMesureRepository = unitOfMesureRepository;
        this.unitOfMeasure_to_unitOfMeasureCommand = unitOfMeasure_to_unitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> getAllUomComand() {
        Set<UnitOfMeasureCommand> uoms=new HashSet<>();
        unitOfMesureRepository.findAll().forEach(um->{
            uoms.add(unitOfMeasure_to_unitOfMeasureCommand.convert(um));
        });

        return uoms;
    }
}

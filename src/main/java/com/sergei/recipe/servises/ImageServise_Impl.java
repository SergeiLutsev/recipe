package com.sergei.recipe.servises;

import com.sergei.recipe.domain.Recipe;
import com.sergei.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServise_Impl implements ImageServise {
    private final RecipeRepository recipeRepository;

    public ImageServise_Impl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Transactional
    @Override
    public void saveImageFile(Long recipId, MultipartFile file) {

        Recipe recipe=recipeRepository.findById(recipId).get();
        try {
            Byte[] byteObject=new Byte[file.getBytes().length];
            int i=0;
            for(byte b:file.getBytes()){
                byteObject[i++]=b;
            }
            recipe.setImage(byteObject);
            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error("error occurred "+e);
            e.printStackTrace();
        }
    }
}

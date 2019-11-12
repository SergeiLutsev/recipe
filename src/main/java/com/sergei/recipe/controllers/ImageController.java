package com.sergei.recipe.controllers;

import com.sergei.recipe.commands.RecipeCommand;
import com.sergei.recipe.servises.ImageServise;
import com.sergei.recipe.servises.RecipeServise;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {
    private final RecipeServise recipeServise;
    private final ImageServise imageServise;

    public ImageController(RecipeServise recipeServise, ImageServise imageServise) {
        this.recipeServise = recipeServise;
        this.imageServise = imageServise;
    }

    @GetMapping({"/recipe/{recipeId}/changeimage"})
    public String openImageChooseForm(@PathVariable String recipeId,
                                      Model model){
        model.addAttribute("recipe",recipeServise.findById(Long.valueOf(recipeId)));
        return "recipe/imageuploadform";
    }
    @PostMapping({"/recipe/{recipeId}/image"})
    public String openImageChooseForm(@PathVariable String recipeId,
                                      @RequestParam("imageFile") MultipartFile file){
        imageServise.saveImageFile(Long.valueOf(recipeId),file);
        return "redirect:/recipe/"+recipeId+"/show";
    }

    /*
    request from recipe/ show
     th:src="@{'/recipe/' + ${recipe.id} + '/recipeimage'}"
     */
    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand=recipeServise.findCommandById(Long.valueOf(id));

        if(recipeCommand.getImage()!=null) {
            byte[] byteArray = new byte[recipeCommand.getImage().length];
            int i = 0;
            for (Byte wraperByte : recipeCommand.getImage()) {
                byteArray[i++] = wraperByte;
            }
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }

}

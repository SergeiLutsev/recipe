package com.sergei.recipe.servises;

import org.springframework.web.multipart.MultipartFile;

public interface ImageServise {
    void saveImageFile(Long recipId, MultipartFile file);
}

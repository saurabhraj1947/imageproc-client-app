package com.seattleu.imageprocclientapp.service;

import com.seattleu.imageprocessor.client.api.ImageProcessorControllerApi;
import com.seattleu.imageprocessor.client.model.EntityModelImageProcessingDetail;
import com.seattleu.imageprocessor.client.model.ImageProcessingDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    private ImageProcessorControllerApi imageProcessorControllerApi;

    public String transformImage(ImageProcessingDetail imageProcessingDetail ) {
        EntityModelImageProcessingDetail result = imageProcessorControllerApi.transformImageUsingPOST(imageProcessingDetail);
        return result.getImage();
    }
}

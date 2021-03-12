package com.seattleu.imageprocclientapp;

import com.seattleu.imageprocclientapp.config.AppConfig;
import com.seattleu.imageprocclientapp.service.ImageService;
import com.seattleu.imageprocessor.client.model.ImageProcessingDetail;
import com.seattleu.imageprocessor.client.model.Transformation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Import(AppConfig.class)
public class ImageprocClientAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImageprocClientAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ImageService imageService) throws IOException {
        return args -> {
            Transformation transformation = new Transformation();
            transformation.setTransFormationType(Transformation.TransFormationTypeEnum.FLIP);
            transformation.setFlipType(Transformation.FlipTypeEnum.HORIZONTAL);
            List<Transformation> transformations = Arrays.asList(transformation);

            String imagePath = "/Users/sraj/Documents/kiki.jpeg";
            BufferedImage bufferedImage = ImageIO.read(new File(imagePath));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ImageIO.write(bufferedImage, "jpeg", baos );
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte [] imageAsBytes = baos.toByteArray();

            ImageProcessingDetail imageProcessingDetail = new ImageProcessingDetail();
            imageProcessingDetail.setImage(ImageUtil.getImageAsBase64(imageAsBytes));
            imageProcessingDetail.setTransformations(transformations);

            String imageAsBase64 = imageService.transformImage(imageProcessingDetail);
            ByteArrayInputStream bis = new ByteArrayInputStream(ImageUtil.getImageAsBytes(imageAsBase64));
            BufferedImage outputImage = null;
            try {
                outputImage = ImageIO.read(bis);
                ImageIO.write(outputImage, "jpeg", new File("/Users/sraj/Documents/transformed.jpeg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

}

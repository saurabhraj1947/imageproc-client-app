package com.seattleu.imageprocclientapp;

import java.util.Base64;

public class ImageUtil {
    public static String getImageAsBase64(byte[] image) {
        return Base64.getEncoder().encodeToString(image);
    }

    public static byte[] getImageAsBytes(String image) {
        return Base64.getDecoder().decode(image);
    }
}

package com.gambaru.gambaru.cms.application.service.generators;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
public class BarcodeGenerator {
    public static BufferedImage generateBarcodeImage(String barcodeText, int width, int height) throws WriterException {
        Code128Writer barcodeWriter = new Code128Writer();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.CODE_128, width, height, hints);
        return createBufferedImage(bitMatrix);
    }

    private static BufferedImage createBufferedImage(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int black = 0xFF000000;
        int white = 0xFFFFFFFF;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = bitMatrix.get(x, y) ? black : white;
                image.setRGB(x, y, pixel);
            }
        }

        return image;
    }
}
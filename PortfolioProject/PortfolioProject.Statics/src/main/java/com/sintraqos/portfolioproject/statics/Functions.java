package com.sintraqos.portfolioproject.statics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Functions {

    private Functions() {
        throw new IllegalStateException("Utility class");
    }

    //region Slice Image

    public static BufferedImage sliceImage(Image image, int imageWidth,int imageHeight) {
        // First slice the image

        GameSettings settings = GameSettings.getInstance();

        BufferedImage[] slicedImage = sliceImage(new ImageIcon(image.getScaledInstance(settings.getDefaultIconSize(), settings.getDefaultIconSize(), Image.SCALE_SMOOTH)));
        BufferedImage combinedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        // Image Array Positions:
        // 0: Top Left
        // 1: Top Middle
        // 2: Top Right
        // 3: Middle Left
        // 4: Middle Middle
        // 5: Middle Right
        // 6: Bottom Left
        // 7: Bottom Middle
        // 8: Bottom Right
        int padding = slicedImage[0].getHeight();

        Graphics g = combinedImage.getGraphics();
        g.drawImage(new ImageIcon(slicedImage[4].getScaledInstance(imageWidth - padding, imageHeight - padding, Image.SCALE_SMOOTH)).getImage(), padding / 2, padding / 2, null);
        g.drawImage(new ImageIcon(slicedImage[1].getScaledInstance(imageWidth - padding, slicedImage[1].getHeight(), Image.SCALE_SMOOTH)).getImage(), padding / 2, 0, null);
        g.drawImage(new ImageIcon(slicedImage[7].getScaledInstance(imageWidth - padding, slicedImage[7].getHeight(), Image.SCALE_SMOOTH)).getImage(), padding / 2, imageHeight -slicedImage[1].getHeight(), null);
        g.drawImage(new ImageIcon(slicedImage[3].getScaledInstance(slicedImage[3].getWidth(), imageHeight - padding, Image.SCALE_SMOOTH)).getImage(), 0, padding / 2, null);
        g.drawImage(new ImageIcon(slicedImage[5].getScaledInstance(slicedImage[5].getWidth(), imageHeight - padding, Image.SCALE_SMOOTH)).getImage(), imageWidth - slicedImage[5].getWidth(), padding / 2, null);
        g.drawImage(slicedImage[0], 0, 0, null);
        g.drawImage(slicedImage[2], imageWidth - slicedImage[2].getWidth(), 0, null);
        g.drawImage(slicedImage[6], 0, imageHeight - slicedImage[6].getHeight(), null);
        g.drawImage(slicedImage[8], imageWidth - slicedImage[2].getWidth(), imageHeight - slicedImage[8].getHeight(), null);
        g.dispose();

        return combinedImage;
    }

    public static BufferedImage[] sliceImage(ImageIcon image) {
        BufferedImage bufferedImage = new BufferedImage(image.getIconWidth(), image.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.createGraphics();
        image.paintIcon(null, graphics, 0, 0);
        graphics.dispose();

        BufferedImage[] slicedImage = new BufferedImage[9];

        int imageWidth = bufferedImage.getWidth();
        int imageHeight = bufferedImage.getHeight();
        int slicedImageWidth = bufferedImage.getWidth() / 3;
        int slicedImageHeight = bufferedImage.getHeight() / 3;

        // Top
        slicedImage[0] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, 0, 0, slicedImageWidth, slicedImageHeight);
        slicedImage[1] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, slicedImageWidth, 0, slicedImageWidth * 2, slicedImageHeight);
        slicedImage[2] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, imageWidth - slicedImageWidth, 0, imageWidth, slicedImageHeight);

        // Middle
        slicedImage[3] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, 0, slicedImageHeight, slicedImageWidth, slicedImageHeight * 2);
        slicedImage[4] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, slicedImageWidth, slicedImageHeight, slicedImageWidth * 2, slicedImageHeight * 2);
        slicedImage[5] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, imageWidth - slicedImageWidth, slicedImageHeight, imageWidth, slicedImageHeight * 2);

        // Bottom
        slicedImage[6] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, 0, imageHeight - slicedImageHeight, slicedImageWidth, imageHeight);
        slicedImage[7] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, slicedImageWidth, imageHeight - slicedImageHeight, imageWidth - slicedImageWidth, imageHeight);
        slicedImage[8] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, imageWidth - slicedImageWidth, imageHeight - slicedImageHeight, imageWidth, imageHeight);

        return slicedImage;
    }

    static BufferedImage sliceImage(BufferedImage bufferedImage, int imageWidth, int imageHeight, int sourceFirstX, int sourceFirstY, int dstCornerX, int dstCornerY) {
        BufferedImage returnImage = new BufferedImage(imageWidth, imageHeight, bufferedImage.getType());
        Graphics2D imgCreator = returnImage.createGraphics();
        imgCreator.drawImage(bufferedImage, 0, 0, imageWidth, imageHeight, sourceFirstX, sourceFirstY, dstCornerX, dstCornerY, null);

        return returnImage;
    }

    //endregion

    //region File names

    public static String getFileNameWithoutExtension(String fileName) {
        if (fileName.indexOf(".") >= 1) {
            return fileName.substring(0, fileName.lastIndexOf("."));
        } else {
            return fileName;
        }
    }

    //endregion

    //region Exceptions

  public static class ExceptionHandler extends RuntimeException {
        public ExceptionHandler(String message, Throwable cause) {
            System.out.println(message + ": " + cause.getMessage());
        }
    }

    //endregion
}

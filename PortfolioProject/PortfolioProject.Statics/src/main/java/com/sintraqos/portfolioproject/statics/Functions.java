package com.sintraqos.portfolioproject.statics;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Functions {

    private Functions() {
        throw new IllegalStateException("Utility class");
    }

    //region Slice Image

    public static BufferedImage sliceImage(Image image, int imageWidth, int imageHeight) {
        // First slice the image

        GameSettings settings = GameSettings.getInstance();

        // Get the image sliced, and return it into an BufferedImage array
        // Create a new buffered image which we can edit

        ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(settings.getDefaultIconSize(), settings.getDefaultIconSize(), Image.SCALE_SMOOTH));

        BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.createGraphics();
        imageIcon.paintIcon(null, graphics, 0, 0);
        // And dispose of the graphics since we will overwrite it later on
        graphics.dispose();

        // Create a new array, since we know it needs to contain 9 parts a list isn't necessary
        BufferedImage[] slicedImage = new BufferedImage[9];

        // Get the needed image sizes
        int bufferedImageWidth = bufferedImage.getWidth();
        int bufferedImageHeight = bufferedImage.getHeight();
        // Get the needed slicedImage sizes
        int slicedImageWidth = bufferedImage.getWidth() / 3;
        int slicedImageHeight = bufferedImage.getHeight() / 3;

        // NOTE:
        // The reason we don't use slicedImageHeight * 3 is because we need to be certain we get all the pixels from the bottom, since 64 / 3 = 21,33, which gets rounded down to 21
        // imageHeight - slicedImageHeight gives a proper pixel position

        // Top from left to right
        slicedImage[0] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, 0, 0, slicedImageWidth, slicedImageHeight);
        slicedImage[1] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, slicedImageWidth, 0, slicedImageWidth * 2, slicedImageHeight);
        slicedImage[2] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, bufferedImageWidth - slicedImageWidth, 0, bufferedImageWidth, slicedImageHeight);

        // Middle from left to right
        slicedImage[3] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, 0, slicedImageHeight, slicedImageWidth, slicedImageHeight * 2);
        slicedImage[4] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, slicedImageWidth, slicedImageHeight, slicedImageWidth * 2, slicedImageHeight * 2);
        slicedImage[5] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, bufferedImageWidth - slicedImageWidth, slicedImageHeight, bufferedImageWidth, slicedImageHeight * 2);

        // Bottom from left to right
        slicedImage[6] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, 0, bufferedImageHeight - slicedImageHeight, slicedImageWidth, bufferedImageHeight);
        slicedImage[7] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, slicedImageWidth, bufferedImageHeight - slicedImageHeight, bufferedImageWidth - slicedImageWidth, bufferedImageHeight);
        slicedImage[8] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, bufferedImageWidth - slicedImageWidth, bufferedImageHeight - slicedImageHeight, bufferedImageWidth, bufferedImageHeight);

        // Create a new BufferedImage with the new image size
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

        // Since we don't want to stretch out the corners of the image, keep them their original size, this prevents weird artifacts
        // The reason for the padding is to prevent the middle parts from showing around the corners, this makes it seamless in theory
        graphics = combinedImage.getGraphics();
        graphics.drawImage(new ImageIcon(slicedImage[4].getScaledInstance(imageWidth - slicedImageHeight, imageHeight - slicedImageHeight, Image.SCALE_SMOOTH)).getImage(), slicedImageHeight / 2, slicedImageHeight / 2, null);
        graphics.drawImage(new ImageIcon(slicedImage[1].getScaledInstance(imageWidth - slicedImageHeight, slicedImage[1].getHeight(), Image.SCALE_SMOOTH)).getImage(), slicedImageHeight / 2, 0, null);
        graphics.drawImage(new ImageIcon(slicedImage[7].getScaledInstance(imageWidth - slicedImageHeight, slicedImage[7].getHeight(), Image.SCALE_SMOOTH)).getImage(), slicedImageHeight / 2, imageHeight - slicedImage[1].getHeight(), null);
        graphics.drawImage(new ImageIcon(slicedImage[3].getScaledInstance(slicedImage[3].getWidth(), imageHeight - slicedImageHeight, Image.SCALE_SMOOTH)).getImage(), 0, slicedImageHeight / 2, null);
        graphics.drawImage(new ImageIcon(slicedImage[5].getScaledInstance(slicedImage[5].getWidth(), imageHeight - slicedImageHeight, Image.SCALE_SMOOTH)).getImage(), imageWidth - slicedImage[5].getWidth(), slicedImageHeight / 2, null);

        // Finally add in the 4 corner pieces on top of the stretched parts
        graphics.drawImage(slicedImage[0], 0, 0, null);
        graphics.drawImage(slicedImage[2], imageWidth - slicedImage[2].getWidth(), 0, null);
        graphics.drawImage(slicedImage[6], 0, imageHeight - slicedImage[6].getHeight(), null);
        graphics.drawImage(slicedImage[8], imageWidth - slicedImage[2].getWidth(), imageHeight - slicedImage[8].getHeight(), null);

        // And dispose of the graphics since we don't need it anymore
        graphics.dispose();

        return combinedImage;
    }

    static BufferedImage sliceImage(BufferedImage bufferedImage, int imageWidth, int imageHeight, int sourceFirstX, int sourceFirstY, int dstCornerX, int dstCornerY) {
        // Create a new BufferedImage of the slice size
        BufferedImage returnImage = new BufferedImage(imageWidth, imageHeight, bufferedImage.getType());
        Graphics2D graphics = returnImage.createGraphics();
        // Get the image part of the original image, then copy that part onto the created image
        graphics.drawImage(bufferedImage, 0, 0, imageWidth, imageHeight, sourceFirstX, sourceFirstY, dstCornerX, dstCornerY, null);

        // And dispose of the graphics since we don't need it anymore
        graphics.dispose();

        return returnImage;
    }

    //endregion

    //region Overlay Image

    public static BufferedImage overlapImage(Image baseImageName, Image overlayImage, int imageWidth, int imageHeight, int padding) {
        BufferedImage overlappedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = overlappedImage.getGraphics();

        graphics.drawImage(sliceImage(baseImageName, imageWidth, imageHeight), 0, 0, null);
        graphics.drawImage(overlayImage.getScaledInstance(imageWidth - (padding * 2), imageHeight - (padding * 2), Image.SCALE_SMOOTH), padding, padding, null);

        // And dispose of the graphics since we don't need it anymore
        graphics.dispose();

        return overlappedImage;
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

    //region Get Files

    public static AudioInputStream getAudioInputStream(String audioClipPath) {
        try {
            return AudioSystem.getAudioInputStream(Objects.requireNonNull(Functions.class.getResource(audioClipPath + ResourcePaths.EXTENSION_AUDIO)));
        } catch (UnsupportedAudioFileException | IOException ex) {
            throw new ExceptionHandler("AudioClip could not be created", ex);
        }
    }

    public static Image loadImage(String imagePath) {
        try {
            return ImageIO.read(Objects.requireNonNull(Functions.class.getClassLoader().getResource(imagePath)));
        } catch (IOException exception) {
            return null;
        }
    }

    public static List<String> getFiles(String filePath, String filePrefix) {
        List<String> fileNames = new ArrayList<>();

        try (
                InputStream in = Functions.class.getResourceAsStream(filePath)) {
            assert in != null;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                String resource;

                while ((resource = br.readLine()) != null) {
                    if (resource.contains(filePrefix)) {
                        fileNames.add(resource);
                    }
                }
            }
        } catch (IOException e) {
            throw new ExceptionHandler("Error reading files with prefix: " + filePrefix, e);
        }

        return fileNames;
    }

    //endregion

    //region Exceptions

    public static class ExceptionHandler extends RuntimeException {

        // Message with cause
        public ExceptionHandler(String message, Throwable cause) {
            System.out.println(capitalize(punctuate(message + ": " + cause.getMessage())));
        }

        // Message only
        public ExceptionHandler(String message) {
            System.out.println(capitalize(punctuate(message)));
        }
    }

    //endregion

    //region String format

    public static String capitalize(String string) {
        // Get the first character of the string, make that uppercase, then add the remaining text after it
        if (string.isEmpty() || string.length() == 1) {
            return string;
        } else {
            return String.format("%s%s", string.substring(0, 1).toUpperCase(), string.substring(1));
        }
    }

    public static String punctuate(String string) {
        // Check if the given string ends with punctuation, if true just return it, otherwise add a dot to the end
        if (string.matches(".*\\p{Punct}")) {
            return string;
        } else {
            return String.format("%s.", string);
        }
    }

    public static String toTitleCase(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String stringPart : string.toLowerCase().split(" ")) {
            stringBuilder.append(String.format("%s ", capitalize(stringPart)));
        }

        return stringBuilder.toString().trim(); // Add the trim to the end to remove leading and trailing spaces
    }

    //endregion
}

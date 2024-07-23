package com.sintraqos.portfolioproject.statics;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Statics {
    public static final List<Dimension> WINDOW_SIZES = new ArrayList<>();

    static {
        // 16:9
        WINDOW_SIZES.add(new Dimension(640, 360));
        WINDOW_SIZES.add(new Dimension(854, 480));
        WINDOW_SIZES.add(new Dimension(960, 540));
        WINDOW_SIZES.add(new Dimension(1024, 576));
        WINDOW_SIZES.add(new Dimension(1280, 720));
        WINDOW_SIZES.add(new Dimension(1366, 768));
        WINDOW_SIZES.add(new Dimension(1600, 900));
        WINDOW_SIZES.add(new Dimension(1920, 1080));
        WINDOW_SIZES.add(new Dimension(2048, 1152));
        WINDOW_SIZES.add(new Dimension(2560, 1440));
        WINDOW_SIZES.add(new Dimension(3200, 1800));
        WINDOW_SIZES.add(new Dimension(3840, 2160));
    }

    // Colors
    public static final Color GUI_BACKGROUND_COLOR = new Color(0, 0, 0);
    public static final Color GUI_FOREGROUND_COLOR = new Color(255, 255, 255);
    public static final Color GUI_DEFAULT_TEXT_COLOR = new Color(32, 131, 109);
    public static final Color GUI_HOVER_TEXT_COLOR = new Color(173, 234, 226);

    // Dialogue
    public static final String DIALOGUE_EXIT_CONDITION = "EXIT";
}


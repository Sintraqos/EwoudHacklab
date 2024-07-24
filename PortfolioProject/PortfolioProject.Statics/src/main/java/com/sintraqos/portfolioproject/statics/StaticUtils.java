package com.sintraqos.portfolioproject.statics;

import java.awt.*;
import java.util.List;

public class StaticUtils {

    private StaticUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static final List<Dimension> WINDOW_SIZES = List.of(
            new Dimension(640, 360),
            new Dimension(854, 480),
            new Dimension(960, 540),
            new Dimension(1024, 576),
            new Dimension(1280, 720),
            new Dimension(1366, 768),
            new Dimension(1600, 900),
            new Dimension(1920, 1080),
            new Dimension(2048, 1152),
            new Dimension(2560, 1440),
            new Dimension(3200, 1800),
            new Dimension(3840, 2160));

    // Colors
    public static final Color GUI_BACKGROUND_COLOR = new Color(0, 0, 0);
    public static final Color GUI_FOREGROUND_COLOR = new Color(255, 255, 255);
    public static final Color GUI_DEFAULT_TEXT_COLOR = new Color(32, 131, 109);
    public static final Color GUI_HOVER_TEXT_COLOR = new Color(173, 234, 226);

    // Dialogue
    public static final String DIALOGUE_EXIT_CONDITION = "EXIT";

    // Names
    public static final String NAME_PLAYER = "%PLAYERNAME%";
    public static final String NAME_KREIA = "Kreia";
    public static final String NAME_ATTON = "Atton";
}


package CLI;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.gui2.EmptyWindowDecorationRenderer;

/**
 * Theme is a utility class for providing a custom theme for the TUI components.
 */
public class Theme {

    /**
     * Returns a SimpleTheme instance with custom colors and window decoration.
     *
     * @return the custom SimpleTheme instance
     */
    public static SimpleTheme getTheme() {
        return SimpleTheme
                .makeTheme(
                        false,
                        TextColor.ANSI.WHITE,
                        TextColor.ANSI.BLACK,
                        TextColor.ANSI.WHITE,
                        TextColor.ANSI.BLACK,
                        TextColor.ANSI.BLACK,
                        TextColor.ANSI.WHITE,
                        TextColor.ANSI.BLACK
                )
                .setWindowDecorationRenderer(
                        new EmptyWindowDecorationRenderer()
                );
    }
}

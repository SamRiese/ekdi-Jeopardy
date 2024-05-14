package CLI;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.gui2.EmptyWindowDecorationRenderer;

public class Theme {
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

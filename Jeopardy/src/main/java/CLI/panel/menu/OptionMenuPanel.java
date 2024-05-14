package CLI.panel.menu;

import CLI.Window;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.Label;

public class OptionMenuPanel extends MainMenuPanel{
    public OptionMenuPanel(Window window) {
        super(window);
    }

    @Override
    protected Label getTitleAsLabel() {
        return new Label("Option Menu").setLayoutData(layoutData);
    }

    @Override
    protected ActionListBox getActionListBox() {
        ActionListBox actionListBox = new ActionListBox().setLayoutData(layoutData);

        actionListBox.addItem("Exit", () -> window.setComponent(new MainMenuPanel(window)));

        return actionListBox;
    }
}

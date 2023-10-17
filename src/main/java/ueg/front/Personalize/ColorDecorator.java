package ueg.front.Personalize;

import javax.swing.*;
import java.awt.*;

public class ColorDecorator implements MessageDecorator {
    private Color color;

    public ColorDecorator(Color color) {
        this.color = color;
    }

    @Override
    public void decorate(JLabel label) {
        label.setForeground(color);
    }
}


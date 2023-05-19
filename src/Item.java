import java.awt.*;

import javax.swing.*;

public class Item {
    private ImageIcon image;
    private String name;

    public Item(String name, String filename) {
        this.name = name;
        image = new ImageIcon(filename);
    }

    public ImageIcon getIcon() {
        return image;
    }

    public String getName() {
        return name;
    }
}

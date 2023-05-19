import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuPanel extends JPanel{
    List<Item> items = new ArrayList<>();

    public MenuPanel(ItemSelectListener itemSelectListener) {
        super();

        setLayout(new FlowLayout());

        loadItems();
        for (Item item : items) {
            JButton button = new JButton(item.getName());
            button.addActionListener(event -> itemSelectListener.onItemSelect(item));
            add(button);
        }
    }

    private void loadItems() {
        String[] itemNames = {"Paella", "Salmon"};
        String[] itemFilenames = {"paella.jpg", "salmon.jpeg"};

        for (int i = 0; i < itemNames.length; i++) {
            items.add(new Item(itemNames[i], "images/" + itemFilenames[i]));
        }
    }
}

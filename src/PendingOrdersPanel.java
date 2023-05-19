import javax.swing.*;

public class PendingOrdersPanel extends JPanel {
    JLabel nextOrderLabel;
    JLabel remainingLabel;

    public PendingOrdersPanel() {
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        remainingLabel = new JLabel();
        add(remainingLabel);

        nextOrderLabel = new JLabel();
        nextOrderLabel.setVerticalTextPosition(JLabel.TOP);
        nextOrderLabel.setHorizontalTextPosition(JLabel.CENTER);
        add(nextOrderLabel);
    }

    public void setNextOrder(Item item, int remaining) {
        if (item == null) {
            nextOrderLabel.setText("");
            nextOrderLabel.setIcon(null);
            remainingLabel.setText("");
            return;
        }

        nextOrderLabel.setText("Next order:");
        var icon = new ImageIcon(item.getIcon().getImage().getScaledInstance(-1, 40, 0));
        nextOrderLabel.setIcon(icon);
        setRemaining(remaining);
    }

    public void setRemaining(int count) {
        remainingLabel.setText(count +  " orders pending");
    }
}

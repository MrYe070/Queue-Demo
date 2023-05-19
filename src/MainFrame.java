import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MainFrame extends JFrame {
    private Queue<Item> pendingOrders = new LinkedList<>();
    private Item currentOrder;

    private ProcessingPanel processingPanel;
    private PendingOrdersPanel pendingOrdersPanel;


    public MainFrame(int width, int height) {
        super();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLayout(new FlowLayout());

        add(new MenuPanel(item -> onMenuItemClick(item)));

        processingPanel = new ProcessingPanel(width/2, height, event -> startNextItem());
        add(processingPanel);

        pendingOrdersPanel = new PendingOrdersPanel();
        add(pendingOrdersPanel);

        pack();
    }

    private void onMenuItemClick(Item menuItem) {
        pendingOrders.add(menuItem);
        startNextItem();
    }

    private void startNextItem() {
        if (!pendingOrders.isEmpty() && !processingPanel.isProcessing()) {
            currentOrder = pendingOrders.poll();
            processingPanel.startItem(currentOrder);
        }

        previewNextOrder();

        pack();
    }

    private void previewNextOrder() {
        pendingOrdersPanel.setNextOrder(pendingOrders.peek(), pendingOrders.size());
    }
}
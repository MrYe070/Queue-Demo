import java.awt.event.ActionListener;
import javax.swing.*;

public class ProcessingPanel extends JPanel {
    private boolean isProcessing = false;
    private long processStartMillis;
    private static final long processTotalMillis = 5_000;

    private JLabel currentItemLabel;
    private JProgressBar progressBar;
    private Timer timer;

    ActionListener onFinishHandler;


    public ProcessingPanel(int width, int height, ActionListener onFinishHandler) {
        super();

        this.onFinishHandler = onFinishHandler;

        setSize(width, height);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        currentItemLabel = new JLabel();
        add(currentItemLabel);

        progressBar = new JProgressBar(0, 10);
        progressBar.setStringPainted(true);
        progressBar.setString("Waiting for next order...");

        add(progressBar);

        timer = new Timer(1, event -> checkTime());
    }

    private void checkTime() {
        long timeElapsedMillis = System.currentTimeMillis() - processStartMillis;

        progressBar.setValue((int) (timeElapsedMillis * progressBar.getMaximum() / processTotalMillis));
        
        if (timeElapsedMillis >= processTotalMillis) {
            timer.stop();
            isProcessing = false;
            progressBar.setString("Done. Waiting for next order...");
            currentItemLabel.setIcon(null);
            onFinishHandler.actionPerformed(null);
        }
    }

    public void startItem(Item item) {
        isProcessing = true;

        processStartMillis = System.currentTimeMillis();
        ImageIcon icon = new ImageIcon(item.getIcon().getImage().getScaledInstance(-1, 400, 0));
        currentItemLabel.setIcon(icon);

        progressBar.setString("Preparing order...");
        progressBar.setValue(0);

        timer.start();
    }

    public boolean isProcessing() {
        return isProcessing;
    }
}

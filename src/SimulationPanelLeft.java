import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class SimulationPanelLeft extends JPanel {
    private JLabel foodLabel;
    private JLabel waterLabel;
    private JLabel electricityLabel;
    private JLabel oxygenLabel;
    private JTextArea eventMessagesTextArea;

    public SimulationPanelLeft(ColonyResources colonyResources) {
        this.setBackground(new Color(0x678f9c));
        this.setPreferredSize(new Dimension(300, 400)); // Adjusted height to accommodate the JTextArea and other components
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Set BoxLayout for vertical arrangement

        Font labelFont = new Font("Lato", Font.BOLD, 20);
        Color valueColor = new Color(0xffffff); // Slightly different color for values

        foodLabel = createLabel("Food: " + colonyResources.getFood(), labelFont, valueColor);
        waterLabel = createLabel("Water: " + colonyResources.getWater(), labelFont, valueColor);
        oxygenLabel = createLabel("Oxygen: " + colonyResources.getOxygen(), labelFont, valueColor);
        electricityLabel = createLabel("Electricity: " + colonyResources.getElectricity(), labelFont, valueColor);

        this.add(foodLabel);
        this.add(waterLabel);
        this.add(oxygenLabel);
        this.add(electricityLabel);

        // Create and add JTextArea for event messages
        eventMessagesTextArea = new JTextArea();
        eventMessagesTextArea.setFont(new Font("Lato", Font.PLAIN, 14));
        eventMessagesTextArea.setLineWrap(true);
        eventMessagesTextArea.setWrapStyleWord(true);
        eventMessagesTextArea.setEditable(false);
        eventMessagesTextArea.setBackground(new Color(0xCCCCCC)); // Background color for visibility

        JScrollPane scrollPane = new JScrollPane(eventMessagesTextArea);
        scrollPane.setPreferredSize(new Dimension(300, 100));
        this.add(scrollPane);
    }

    private JLabel createLabel(String text, Font font, Color valueColor) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(valueColor);
        label.setBorder(new EmptyBorder(10, 10, 0, 0));
        return label;
    }

    public void updateResources(ColonyResources colonyResources) {
        foodLabel.setText("Food: " + colonyResources.getFood());
        waterLabel.setText("Water: " + colonyResources.getWater());
        oxygenLabel.setText("Oxygen: " + colonyResources.getOxygen());
        electricityLabel.setText("Electricity: " + colonyResources.getElectricity());
    }

    public void appendEventMessage(String message) {
        eventMessagesTextArea.append(message + "\n");
        eventMessagesTextArea.setCaretPosition(eventMessagesTextArea.getDocument().getLength()); // Scroll to the bottom
    }

    public void clearEventMessages() {
        eventMessagesTextArea.setText("");
    }
}

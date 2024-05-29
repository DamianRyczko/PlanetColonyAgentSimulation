import javax.swing.*;
import java.awt.*;

public class ResourceGraphPanel extends JPanel {
    private int food;
    private int water;
    private int oxygen;
    private int electricity;

    public ResourceGraphPanel(ColonyResources colonyResources) {
        this.food = colonyResources.getFood();
        this.water = colonyResources.getWater();
        this.oxygen = colonyResources.getOxygen();
        this.electricity = colonyResources.getElectricity();
        this.setPreferredSize(new Dimension(300, 100)); // Set a preferred size for the graph
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        int barWidth = width / 4;

        int maxValue = Math.max(Math.max(food, water), Math.max(oxygen, electricity));

        int foodBarHeight = (int) ((double) food / maxValue * height);
        int waterBarHeight = (int) ((double) water / maxValue * height);
        int oxygenBarHeight = (int) ((double) oxygen / maxValue * height);
        int electricityBarHeight = (int) ((double) electricity / maxValue * height);

        g2d.setColor(Color.RED);
        g2d.fillRect(0, height - foodBarHeight, barWidth, foodBarHeight);

        g2d.setColor(Color.BLUE);
        g2d.fillRect(barWidth, height - waterBarHeight, barWidth, waterBarHeight);

        g2d.setColor(Color.GREEN);
        g2d.fillRect(barWidth * 2, height - oxygenBarHeight, barWidth, oxygenBarHeight);

        g2d.setColor(Color.YELLOW);
        g2d.fillRect(barWidth * 3, height - electricityBarHeight, barWidth, electricityBarHeight);
    }

    public void updateValues(ColonyResources colonyResources) {
        this.food = colonyResources.getFood();
        this.water = colonyResources.getWater();
        this.oxygen = colonyResources.getOxygen();
        this.electricity = colonyResources.getElectricity();
        repaint();
    }
}

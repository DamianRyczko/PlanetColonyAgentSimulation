import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimulationPanelDisplay extends SimulationFrame {
    private final AMap map;
    private final Map<Integer, ImageIcon> typeToIcon;
    private final Map<Integer, Color> astronautTypeToColor;

    ArrayList<Building> buildings;
    ArrayList<Astronaut> astronauts;

    public SimulationPanelDisplay(AMap map, ArrayList<Building> buildings, ArrayList<Astronaut> astronauts) {
        this.map = map;
        this.setBackground(new Color(0x262a2b));
        this.typeToIcon = new HashMap<>();
        this.buildings = buildings;
        this.astronauts = astronauts;

        typeToIcon.put(0, new ImageIcon("resource_0.png"));
        typeToIcon.put(1, new ImageIcon("resource_1.png"));
        typeToIcon.put(2, new ImageIcon("resource_2.png"));
        typeToIcon.put(3, new ImageIcon("building_1.png"));
        typeToIcon.put(4, new ImageIcon("building_4.png"));
        typeToIcon.put(5, new ImageIcon("building_5.png"));
        typeToIcon.put(6, new ImageIcon("building_3.png"));
        typeToIcon.put(7, new ImageIcon("building_2.png"));

        typeToIcon.put(8, new ImageIcon("building_1_damaged.png"));
        typeToIcon.put(9, new ImageIcon("building_4_damaged.png"));
        typeToIcon.put(10, new ImageIcon("building_5_damaged.png"));
        typeToIcon.put(11, new ImageIcon("building_3_damaged.png"));
        typeToIcon.put(12, new ImageIcon("building_2_damaged.png"));

        typeToIcon.put(13, new ImageIcon("building_4_dirty.png"));


        astronautTypeToColor = new HashMap<>();
        astronautTypeToColor.put(1, Color.GREEN);
        astronautTypeToColor.put(2, Color.RED);
        astronautTypeToColor.put(3, Color.BLUE);
        astronautTypeToColor.put(4, Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();
        int gridSizeX = map.getGridSizeX();
        int gridSizeY = map.getGridSizeY();
        int cellWidth = panelWidth / gridSizeX;
        int cellHeight = panelHeight / gridSizeY;

        // Calculate offsets to center the grid
        int totalGridWidth = cellWidth * gridSizeX;
        int totalGridHeight = cellHeight * gridSizeY;
        int offsetX = (panelWidth - totalGridWidth) / 2;
        int offsetY = (panelHeight - totalGridHeight) / 2;

        for (int x = 0; x < gridSizeX; x++) {
            for (int y = 0; y < gridSizeY; y++) {
                ImageIcon icon = null;
                int fieldType = 0;
                if (map.getFieldEmpty(x, y)) {
                    fieldType = map.getFieldType(x, y);
                } else {
                    for (Object building : buildings) {
                        if (building instanceof Farm farm) {
                            if (farm.getPostion().getX() == x && farm.getPostion().getY() == y) {
                                fieldType = 3;
                                if (farm.getIsDamaged()){
                                    fieldType = 8;
                                }
                                break;
                            }
                        } else if (building instanceof SolarPanel solarPanel) {
                            if (solarPanel.getPostion().getX() == x && solarPanel.getPostion().getY() == y) {
                                fieldType = 4;
                                if (solarPanel.getDirty()){
                                    fieldType = 13;
                                }
                                if (solarPanel.getIsDamaged()){
                                    fieldType = 9;
                                }
                                break;
                            }
                        } else if (building instanceof FusionReactor fusionReactor) {
                            if (fusionReactor.getPostion().getX() == x && fusionReactor.getPostion().getY() == y) {
                                fieldType = 5;
                                if (fusionReactor.getIsDamaged()){
                                    fieldType = 10;
                                }
                                break;
                            }
                        } else if (building instanceof OxygenGenerator oxygenGenerator) {
                            if (oxygenGenerator.getPostion().getX() == x && oxygenGenerator.getPostion().getY() == y) {
                                fieldType = 6;
                                if (oxygenGenerator.getIsDamaged()){
                                    fieldType = 11;
                                }
                                break;
                            }
                        } else if (building instanceof WaterPurifier waterPurifier) {
                            if (waterPurifier.getPostion().getX() == x && waterPurifier.getPostion().getY() == y) {
                                fieldType = 7;
                                if (waterPurifier.getIsDamaged()){
                                    fieldType = 12;
                                }
                                break;
                            }
                        }
                    }
                }
                icon = typeToIcon.get(fieldType);
                if (icon != null) {
                    Image img = icon.getImage();
                    g.drawImage(img, offsetX + x * cellWidth, offsetY + y * cellHeight, cellWidth, cellHeight, this);
                }
            }
        }

        // Draw astronauts
        for (Astronaut astronaut : astronauts) {
            int x = astronaut.getPosition().getX();
            int y = astronaut.getPosition().getY();
            int type= 0;
            if (astronaut instanceof Medic medic) {
                type = 2;
                if (!medic.isAlive()){
                    type = 4;
                }

            } else if (astronaut instanceof Engineer engineer) {
                type = 3;
                if (!engineer.isAlive()){
                    type = 4;
                }
            }

            int health = astronaut.getHealth();

            // Get the color for the astronaut type
            Color color = astronautTypeToColor.getOrDefault(type, Color.WHITE);
            g.setColor(color);

            // Draw the astronaut as a small dot
            int dotSize = Math.min(cellWidth, cellHeight) / 2;
            int dotX = offsetX + x * cellWidth + (cellWidth - dotSize) / 2;
            int dotY = offsetY + y * cellHeight + (cellHeight - dotSize) / 2;
            g.fillOval(dotX, dotY, dotSize, dotSize);

            // Draw the health label above the dot
            String healthStr = String.valueOf(health);
            FontMetrics fm = g.getFontMetrics();
            int labelWidth = fm.stringWidth(healthStr);
            int labelHeight = fm.getHeight();
            Font labelFont = new Font("Lato", Font.BOLD, 10);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // Set semi-transparent background
            g2d.setColor(Color.BLACK);
            g2d.fillRect(dotX, dotY - labelHeight - 3, labelWidth, labelHeight);

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // Set back to opaque for text
            g2d.setColor(Color.WHITE);
            g2d.setFont(labelFont);
            g2d.drawString(healthStr, dotX, dotY - 5);
        }
    }

    public void repaintMap() {
        this.repaint();
    }
}

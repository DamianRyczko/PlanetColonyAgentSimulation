import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimulationPanelDisplay extends JPanel {
    private final AMap map;
    private final Map<Integer, ImageIcon> typeToIcon;

    ArrayList<Object> buildings;

    public SimulationPanelDisplay(AMap map,  ArrayList<Object> buildings) {
        this.map = map;
        this.setBackground(new Color(0x262a2b));
        this.typeToIcon = new HashMap<>();
        this.buildings = buildings;


        typeToIcon.put(0, new ImageIcon("resource_0.png"));
        typeToIcon.put(1, new ImageIcon("resource_1.png"));
        typeToIcon.put(2, new ImageIcon("resource_2.png"));
        typeToIcon.put(3, new ImageIcon("building_1.png"));
        typeToIcon.put(4, new ImageIcon("building_4.png"));
        typeToIcon.put(5, new ImageIcon("building_5.png"));
        typeToIcon.put(6, new ImageIcon("building_3.png"));
        typeToIcon.put(7, new ImageIcon("building_2.png"));


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
                if(map.getFieldEmpty(x,y)){
                    fieldType = map.getFieldType(x, y);
                }
                else{
                    for (Object building : buildings) {
                        if (building instanceof Farm farm) {
                            if (farm.getPostion().getX() == x && farm.getPostion().getY() == y){
                                fieldType = 3;
                                break;
                            }
                        } else if (building instanceof SolarPanel solarPanel) {
                            if (solarPanel.getPostion().getX() == x && solarPanel.getPostion().getY() == y){
                                fieldType = 4;
                                break;
                            }
                        } else if (building instanceof FusionReactor fusionReactor) {;
                            if (fusionReactor.getPostion().getX() == x && fusionReactor.getPostion().getY() == y){
                                fieldType = 5;
                                break;
                            }
                        } else if (building instanceof OxygenGenerator oxygenGenerator) {
                            if (oxygenGenerator.getPostion().getX() == x && oxygenGenerator.getPostion().getY() == y){
                                fieldType = 6;
                                break;
                            }
                        } else if (building instanceof WaterPurifier waterPurifier) {
                            if (waterPurifier.getPostion().getX() == x && waterPurifier.getPostion().getY() == y){
                                fieldType = 7;
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
    }

    public void repaintMap() {
        this.repaint();
    }

}

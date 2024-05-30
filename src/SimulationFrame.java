import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SimulationFrame extends JFrame {



    SimulationPanelLeft simulationPanelLeft;
    SimulationPanelDisplay simulationPanelDisplay;
    public SimulationFrame(ColonyResources colonyResources, AMap map, ArrayList<Object> buildings,  ArrayList<Astronaut> astronauts){;
        this.setTitle("Colony Simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application == stop simulation
        this.setResizable(true);
        this.setSize(1000,700);

        ImageIcon titleIcon = new ImageIcon("TitleIcon.png");
        this.setIconImage(titleIcon.getImage());// set frame image

        this.simulationPanelLeft = new SimulationPanelLeft(colonyResources);
        this.add(simulationPanelLeft, BorderLayout.WEST);
        this.simulationPanelDisplay = new SimulationPanelDisplay(map, buildings, astronauts);
        this.add(simulationPanelDisplay, BorderLayout.CENTER);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(0x575b61));
    }

    public void repaintMap() {
        simulationPanelDisplay.repaintMap();
    }
    public SimulationPanelLeft getSimulationPanelLeft() {
        return simulationPanelLeft;
    }

    public void setSimulationPanelLeft(SimulationPanelLeft simulationPanelLeft) {
        this.simulationPanelLeft = simulationPanelLeft;
    }


}

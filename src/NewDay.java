import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.random;

public class NewDay {


    static public ArrayList<Engineer> nextDay(ArrayList<Building> buildings, ArrayList<Astronaut> astronauts, ColonyResources colonyResources, AMap map){
        resetAll(astronauts);
        assignMadic(astronauts,map);
        assignCollectors(buildings, astronauts, colonyResources);
        ArrayList<Engineer> freeEngineers = assingEngineers(buildings, astronauts);



        randomMovs(astronauts, map);

        return freeEngineers;
    }

    static void randomMovs(ArrayList<Astronaut> astronauts, AMap map){
        for(Astronaut astronaut : astronauts){
            if (astronaut.isMoveMade()){
                break;
            }
            if (!astronaut.isOccupied()){
                doRandomMove(astronaut, map);
            }
        }
    }

    static void doRandomMove(Astronaut astronaut, AMap map){
        int distans = astronaut.getDailyDistance();
        Position position = astronaut.getPosition();
        int xMov = (int)(random()*distans);
        int yMov = (int)(random()*(distans-xMov));
        if (random() < 0.5){
            xMov *= -1;
        }
        if (random() < 0.5){
            yMov *= -1;
        }
        position.setX(position.getX()+xMov);
        position.setY(position.getY()+yMov);
        position = correctPosition(position, map);
        astronaut.moveTo(position);
    }

    static Position correctPosition(Position position, AMap map){
        int X = position.getX();
        int Y = position.getY();
        if (X < 0){
            X = 0;
        }
        if (X >= map.getGridSizeX()){
            X = map.getGridSizeX()-1;
        }
        if (Y < 0){
            Y = 0;
        }
        if (Y >= map.getGridSizeY()){
            Y = map.getGridSizeY()-1;
        }
        position.setX(X);
        position.setY(Y);
        return position;
    }

    static void resetAll(ArrayList<Astronaut> astronauts){
        ArrayList<Astronaut> astronautsToRemove = new ArrayList<>();
        for (Astronaut astronaut : astronauts) {
            astronaut.reSetMoveDone();
            astronaut.setMoveMade(false);
            astronaut.setOccupied(false);
            if (astronaut.getHealth() < 100){
                astronaut.kill();
            }
            if (!astronaut.isAlive()){
                astronautsToRemove.add(astronaut);
                continue;
            }

        }
        for (Astronaut astronaut : astronautsToRemove) {
            astronauts.remove(astronaut);
        }

    }

    static void assignMadic(ArrayList<Astronaut> astronauts, AMap map){
        for(Astronaut astronaut : astronauts){
            if (astronaut.getHealth() < 100){
                findAndAssignMadic(astronaut, astronauts, map);
            }
        }
    }

    static public boolean checkIfAvailable(Astronaut astronaut){
        if (astronaut.isOccupied()){return false;}
        if (astronaut.isMoveMade()){return false;}
        return true;
    }

    static void findAndAssignMadic (Astronaut patient, ArrayList<Astronaut> astronauts, AMap map){
        Position position = patient.getPosition();
        int minimumDistance = Integer.MAX_VALUE;
        Medic theMedic = null;
        for(Astronaut astronaut : astronauts) {
            if (astronaut instanceof Medic) {
                if (!checkIfAvailable(astronaut)){break;}
                if (minimumDistance > abs(astronaut.getPosition().getX()-position.getX()) + abs(astronaut.getPosition().getY()-position.getY())) {
                    minimumDistance = abs(astronaut.getPosition().getX()-position.getX()) + abs(astronaut.getPosition().getY()-position.getY());
                    theMedic = (Medic) astronaut;
                }
            }
        }
        if (minimumDistance == 0){
            theMedic.heal(patient);
            theMedic.setOccupied(false);
        }
        else if (theMedic == null){return;}
        else{
            theMedic.moveTo(FindPath.BFS(theMedic.getPosition(),position,map.getGridSize(),theMedic.getDailyDistance()));
        }
    }

    static void assignCollectors(ArrayList<Building> buildings, ArrayList<Astronaut> astronauts, ColonyResources colonyResources){
        for(Astronaut astronaut : astronauts){
            if (astronaut.getHealth() < 100){
                break;
            }
            if (astronaut instanceof Collector){
                ((Collector) astronaut).dailyTask(buildings, astronauts, colonyResources);
            }
        }
    }

    static ArrayList<Engineer> assingEngineers(ArrayList<Building> buildings, ArrayList<Astronaut> astronauts){

        for (Building building : buildings) {
            if (isBuildingIsDamaged(building)){
                findEngineerToRepair(building, astronauts);
                //System.out.println("okok");
            }
        }

        ArrayList<Engineer> freeEngineers = new ArrayList<>();
        for(Astronaut astronaut : astronauts){
            if (astronaut instanceof Engineer){
                if (astronaut.isfree()){
                    freeEngineers.add((Engineer) astronaut);
                }
            }
        }
        return freeEngineers;
    }

    static boolean isBuildingIsDamaged(Building building){
        if (building.getIsDamaged()){
            return true;
        }
        if (building instanceof SolarPanel){
            if (((SolarPanel) building).getDirty()){
                return true;
            }
        }
        return false;
    }

    static void findEngineerToRepair(Building building, ArrayList<Astronaut> astronauts){
        Engineer theEngineer = null;
        int minimumDistance = Integer.MAX_VALUE;
        for (Astronaut astronaut : astronauts) {
            if (astronaut instanceof Engineer){
                if (!checkIfAvailable(astronaut)){break;}
                if (minimumDistance > Position.manhattanDistance(astronaut.getPosition(),building.getPostion())){
                    minimumDistance = Position.manhattanDistance(astronaut.getPosition(),building.getPostion());
                    theEngineer = (Engineer) astronaut;
                }
            }
        }
        if (theEngineer != null) {
            theEngineer.goToRepair(building);
            //System.out.println("super");
        }
    }
}

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.random;

public class NewDay {


    static public void nextDay(ArrayList<Building> buildings, ArrayList<Astronaut> astronauts, AMap map){
        resetAll(astronauts);
        assignMadic(astronauts,map);

        randomMovs(astronauts, map);

    }

    static void randomMovs(ArrayList<Astronaut> astronauts, AMap map){
        for(Astronaut astronaut : astronauts){
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
        System.out.println("okokokok");
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
        for (Astronaut astronaut : astronauts) {
            astronaut.reSetMoveDone();
            if (!astronaut.isAlive()){
                astronauts.remove(astronaut);
                break;
            }
            if (astronaut.getHealth() < 100){
                astronaut.setOccupied(true);
                break;
            }
            astronaut.setMoveMade(false);
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
        if (astronaut.isAlive()){return true;}
        return false;
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
            theMedic.setOccupied(true);
        }
        if (theMedic == null){}
        else{
            theMedic.moveTo(FindPath.BFS(theMedic.getPosition(),position,map.getGridSize(),theMedic.getDailyDistance()));

        }
    }

}

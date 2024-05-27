import java.util.*;

class Node{
    Position position;
    int steps;

    public Node(Position position, int steps){
        this.position = position;
        this.steps = steps;
    }
}


public class FindPath {

    private static final int[][] DIRECTIONS = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };


    public static Position BFS(Position start, Position end, Position gridSize, int numberOfSteps) {
        Queue<Node> queue = new LinkedList<>();
        Set<Position> visited = new HashSet<>();
        Map<Position,Position> previous = new HashMap<>();
        queue.add(new Node(start,0));
        visited.add(start);
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            if (current.position.equals(end)) {
                int steps = current.steps;
                Position newPosition = current.position;
                while (steps > numberOfSteps) {
                    steps --;
                    newPosition = previous.get(current.position);
                }
                return newPosition;
            }
            for (int[] direction : DIRECTIONS) {
                int newStepsX = current.position.getX() + direction[0];
                int newStepsY = current.position.getY() + direction[1];
                if (newStepsX < 0 || newStepsX > gridSize.getX() || newStepsY < 0 || newStepsY > gridSize.getY()) {
                    continue;
                }
                Position newposition = new Position(newStepsX, newStepsY);
                if (!visited.contains(newposition)) {
                    queue.add(new Node(newposition,current.steps + 1));
                    visited.add(newposition);
                    previous.put(newposition, current.position);
                }
            }
        }
        return new Position(-1,-1);
    }
}


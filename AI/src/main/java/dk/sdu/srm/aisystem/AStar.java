package dk.sdu.srm.aisystem;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import dk.sdu.srm.common.ai.AISPI;

import java.util.*;

public class AStar implements AISPI {

    private static final int DIAGONAL_COST = 14;
    private static final int STRAIGHT_COST = 10;

    public ArrayList<Vector2> findPath(ArrayList<ArrayList<TiledMapTileLayer.Cell>> cells, int startX, int startY, int targetX, int targetY) {
        int width = cells.get(0).size();
        int height = cells.size();

        boolean[][] closedSet = new boolean[height][width];
        Node[][] nodes = new Node[height][width];

        PriorityQueue<Node> openSet = new PriorityQueue<>((n1, n2) -> n1.fCost - n2.fCost);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                nodes[y][x] = new Node(x, y);
            }
        }

        Node startNode = nodes[startY][startX];
        Node targetNode = nodes[targetY][targetX];
        startNode.gCost = 0;
        startNode.hCost = calculateHeuristic(startX, startY, targetX, targetY);
        startNode.calculateFCost();

        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll();
            if (currentNode == targetNode) {
                return reconstructPath(currentNode);
            }

            closedSet[currentNode.y][currentNode.x] = true;

            for (int i = 0; i < 9; i++) {
                if (i == 4) continue; // Skip the current node

                int offsetX = (i % 3) - 1;
                int offsetY = (i / 3) - 1;
                int neighborX = currentNode.x + offsetX;
                int neighborY = currentNode.y + offsetY;

                if (neighborX >= 0 && neighborX < width && neighborY >= 0 && neighborY < height) {
                    Node neighborNode = nodes[neighborY][neighborX];

                    if (!closedSet[neighborY][neighborX] && isValidCell(cells, neighborX, neighborY)) {
                        int cost = (offsetX == 0 || offsetY == 0) ? STRAIGHT_COST : DIAGONAL_COST;
                        int gCost = currentNode.gCost + cost;

                        if (gCost < neighborNode.gCost) {
                            neighborNode.gCost = gCost;
                            neighborNode.hCost = calculateHeuristic(neighborX, neighborY, targetX, targetY);
                            neighborNode.calculateFCost();
                            neighborNode.parent = currentNode;

                            if (!openSet.contains(neighborNode)) {
                                openSet.add(neighborNode);
                            }
                        }
                    }
                }
            }
        }

        return null; // No path found
    }

    private static boolean isValidCell(ArrayList<ArrayList<TiledMapTileLayer.Cell>> cells, int x, int y) {
        if (y >= 0 && y < cells.size() && x >= 0 && x < cells.get(y).size()) {
            TiledMapTileLayer.Cell cell = cells.get(y).get(x);
            return cell != null && !cell.getTile().getProperties().containsKey("wall");
        }
        return false;
    }

    private static int calculateHeuristic(int startX, int startY, int targetX, int targetY) {
        return Math.abs(targetX - startX) + Math.abs(targetY - startY);
    }

    private static ArrayList<Vector2> reconstructPath(Node currentNode) {
        ArrayList<Vector2> path = new ArrayList<>();
        while (currentNode.parent != null) {
            path.add(0, new Vector2(currentNode.x, currentNode.y));
            currentNode = currentNode.parent;
        }
        return path;
    }

    private static class Node {
        private int x;
        private int y;
        private int gCost;
        private int hCost;
        private int fCost;
        private Node parent;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            gCost = Integer.MAX_VALUE;
            hCost = Integer.MAX_VALUE;
            fCost = Integer.MAX_VALUE;
            parent = null;
        }

        public void calculateFCost() {
            fCost = gCost + hCost;
        }
    }

}
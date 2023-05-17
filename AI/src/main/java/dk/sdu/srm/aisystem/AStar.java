package dk.sdu.srm.aisystem;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import dk.sdu.srm.common.ai.AISPI;
import dk.sdu.srm.common.data.Coords;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.data.entityparts.PositionPart;

import java.util.*;

public class AStar implements AISPI {

    private static class Node {
        private int x;
        private int y;
        private int gCost;
        private int hCost;
        private Node parent;

        private Node(int x, int y, int gCost, int hCost, Node parent) {
            this.x = x;
            this.y = y;
            this.gCost = gCost;
            this.hCost = hCost;
            this.parent = parent;
        }

        private int getFCost() {
            return gCost + hCost;
        }
    }

    @Override
    public ArrayList<Vector2> findPath(ArrayList<ArrayList<TiledMapTileLayer.Cell>> cells, int startX, int startY) {

        int targetX = 0;
        int targetY = 5;

        // Check if the target is valid

        if (!isValidCell(cells, targetX, targetY)) {
            System.out.println("Target cell is invalid, cannot find a path");
            return null; // Target cell is invalid, cannot find a path
        }

        System.out.println("Start: " + startX + ", " + startY);
        System.out.println("Target: " + targetX + ", " + targetY);
        TiledMapTileLayer.Cell startCell = cells.get(startY).get(startX);
        TiledMapTileLayer.Cell targetCell = cells.get(targetY).get(targetX);
        if (startCell == null || targetCell == null) {
            System.out.println("Start or target cell is invalid, cannot find a path");
            return null; // Start or target cell is invalid, cannot find a path
        }

        int mapWidth = cells.get(0).size();
        int mapHeight = cells.size();

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(Node::getFCost));
        Set<Node> closedSet = new HashSet<>();

        Node startNode = new Node(startX, startY, 0, calculateHeuristic(startX, startY, targetX, targetY), null);
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll();

            if (currentNode.x == targetX && currentNode.y == targetY) {
                // Found the target, reconstruct the path
                return reconstructPath(currentNode);
            }

            closedSet.add(currentNode);

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) {
                        continue; // Skip the current node
                    }

                    int neighborX = currentNode.x + dx;
                    int neighborY = currentNode.y + dy;

                    if (neighborX < 0 || neighborX >= mapWidth || neighborY < 0 || neighborY >= mapHeight) {
                        continue; // Skip if the neighbor is outside the map boundaries
                    }

                    if (!isValidCell(cells, neighborX, neighborY)) {
                        continue; // Skip if the neighbor cell is invalid
                    }

                    if (closedSet.contains(new Node(neighborX, neighborY, 0, 0, null))) {
                        continue; // Skip if the neighbor is already evaluated
                    }

                    int gCost = currentNode.gCost + 1; // Assuming the cost to move to a neighboring cell is 1
                    int hCost = calculateHeuristic(neighborX, neighborY, targetX, targetY);
                    Node neighborNode = new Node(neighborX, neighborY, gCost, hCost, currentNode);

                    if (openSet.contains(neighborNode)) {
                        Node existingNode = getNodeFromSet(openSet, neighborNode);
                        if (gCost < existingNode.gCost) {
                            existingNode.gCost = gCost;
                            existingNode.parent = currentNode;
                        }
                    } else {
                        openSet.add(neighborNode);
                    }
                }
            }
        }

        System.out.println("Could not find a path");
        return null; // No path found
    }

    private static boolean isValidCell(ArrayList<ArrayList<TiledMapTileLayer.Cell>> cells, int x, int y) {
        if (y >= 0 && y < cells.size() && x >= 0 && x < cells.get(y).size()) {
            TiledMapTileLayer.Cell cell = cells.get(y).get(x);
            return cell != null; // Return true if the cell is not null (i.e., it is a valid tile)
        }
        return false;
    }

    private static int calculateHeuristic(int startX, int startY, int targetX, int targetY) {
        return Math.abs(targetX - startX) + Math.abs(targetY - startY);
    }

    private static ArrayList<Vector2> reconstructPath(Node currentNode) {
        ArrayList<Vector2> path = new ArrayList<>();
        while (currentNode != null) {
            path.add(0, new Vector2(currentNode.x, currentNode.y));
            currentNode = currentNode.parent;
        }
        return path;
    }
    private static Node getNodeFromSet(PriorityQueue<Node> set, Node node) {
        for (Node existingNode : set) {
            if (existingNode.equals(node)) {
                return existingNode;
            }
        }
        return null;
    }

}

    /*
    private static int DEFAULT_HV_COST = 10; // Horizontal - Vertical Cost
    private static int DEFAULT_DIAGONAL_COST = 14;
    private int hvCost;
    private int diagonalCost;
    private Node[][] searchArea;
    private PriorityQueue<Node> openList;
    private Set<Node> closedSet;
    private Node initialNode;
    private Node finalNode;

    public AI(int rows, int cols, Node initialNode, Node finalNode, int hvCost, int diagonalCost) {
        this.hvCost = hvCost;
        this.diagonalCost = diagonalCost;
        setInitialNode(initialNode);
        setFinalNode(finalNode);
        this.searchArea = new Node[rows][cols];
        this.openList = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node node0, Node node1) {
                return Integer.compare(node0.getF(), node1.getF());
            }
        });
        setNodes();
        this.closedSet = new HashSet<>();
    }

    public AStar(int rows, int cols, Node initialNode, Node finalNode) {
        this(rows, cols, initialNode, finalNode, DEFAULT_HV_COST, DEFAULT_DIAGONAL_COST);
    }

    private void setNodes() {
        for (int i = 0; i < searchArea.length; i++) {
            for (int j = 0; j < searchArea[0].length; j++) {
                Node node = new Node(i, j);
                node.calculateHeuristic(getFinalNode());
                this.searchArea[i][j] = node;
            }
        }
    }

    public void setBlocks(int[][] blocksArray) {
        for (int i = 0; i < blocksArray.length; i++) {
            int row = blocksArray[i][0];
            int col = blocksArray[i][1];
            setBlock(row, col);
        }
    }

    public List<Node> findPath() {
        openList.add(initialNode);
        while (!isEmpty(openList)) {
            Node currentNode = openList.poll();
            closedSet.add(currentNode);
            if (isFinalNode(currentNode)) {
                return getPath(currentNode);
            } else {
                addAdjacentNodes(currentNode);
            }
        }
        return new ArrayList<Node>();
    }

    private List<Node> getPath(Node currentNode) {
        List<Node> path = new ArrayList<Node>();
        path.add(currentNode);
        Node parent;
        while ((parent = currentNode.getParent()) != null) {
            path.add(0, parent);
            currentNode = parent;
        }
        return path;
    }

    private void addAdjacentNodes(Node currentNode) {
        addAdjacentUpperRow(currentNode);
        addAdjacentMiddleRow(currentNode);
        addAdjacentLowerRow(currentNode);
    }

    private void addAdjacentLowerRow(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int lowerRow = row + 1;
        if (lowerRow < getSearchArea().length) {
            if (col - 1 >= 0) {
                checkNode(currentNode, col - 1, lowerRow, getDiagonalCost()); // Comment this line if diagonal movements are not allowed
            }
            if (col + 1 < getSearchArea()[0].length) {
                checkNode(currentNode, col + 1, lowerRow, getDiagonalCost()); // Comment this line if diagonal movements are not allowed
            }
            checkNode(currentNode, col, lowerRow, getHvCost());
        }
    }

    private void addAdjacentMiddleRow(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int middleRow = row;
        if (col - 1 >= 0) {
            checkNode(currentNode, col - 1, middleRow, getHvCost());
        }
        if (col + 1 < getSearchArea()[0].length) {
            checkNode(currentNode, col + 1, middleRow, getHvCost());
        }
    }

    private void addAdjacentUpperRow(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int upperRow = row - 1;
        if (upperRow >= 0) {
            if (col - 1 >= 0) {
                checkNode(currentNode, col - 1, upperRow, getDiagonalCost()); // Comment this if diagonal movements are not allowed
            }
            if (col + 1 < getSearchArea()[0].length) {
                checkNode(currentNode, col + 1, upperRow, getDiagonalCost()); // Comment this if diagonal movements are not allowed
            }
            checkNode(currentNode, col, upperRow, getHvCost());
        }
    }

    private void checkNode(Node currentNode, int col, int row, int cost) {
        Node adjacentNode = getSearchArea()[row][col];
        if (!adjacentNode.isBlock() && !getClosedSet().contains(adjacentNode)) {
            if (!getOpenList().contains(adjacentNode)) {
                adjacentNode.setNodeData(currentNode, cost);
                getOpenList().add(adjacentNode);
            } else {
                boolean changed = adjacentNode.checkBetterPath(currentNode, cost);
                if (changed) {
                    // Remove and Add the changed node, so that the PriorityQueue can sort again its
                    // contents with the modified "finalCost" value of the modified node
                    getOpenList().remove(adjacentNode);
                    getOpenList().add(adjacentNode);
                }
            }
        }
    }

    private boolean isFinalNode(Node currentNode) {
        return currentNode.equals(finalNode);
    }

    private boolean isEmpty(PriorityQueue<Node> openList) {
        return openList.size() == 0;
    }

    private void setBlock(int row, int col) {
        this.searchArea[row][col].setBlock(true);
    }

    public Node getInitialNode() {
        return initialNode;
    }

    public void setInitialNode(Node initialNode) {
        this.initialNode = initialNode;
    }

    public Node getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(Node finalNode) {
        this.finalNode = finalNode;
    }

    public Node[][] getSearchArea() {
        return searchArea;
    }

    public void setSearchArea(Node[][] searchArea) {
        this.searchArea = searchArea;
    }

    public PriorityQueue<Node> getOpenList() {
        return openList;
    }

    public void setOpenList(PriorityQueue<Node> openList) {
        this.openList = openList;
    }

    public Set<Node> getClosedSet() {
        return closedSet;
    }

    public void setClosedSet(Set<Node> closedSet) {
        this.closedSet = closedSet;
    }

    public int getHvCost() {
        return hvCost;
    }

    public void setHvCost(int hvCost) {
        this.hvCost = hvCost;
    }

    private int getDiagonalCost() {
        return diagonalCost;
    }

    private void setDiagonalCost(int diagonalCost) {
        this.diagonalCost = diagonalCost;
    }

    @Override
    public List<Coords> calculate(World world) {
        Node initalNode = new Node(1,1);
        Node finalNode = new Node(2,2);

        AStar AStar = new AStar()
    }

}
*/
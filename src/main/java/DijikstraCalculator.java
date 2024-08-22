import java.util.*;

public class DijikstraCalculator {


     static String startNodeName;
      static String destinationNodeName;

    public DijikstraCalculator(String startN, String endN) {
        startNodeName=startN;
        destinationNodeName=endN;
    }

    // A class to represent a node in the graph
        static class Node {
            String name;
            int distance;

            Node(String name, int distance) {
                this.name = name;
                this.distance = distance;
            }
        }

        // Comparator for the priority queue
        static class NodeComparator implements Comparator<Node> {
            public int compare(Node node1, Node node2) {
                return Integer.compare(node1.distance, node2.distance);
            }
        }

        public static Map<String, Integer> dijkstra(Map<String, List<Node>> graph, String start) {
            // Priority queue to store nodes to be processed
            PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new NodeComparator());
            // Map to store the shortest path to each node
            Map<String, Integer> shortestPaths = new HashMap<>();
            // Initialize distances
            for (String node : graph.keySet()) {
                shortestPaths.put(node, Integer.MAX_VALUE);
            }
            shortestPaths.put(start, 0);
            priorityQueue.add(new Node(start, 0));

            while (!priorityQueue.isEmpty()) {
                Node current = priorityQueue.poll();
                String currentNode = current.name;
                int currentDistance = current.distance;

                // If we have already found a shorter path, skip processing
                if (currentDistance > shortestPaths.get(currentNode)) {
                    continue;
                }

                // Process each neighbor
                for (Node neighbor : graph.get(currentNode)) {
                    int newDistance = currentDistance + neighbor.distance;

                    if (newDistance < shortestPaths.get(neighbor.name)) {
                        shortestPaths.put(neighbor.name, newDistance);
                        priorityQueue.add(new Node(neighbor.name, newDistance));
                    }
                }
            }

            return shortestPaths;
        }

        public static String calcDistance() throws InterruptedException {

            String dijkTotal = null;

                // Define the graph as an adjacency list
                Map<String, List<Node>> graph = new HashMap<>();
                graph.put("A", Arrays.asList(new Node("B", 4), new Node("C", 6)));
                graph.put("B", Arrays.asList(new Node("A", 4), new Node("F", 2)));
                graph.put("C", Arrays.asList(new Node("A", 6), new Node("D", 8)));
                graph.put("D", Arrays.asList(new Node("C", 8), new Node("E", 4), new Node("G", 1)));
                graph.put("E", Arrays.asList(new Node("B", 2), new Node("F", 3), new Node("I", 8), new Node("D", 4)));
                graph.put("F", Arrays.asList(new Node("B", 2), new Node("E", 3), new Node("G", 4), new Node("H", 6)));
                graph.put("G", Arrays.asList(new Node("D", 1), new Node("F", 4), new Node("H", 5), new Node("I", 5)));
                graph.put("H", Arrays.asList(new Node("F", 6), new Node("G", 5)));
                graph.put("I", Arrays.asList(new Node("E", 8), new Node("G", 5)));


                Map<String, Integer> shortestPaths = dijkstra(graph, startNodeName);


                String keyToFind = destinationNodeName;

                // Iterate over the entries of the HashMap
                for (Map.Entry<String, Integer> entry : shortestPaths.entrySet()) {
                    // Get the key and value from the current entry
                    String key = entry.getKey();
                    Integer value = entry.getValue();

                    // Check if the current key matches the key we're looking for
                    if (key.equals(keyToFind)) {
                        System.out.println("Distance From source Node " + startNodeName + " to " + key + " from dijikstra calc: " + value);

                        dijkTotal= String.valueOf(value);
                        // Exit loop if key is found

                    }

                }


            return dijkTotal;
        }

        }





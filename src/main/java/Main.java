public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.deleteAllData();
        graph.createData();
        graph.databaseStatistics();
//        System.out.printf(graph.allRelationshipsForNodeByName("Pokemon", "Totodile"));
//        System.out.printf(graph.allRelationshipsForNodeByID(4));
//        System.out.printf(graph.pathBetweenNodesByID(4,5));
//        System.out.printf(graph.pathBetweenNodesByName("Region", "Sinnoh",
//                "Region", "Unova"));

    }
}

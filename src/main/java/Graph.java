import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;

public class Graph {

    private final GraphDatabase graphDatabase = GraphDatabase.createDatabase();

    public void databaseStatistics() {
        System.out.println(graphDatabase.runCypher("MATCH (n)\n" +
                "OPTIONAL MATCH (n)-[r]->(x)\n" +
                "WITH DISTINCT {l1: labels(n), r: type(r), l2: labels(x)}\n" +
                "AS `first degree connection`\n" +
                "RETURN first degree connection;"));
        System.out.println(graphDatabase.runCypher("CALL db.labels()"));
        System.out.println(graphDatabase.runCypher("CALL db.relationshipTypes()"));
    }

    public String deleteAllData(){
        return graphDatabase.runCypher("MATCH (n)\n" +
                "OPTIONAL MATCH (n)-[r]-()\n" +
                "DELETE n,r");
    }

    public void createData(){
        GraphDatabaseService graph = graphDatabase.getGraphDatabaseService();
        try(Transaction transaction = graph.beginTx()){

            //Regions
            Node kanto = createRegion(graph, "Kanto", "Oak", "Team Rocket");
            Node seviiIslands = createRegion(graph, "Sevii Islands", "None", "Team Rocket");
            Node johto = createRegion(graph, "Johto", "Elm", "Team Rocket");
            Node hoenn = createRegion(graph, "Hoenn", "Birch", "Team Aqua and Magma");
            Node sinnoh = createRegion(graph, "Sinnoh", "Rowan", "Team Galactic");
            Node unova = createRegion(graph, "Unova", "Juniper", "Team Plasma");
            Node kalos = createRegion(graph, "Kalos", "Sycamore", "Team Flare");
            Node alola = createRegion(graph, "Alola", "Kukui", "Team Skull and Rainbow");

            //Trainers
            Node caroleH = createTrainer(graph, "Carole", "Hammond", 15, "female");
            Node sethH = createTrainer(graph, "Seth", "Hariss", 17, "female");
            Node dorothyB = createTrainer(graph, "Dorothy", "Barton", 22, "female");
            Node constanceS = createTrainer(graph, "Constance", "Silva", 43, "female");
            Node danaG = createTrainer(graph, "Dana", "Garner", 19, "female");
            Node brytanB = createTrainer(graph, "Bryant", "Beck", 25, "male");
            Node kyleS = createTrainer(graph, "Kyle", "Sherman", 54, "male");
            Node alanW = createTrainer(graph, "Alan", "Williams", 26, "male");
            Node mauelL = createTrainer(graph, "Manuel", "Larson", 18, "male");
            Node garryH = createTrainer(graph, "Garry", "Holt", 14, "male");

            //Pokemon
            Node bulbasaur = createPokemon(graph, "Bulbasaur", "grass", 44.2);
            Node charmander = createPokemon(graph, "Charmander", "fire", 55.6);
            Node squirtle = createPokemon(graph, "Squirtle", "water", 52.2);

            //brak

            Node chikorita = createPokemon(graph, "Chikorita", "grass", 42.5);
            Node totodile = createPokemon(graph, "Totodile", "water", 40.2);
            Node skuntank = createPokemon(graph, "Skuntank", "poison", 22.5);

            Node mudkip = createPokemon(graph, "Mudkip", "water", 32.2);
            Node torchic = createPokemon(graph, "Torchic", "fire", 18.2);

            Node metagross = createPokemon(graph, "Metagross", "rock", 734.3);
            Node gible = createPokemon(graph, "Gible", "rock", 73.3);

            Node oshawoot = createPokemon(graph, "Oshawott", "water", 53.3);
            Node landours = createPokemon(graph, "Landorus", "ground", 152.2);
            Node cottonee = createPokemon(graph, "Cottonee", "fairy", 12.6);

            Node chespin = createPokemon(graph, "Chespin", "grass", 22.2);
            Node fennkein = createPokemon(graph, "Fennekin", "fire", 24.2);

            Node wingull = createPokemon(graph, "Wingull", "flying", 30.0);

            //Relations
            createLivesRelationship(bulbasaur, kanto);
            createLivesRelationship(charmander, kanto);
            createLivesRelationship(squirtle, kanto);
            createLivesRelationship(chikorita, johto);
            createLivesRelationship(totodile, johto);
            createLivesRelationship(skuntank, johto);
            createLivesRelationship(mudkip, hoenn);
            createLivesRelationship(torchic, hoenn);
            createLivesRelationship(metagross, sinnoh);
            createLivesRelationship(gible, sinnoh);
            createLivesRelationship(oshawoot, unova);
            createLivesRelationship(landours, unova);
            createLivesRelationship(cottonee, unova);
            createLivesRelationship(chespin, kalos);
            createLivesRelationship(fennkein, kalos);
            createLivesRelationship(wingull, alola);

            createComesRelationship(caroleH, kanto);
            createComesRelationship(sethH, seviiIslands);
            createComesRelationship(dorothyB, johto);
            createComesRelationship(constanceS, hoenn);
            createComesRelationship(danaG, sinnoh);
            createComesRelationship(brytanB, sinnoh);
            createComesRelationship(kyleS, unova);
            createComesRelationship(alanW, kalos);
            createComesRelationship(mauelL, alola);
            createComesRelationship(garryH, seviiIslands);

            createHaveRelationship(caroleH, fennkein);
            createHaveRelationship(sethH, totodile);
            createHaveRelationship(sethH, mudkip);
            createHaveRelationship(dorothyB, wingull);
            createHaveRelationship(constanceS, oshawoot);
            createHaveRelationship(constanceS, chespin);
            createHaveRelationship(constanceS, metagross);
            createHaveRelationship(danaG, squirtle);
            createHaveRelationship(danaG, skuntank);
            createHaveRelationship(brytanB, cottonee);
            createHaveRelationship(brytanB, bulbasaur);
            createHaveRelationship(kyleS, gible);
            createHaveRelationship(kyleS, charmander);
            createHaveRelationship(alanW, charmander);
            createHaveRelationship(alanW, chikorita);
            createHaveRelationship(alanW, torchic);
            createHaveRelationship(mauelL, landours);
            createHaveRelationship(mauelL, totodile);
            createHaveRelationship(garryH, chikorita);
            createHaveRelationship(garryH, mudkip);
            createHaveRelationship(garryH, gible);
            createHaveRelationship(garryH, chespin);

            createKnowsRelationship(caroleH, sethH);
            createKnowsRelationship(caroleH, dorothyB);
            createKnowsRelationship(sethH, alanW);
            createKnowsRelationship(dorothyB, alanW);
            createKnowsRelationship(dorothyB, garryH);
            createKnowsRelationship(constanceS, danaG);
            createKnowsRelationship(danaG, kyleS);
            createKnowsRelationship(danaG, mauelL);
            createKnowsRelationship(brytanB, garryH);
            createKnowsRelationship(alanW, caroleH);
            createKnowsRelationship(alanW, brytanB);
            createKnowsRelationship(mauelL, constanceS);
            createKnowsRelationship(mauelL, dorothyB);
            createKnowsRelationship(garryH, mauelL);
            createKnowsRelationship(garryH, danaG);
            createKnowsRelationship(garryH, sethH);

            transaction.success();
        }
    }

    private Node createPokemon(GraphDatabaseService gdb, String name, String type, double weight){
        Node pokemon = gdb.createNode();
        pokemon.addLabel(() -> "Pokemon");
        pokemon.setProperty("name", name);
        pokemon.setProperty("type", type);
        pokemon.setProperty("weight", weight);
        return pokemon;
    }

    private Node createTrainer(GraphDatabaseService gdb, String name, String lastName, int age, String gender){
        Node trainer = gdb.createNode();
        trainer.addLabel(() -> "Trainer");
        trainer.setProperty("name", name);
        trainer.setProperty("lastName", lastName);
        trainer.setProperty("age", age);
        trainer.setProperty("gender", gender);
        return trainer;
    }

    private Node createRegion(GraphDatabaseService gdb, String name, String professor, String regionVillains){
        Node region = gdb.createNode();
        region.addLabel(() -> "Region");
        region.setProperty("name", name);
        region.setProperty("professor", professor);
        region.setProperty("regionVillains", regionVillains);
        return region;
    }

    private void createLivesRelationship(Node pokemon, Node region){
        pokemon.createRelationshipTo(region, RelationshipType.withName("LIVES"));
    }
    private void createComesRelationship(Node trainer, Node region){
        trainer.createRelationshipTo(region, RelationshipType.withName("COMES"));
    }
    private void createHaveRelationship(Node trainer, Node pokemon){
        trainer.createRelationshipTo(pokemon, RelationshipType.withName("HAVE"));
    }
    private void createKnowsRelationship(Node trainer1, Node trainer2){
        trainer1.createRelationshipTo(trainer2, RelationshipType.withName("KNOWS"));
        trainer2.createRelationshipTo(trainer1, RelationshipType.withName("KNOWS"));
    }



}

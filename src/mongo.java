import com.mongodb.Block;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class mongo {

    static Block<Document> printBlock = new Block<Document>() {
        @Override
        public void apply(final Document document) {
            System.out.println(document.toJson());
        }
    };

    public static void main( String args[] ) {


        MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://admin:ADMIN@cluster0786-eve5j.mongodb.net/FIRST?" +
                        "retryWrites=true&w=majority&connectTimeoutMS=30000&socketTimeoutMS=30000");
        MongoDatabase database = mongoClient.getDatabase("FIRST");
        System.out.println(database);
        System.out.println("connected !");
        MongoCollection<Document> coll = database.getCollection("ONE");
        System.out.println(coll);
        /*for (String name : database.listCollectionNames()) {
            System.out.println(name);
        }*/

        System.out.println("Collection name  :: "+database.listCollectionNames());
        for (String name:database.listCollectionNames())
            System.out.println("name:: "+name);
//method without any arguments to query all documents in a collection:


        //coll.find(eq("name", "456 Cookies Shop")).forEach(printBlock);

        Document document = new Document("_id",1)
                //.append("_id",2,new Document())
                .append("_id", new Document("phone", "49"))
                .append("_id", new Document("phone", "55"));

       // coll.insertOne(document);

       /* coll.bulkWrite(
                Arrays.asList(new InsertOneModel<>(new Document("_id", 4)),
                        new InsertOneModel<>(new Document("_id", 5)),
                        new InsertOneModel<>(new Document("_id", 6)),
                        new UpdateOneModel<>(new Document("_id", 1),
                                new Document("$set", new Document("x", 2))),
                        new DeleteOneModel<>(new Document("_id", 2)),
                        new ReplaceOneModel<>(new Document("_id", 3),
                                new Document("_id", 3).append("x", 4))));


        // 2. Unordered bulk operation - no guarantee of order of operation
        coll.bulkWrite(
                Arrays.asList(new InsertOneModel<>(new Document("_id", 4)),
                        new InsertOneModel<>(new Document("_id", 5)),
                        new InsertOneModel<>(new Document("_id", 6)),
                        new UpdateOneModel<>(new Document("_id", 1),
                                new Document("$set", new Document("x", 2))),
                        new DeleteOneModel<>(new Document("_id", 2)),
                        new ReplaceOneModel<>(new Document("_id", 3),
                                new Document("_id", 3).append("x", 4))),
                new BulkWriteOptions().ordered(false));
*/

        coll.find().forEach(printBlock);








        // Creating a Mongo client
       /* MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();
        //options.socketKeepAlive(true);
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://admin:<admin>@cluster0786-eve5j" +
                ".mongodb.net/test?retryWrites=true&w=majority/?ssl=true"));

        // Creating Credentials

        System.out.println("Connected to the database successfully");

        // Accessing the database
        MongoDatabase database = mongoClient.getDatabase("test");
        System.out.println("Database name ::"+ database);
        List<String> dbname=mongoClient.getDatabaseNames();
        System.out.
        (dbname);*/
    }
}
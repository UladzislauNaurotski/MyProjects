import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ParseAndMongodbFile {

    private static List<Student> listStudents;
    private static MongoCollection<Document> listStudentsInMongo;

    public List<Student> getLinesStudent(String file) {
        listStudents = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(file));
            for (int i = 0; i < lines.size(); i++) {
                String[] line = lines.get(i).split(",(?=(?:[^\\\"]*|\\\"[^\\\"]*\\\")*$)");
                if (line.length != 3) {
                    continue;
                }
                listStudents.add(new Student(line[0], Integer.parseInt(line[1]), line[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listStudents;
    }

    public MongoCollection<Document> getLinesMongoDB(List<Student> listStudents
            , MongoDatabase database, String nameCollection) {
        listStudentsInMongo = database.getCollection(nameCollection);
        listStudentsInMongo.drop();
        for (int i = 0; i < listStudents.size(); i++) {
            Document document = new Document();
            document.append("name", listStudents.get(i).getName());
            document.append("age", listStudents.get(i).getAge());
            document.append("courses", listStudents.get(i).getCourses());
            listStudentsInMongo.insertOne(document);
        }
        return listStudentsInMongo;
    }
}

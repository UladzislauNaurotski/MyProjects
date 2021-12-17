import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.elemMatch;
import static com.mongodb.client.model.Filters.gt;

public class StudentsTest {

    public static final String way = "mongo.csv";
    public static final String nameCollection = "students";

    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");
        ParseAndMongodbFile parseAndMongodbFile = new ParseAndMongodbFile();
        List<Student> studentsList = parseAndMongodbFile.getLinesStudent(way);
        MongoCollection<Document> listStudents = parseAndMongodbFile.getLinesMongoDB(studentsList, database, nameCollection);
        long countStudent = listStudents.count();
        System.out.println("Всего студентов = " + countStudent + " чел.");
        int countStudentOld = (int) listStudents.countDocuments(gt("age", 40));
        System.out.println("Количество студентов старше 40 лет = " + countStudentOld);
        listStudents.find().sort(new BasicDBObject("age", 1)).limit(1).forEach((Consumer<? super Document>) doc -> {
            System.out.println("Имя самого молодого студента - " + doc.get("name"));
        });
        listStudents.find().sort(new BasicDBObject("age", -1)).limit(1).forEach((Consumer<? super Document>) doc -> {
            System.out.println("Список курсов самого старого студента - " + doc.get("courses"));
} );
    }

}

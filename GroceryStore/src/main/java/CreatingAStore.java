import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Field;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.eq;

public class CreatingAStore {

    private MongoCollection<Document> shops;
    private MongoCollection<Document> goods;

    public void init() {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");
        shops = database.getCollection("Shops");
        goods = database.getCollection("Goods");
        shops.drop();
        goods.drop();
    }

    public void addShop(String nameShop) {
        Shop shop = new Shop(nameShop, new ArrayList<>());
        Document shopDoc = new Document()
                .append("Name", shop.getName())
                .append("Goods", shop.getListProductsShop());
        shops.insertOne(shopDoc);
    }

    public void addProduct(String nameProduct, int price) {
        Product product = new Product(nameProduct, price);
        Document goodDoc = new Document()
                .append("Name", product.getName())
                .append("Price", product.getPrice());
        goods.insertOne(goodDoc);
    }

    public void addProductToShop(String shop, String good) {
        Document goodDoc = new Document("Goods", good);
        Document document = new Document("$push", goodDoc);
        shops.findOneAndUpdate(eq("Name", shop), document);
    }

    public void getStat() {
        List<Bson> params = Arrays.asList(
                lookup("Goods", "Goods", "Name", "Result"),
                addFields(new Field("MaxPricedGood",
                                Document.parse("{$filter: {input: '$Result', as: 'res', cond: {'$eq' : ['$$res.Price', {'$max': '$Result.Price'}]}}}")),
                        new Field("MinPricedGood",
                                Document.parse("{$filter: {input: '$Result', as: 'res', cond: {'$eq' : ['$$res.Price', {'$min': '$Result.Price'}]}}}")),
                        new Field("CheapGoods",
                                Document.parse("{$filter: {input: '$Result', as: 'res', cond: {'$lt' : ['$$res.Price', 100]}}}"))),
                project(new Document()
                        .append("Name", "$Name")
                        .append("Count", new Document("$size", "$Goods"))
                        .append("AvgPrice", new Document("$avg", "$Result.Price"))
                        .append("MaxPricedGoodName", new Document("$first", "$MaxPricedGood.Name"))
                        .append("MinPricedGoodName", new Document("$first", "$MinPricedGood.Name"))
                        .append("CheapGoods", new Document("$size", "$CheapGoods"))
                ));

        for (Document doc : shops.aggregate(params)) {
            System.out.printf("Магазин %s:\n", doc.getString("Name"));
            System.out.printf("%d единицы товаров\n", doc.getInteger("Count"));
            System.out.printf("Средняя цена товара - %.2f рублей\n", doc.getDouble("AvgPrice"));
            System.out.printf("Самый дорогой товар - %s\n", doc.getString("MaxPricedGoodName"));
            System.out.printf("Самый дешёвый товар - %s\n", doc.getString("MinPricedGoodName"));
            System.out.printf("Количество товаров дешевле 100 рублей - %d\n\n", doc.getInteger("CheapGoods"));
        }
    }
}

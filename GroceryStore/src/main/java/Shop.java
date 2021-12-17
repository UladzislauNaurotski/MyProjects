import java.util.List;

public class Shop {

    private String name;
    private List<Product> listProductsShop;

    public Shop(String name, List<Product> listProductsShop) {
        this.name = name;
        this.listProductsShop = listProductsShop;
    }

    public String getName() {
        return name;
    }

    public List<Product> getListProductsShop() {
        return listProductsShop;
    }
}

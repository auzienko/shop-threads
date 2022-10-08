import entity.Dispatcher;
import entity.QueueCustomer;
import entity.Shop;
import service.ShopWorker;

public class Runner {
    public static void main(String[] args) {
        Shop shop = new Shop(
                "EuroShop",
                new Dispatcher(100),
                new QueueCustomer()
        );
        ShopWorker shopWorker = new ShopWorker(shop);
        shopWorker.start();
    }
}

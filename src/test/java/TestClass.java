import com.google.gson.Gson;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class TestClass {
    private static final double TAX = 0.2;
    private final Gson gson;
    List<RealItem> realItems;
    List<VirtualItem> virtualItems;
    private double total;

    public TestClass() {
        gson = new Gson();
        realItems=new ArrayList<>();
        virtualItems=new ArrayList<>();
    }

    public  void writeFile(Cart cart){
        try (FileWriter writer = new FileWriter("src/main/resources/" + cart.getCartName() + ".json")) {
            writer.write(gson.toJson(cart));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Cart readFromFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return gson.fromJson(reader.readLine(), Cart.class);
    }
    public void addRealItems(RealItem realItem){
        realItems.add(realItem);
        total += realItem.getPrice() + realItem.getPrice()*TAX;
    }
    public void addVirtualItem(VirtualItem item) {
        virtualItems.add(item);
        total += item.getPrice() + item.getPrice()*TAX;
    }
    public double getTotalPrice() {
       return total;

    }

}
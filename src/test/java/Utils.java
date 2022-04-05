import com.google.gson.Gson;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Utils {
    private static final double TAX = 0.2;
    private static Gson gson = null;
    List<RealItem> realItems;
    List<VirtualItem> virtualItems;
    private double total;

    public Utils() {
        gson = new Gson();
        realItems=new ArrayList<>();
        virtualItems=new ArrayList<>();
    }

    public  static void writeFile(Cart cart){
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
    public double addRealItems(RealItem realItem){
        realItems.add(realItem);
        return realItem.getPrice() + realItem.getPrice()*TAX;
    }

    public static double getPrice(double price)
    {
       return  price*(1+TAX);
    }

}
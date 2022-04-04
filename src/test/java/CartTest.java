

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;




public class CartTest {
    static Utils utils =new Utils();
    static Cart cart;
    static RealItem car1;
    static RealItem car2;
    static VirtualItem disk;

    @BeforeAll
    public static void init(){
        //create test data
        cart=new Cart("eugen-cart");
        car1 = new RealItem();
        car1.setName("Mazda");
        car1.setPrice(52026.9);
        car1.setWeight(1860);

        car2 = new RealItem();
        car2.setName("Honda");
        car2.setPrice(44226.3);
        car2.setWeight(1990);

    }

    @Test
    @DisplayName("Test for adding Real Item")
    public void addRealItemTest(){
        cart.addRealItem(car1);
        double result=cart.getTotalPrice();
        //get expected result
        //utils.addRealItems(car);
        double expectedresult= utils.addRealItems(car1);
        Assert.assertEquals(result,expectedresult);

    }

    @Test
    public void deleteRealItemTest(){
        cart.addRealItem(car1);
        cart.addRealItem(car2);
        cart.deleteRealItem(car1);
        //get expected result

        double totalPrice= Utils.getPrice(car1.getPrice()+car2.getPrice()-car1.getPrice());
        Assert.assertEquals(cart.getTotalPrice(),totalPrice);
    }


}

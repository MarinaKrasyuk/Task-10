
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import parser.JsonParser;
import parser.NoSuchFileException;
import parser.Parser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.File;
import java.io.IOException;



public class FirstTest {

    Parser parser=new JsonParser();
    static TestClass testClass=new TestClass();
    static Cart cart;
    static RealItem car;
    static VirtualItem disk;

    @BeforeAll
    public static void init(){
        //create test data
        cart=new Cart("eugen-cart");
        car = new RealItem();
        car.setName("Mazda");
        car.setPrice(52026.9);
        car.setWeight(1860);

        disk = new VirtualItem();
        disk.setName("MacOs");
        disk.setPrice(15);
        disk.setSizeOnDisk(250000);


    }
    @Test
    @Disabled
    @DisplayName("Exception: Invalid File")
    void readFromFileTestException() {
        File file=new File("");
        Exception exception = assertThrows(NoSuchFileException.class, () ->
                parser.readFromFile(file));
        assertEquals(String.format("File %s.json not found!", file), exception.getMessage());
    }

    @Test
    @DisplayName("Test for adding Real Item")
    public void addRealItemTest(){
        cart.addRealItem(car);
        double result=cart.getTotalPrice();
        //get expected result
        testClass.addRealItems(car);
        double expectedresult= testClass.getTotalPrice();
        Assert.assertEquals(result,expectedresult);

    }
    @Test
    @DisplayName("Test for adding Virtual Item")
    public void addVirtualItemTest(){
        cart.addVirtualItem(disk);
        double result=cart.getTotalPrice();
        //get expected result
        testClass.addVirtualItem(disk);
        double expectedresult= testClass.getTotalPrice();
        Assert.assertEquals(result,expectedresult);

    }
    @Test
    @DisplayName("Write to File")
    void writeToFileTest() throws IOException {
        parser.writeToFile(cart);
        //get expected cart
        Cart expectedresult= testClass.readFromFile(new File("src/main/resources/eugen-cart.json"));
        Assert.assertEquals(cart.getCartName(), expectedresult.getCartName());
        Assert.assertEquals(cart.getTotalPrice(), expectedresult.getTotalPrice());

    }
    @Test
    @DisplayName("Read from File")
    void readFromFileTest() throws IOException {

        testClass.writeFile(cart);
        //get expected cart
        Cart expectedresult=parser.readFromFile(new File("src/main/resources/eugen-cart.json"));
        Assert.assertEquals(cart.getCartName(), expectedresult.getCartName());
        Assert.assertEquals(cart.getTotalPrice(), expectedresult.getTotalPrice());

    }


}

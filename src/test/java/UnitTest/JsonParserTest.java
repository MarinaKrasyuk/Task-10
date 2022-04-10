package UnitTest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.testng.annotations.*;
import org.testng.Assert;
import parser.JsonParser;
import parser.NoSuchFileException;
import parser.Parser;
import shop.Cart;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonParserTest {
    Parser parser=new JsonParser();
    static Utils utils =new Utils();
    static Cart cart;

    @BeforeSuite(alwaysRun = true)
    public void init() {
        //create test data
        cart = new Cart("eugen-cart");
    }

    @Parameters("path")
    @Test
    void readFromFileTestException(String path) {
        File file=new File(path);
        Exception exception = assertThrows(NoSuchFileException.class, () ->
                parser.readFromFile(file));
        assertEquals(String.format("File %s.json not found!", file), exception.getMessage());
    }


    @Test (groups = "excludeTest")
    void writeToFileTest() throws IOException {
        parser.writeToFile(cart);
        //get expected cart
        Cart expectedresult= utils.readFromFile(new File("src/main/resources/eugen-cart.json"));
        Assert.assertEquals(cart.getCartName(), expectedresult.getCartName());
        Assert.assertEquals(cart.getTotalPrice(), expectedresult.getTotalPrice());

    }

    @Test(groups = "includeTest")
    void readFromFileTest() throws IOException {

        utils.writeFile(cart);
        //get expected cart
        Cart expectedresult=parser.readFromFile(new File("src/main/resources/eugen-cart.json"));
        Assert.assertEquals(cart.getCartName(), expectedresult.getCartName());
        Assert.assertEquals(cart.getTotalPrice(), expectedresult.getTotalPrice());

    }

    @DataProvider(name = "testDate")
    public Object[][] createData() {
        Object[][] data = new Object[][] { { "" }, {"src/resources/" }, { "test.json" }, {"src/main/resources/test.json" } };
        return data;
    }
    @Test(dataProvider = "testDate")
    public void TestException(String path){
        File file=new File(path);
        Exception exception = assertThrows(NoSuchFileException.class, () ->
                parser.readFromFile(file));
        assertEquals(String.format("File %s.json not found!", file), exception.getMessage());
    }

}

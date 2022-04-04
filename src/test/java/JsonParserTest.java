import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.testng.Assert;
import parser.JsonParser;
import parser.NoSuchFileException;
import parser.Parser;
import shop.Cart;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonParserTest {
    Parser parser=new JsonParser();
    static Utils utils =new Utils();
    static Cart cart;

    @BeforeAll
    public static void init() {
        //create test data
        cart = new Cart("eugen-cart");
    }

    @ParameterizedTest(name = "Exception: Invalid File")
    @ValueSource(strings = { "", "src/resources/", "test.json","src/main/resources/test.json","src/main/resources/test.txt" })
    void readFromFileTestException(String strings) {
        File file=new File(strings);
        Exception exception = assertThrows(NoSuchFileException.class, () ->
                parser.readFromFile(file));
        assertEquals(String.format("File %s.json not found!", file), exception.getMessage());
    }
    @Test
    @DisplayName("Write to File")
    void writeToFileTest() throws IOException {
        parser.writeToFile(cart);
        //get expected cart
        Cart expectedresult= utils.readFromFile(new File("src/main/resources/eugen-cart.json"));
        Assert.assertEquals(cart.getCartName(), expectedresult.getCartName());
        Assert.assertEquals(cart.getTotalPrice(), expectedresult.getTotalPrice());

    }
    @Disabled
    @Test
    @DisplayName("Read from File")
    void readFromFileTest() throws IOException {

        utils.writeFile(cart);
        //get expected cart
        Cart expectedresult=parser.readFromFile(new File("src/main/resources/eugen-cart.json"));
        Assert.assertEquals(cart.getCartName(), expectedresult.getCartName());
        Assert.assertEquals(cart.getTotalPrice(), expectedresult.getTotalPrice());

    }
}

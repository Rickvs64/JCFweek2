package Test;

import Classes.HuffmanTree;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HuffmanTreeTest {
    @Test
    public void validateString1() {
        HuffmanTree tree = new HuffmanTree("Goedemorgen allemaal!");

        Assert.assertEquals("Goedemorgen allemaal!", tree.getDecoded());
    }

    @Test
    public void validateString2() {
        HuffmanTree tree = new HuffmanTree("Mijn naam is Ricardo.");

        Assert.assertEquals("Mijn naam is Ricardo.", tree.getDecoded());
    }

    @Test
    public void validateArabic() {
        HuffmanTree tree = new HuffmanTree("صباح الخير");

        Assert.assertEquals("صباح الخير", tree.getDecoded());
    }

    @Test
    public void validateChinese() {
        HuffmanTree tree = new HuffmanTree("早上好");

        Assert.assertEquals("早上好", tree.getDecoded());
    }

    @Test
    public void validateUppercase() {
        HuffmanTree tree = new HuffmanTree("CaPiTaLs");

        Assert.assertNotEquals("Capitals", tree.getDecoded());
    }
}
package es.merlinsoftware;

import static org.junit.Assert.assertTrue;

import es.merlinsoftware.pojo.ProductSales;
import es.merlinsoftware.pojo.ProductStock;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingScoresTest {
    @Test
    public void testHappyPath() {
        List<ProductSales> productSales = new ArrayList<>();
        productSales.add(new ProductSales(1L, 10000.0));
        productSales.add(new ProductSales(2L, 50000.0));
        productSales.add(new ProductSales(3L, 100000.0));
        productSales.add(new ProductSales(4L, 75000.0));


        List<ProductStock> productStock = new ArrayList<>();
        productStock.add(new ProductStock(1L, 100000L));
        productStock.add(new ProductStock(2L, 400000L));
        productStock.add(new ProductStock(3L, 200000L));
        productStock.add(new ProductStock(4L, 300000L));


        Long[] expectedResult = {2L, 4L, 3L, 1L};

        Assert.assertArrayEquals(Solution.sortProductsByScores(50, 50, productStock, productSales).toArray(),
                expectedResult);
    }
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testProductsSalesInformationNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("productsSalesInformation no puede ser null");
        Solution.sortProductsByScores(50, 50, new ArrayList<>(), null);
    }
    
    @Test
    public void testProductsStockInformationNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("productsStockInformation no puede ser null");
        Solution.sortProductsByScores(50, 50, null, new ArrayList<>());
    }
    
    @Test
    public void testEmptyLists() {
        List<ProductSales> productSales = new ArrayList<>();
        List<ProductStock> productStock = new ArrayList<>();

        Long[] expectedResult = {};

        Assert.assertArrayEquals(Solution.sortProductsByScores(50, 50, productStock, productSales).toArray(),
                expectedResult);
    }
    
    @Test
    public void testZeroWeights() {
    	thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("La suma de pesos no puede ser 0");
        Solution.sortProductsByScores(0, 0, new ArrayList<>(), new ArrayList<>());
    }
    
    @Test
    public void testDifferentLists() {
        List<ProductSales> productSales = new ArrayList<>();
        productSales.add(new ProductSales(1L, 10000.0));
        productSales.add(new ProductSales(2L, 50000.0));
        productSales.add(new ProductSales(3L, 100000.0));
        productSales.add(new ProductSales(4L, 75000.0));


        List<ProductStock> productStock = new ArrayList<>();
        productStock.add(new ProductStock(5L, 100000L));
        productStock.add(new ProductStock(6L, 400000L));
        productStock.add(new ProductStock(3L, 200000L));
        productStock.add(new ProductStock(4L, 300000L));


        Long[] expectedResult = {6L, 4L, 3L, 5L, 2L, 1L};

        Assert.assertArrayEquals(Solution.sortProductsByScores(50, 50, productStock, productSales).toArray(),
                expectedResult);
    }
    
}

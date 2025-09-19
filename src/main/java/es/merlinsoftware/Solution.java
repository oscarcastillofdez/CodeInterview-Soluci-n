package es.merlinsoftware;

import es.merlinsoftware.pojo.ProductSales;
import es.merlinsoftware.pojo.ProductStock;

import java.util.*;

public class Solution {

    public static List<Long> sortProductsByScores(int stockWeight, int salesWeight,
                                                  List<ProductStock> productsStockInformation,
                                                  List<ProductSales> productsSalesInformation) {

    	// Validación de la entrada
    	Objects.requireNonNull(productsStockInformation, "productsStockInformation no puede ser null");
        Objects.requireNonNull(productsSalesInformation, "productsSalesInformation no puede ser null");

        if (stockWeight == 0 && salesWeight == 0) {
            throw new IllegalArgumentException("La suma de pesos no puede ser 0");
        }

        // Cálculo de hashmaps 
        Map<Long, Double> stockMap = new HashMap<>();
        for (ProductStock ps : productsStockInformation) {
            if (ps != null) {
                stockMap.put(ps.getProductId(), Math.max(0d, (double)ps.getAvailableStock()));
            }
        }
        Map<Long, Double> salesMap = new HashMap<>();
        for (ProductSales ps : productsSalesInformation) {
            if (ps != null) {
                salesMap.put(ps.getProductId(), Math.max(0d, ps.getSalesAmount()));
            }
        }

        // Unión de ids, por si falta alguna id en alguna de las listas
        Set<Long> productIds = new HashSet<>();
        productIds.addAll(stockMap.keySet());
        productIds.addAll(salesMap.keySet());
        if (productIds.isEmpty()) return Collections.emptyList();

        // Cálculo de los scores para la comparación
        Map<Long, Double> scoresMap = new HashMap<>();
        for (Long id : productIds) {
            double sales = salesMap.getOrDefault(id, 0d);
            double stock = stockMap.getOrDefault(id, 0d);

            double score = sales * salesWeight + stock * stockWeight;
            scoresMap.put(id, score);
        }
        
        List<Long> ordered = new ArrayList<>(productIds);
        ordered.sort((a, b) -> {
            int cmp = Double.compare(scoresMap.get(b), scoresMap.get(a));
            if (cmp != 0) return cmp;
            return Long.compare(a, b);
        });
        
        return ordered;
    }
}
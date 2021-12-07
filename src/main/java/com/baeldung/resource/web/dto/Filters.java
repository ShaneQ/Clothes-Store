package com.baeldung.resource.web.dto;


import static org.apache.logging.log4j.util.Strings.isNotBlank;

import com.baeldung.resource.persistence.model.Product;
import java.util.Map;

public class Filters {

    public static final String FILTER_BY_SIZE = "filterBySize";
    public static final String FILTER_BY_COLOR = "filterByColor";
    public static final String FILTER_BY_SEASON = "filterBySeason";
    public static final String FILTER_BY_CATEGORY = "filterByCategory";

    public static boolean filterBySize(Product product, Map<String, String> filters) {
        boolean result = true;
        if (filters.containsKey(FILTER_BY_SIZE) && isNotBlank(filters.get(FILTER_BY_SIZE)))  {
            result = Sizes.contains(product.getSizes(), filters.get(FILTER_BY_SIZE).split(","));
        }
        return result;
    }

    public static boolean filterByColor(Product product, Map<String, String> filters) {
        boolean result = true;
        if (filters.containsKey(FILTER_BY_COLOR) && isNotBlank(filters.get(FILTER_BY_COLOR)))  {
            result = Colors.equals(product.getColor(), filters.get(FILTER_BY_COLOR).split(","));
        }
        return result;
    }

    public static boolean filterBySeason(Product product, Map<String, String> filters) {
        boolean result = true;
        if (filters.containsKey(FILTER_BY_SEASON) && isNotBlank(filters.get(FILTER_BY_SEASON)))  {
            result = Seasons.equals(product.getSeason(), filters.get(FILTER_BY_SEASON).split(","));
        }
        return result;
    }

    public static boolean filterByCategory(Product product, Map<String, String> filters) {
        boolean result = true;
        if (filters.containsKey(FILTER_BY_CATEGORY) && isNotBlank(filters.get(FILTER_BY_CATEGORY)))  {
            result = Categories.equals(product.getCategory(), filters.get(FILTER_BY_CATEGORY).split(","));
        }
        return result;
    }
}

package en.builin.app;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class Main {
    public static void main(String[] args) {
        Multimap<String, String> groceryCart = ArrayListMultimap.create();

        groceryCart.put("Fruits", "Apple");
        groceryCart.put("Fruits", "Grapes");
        groceryCart.put("Fruits", "Strawberries");
        groceryCart.put("Vegetables", "Spinach");
        groceryCart.put("Vegetables", "Cabbage");

        System.out.println(groceryCart);
    }
}

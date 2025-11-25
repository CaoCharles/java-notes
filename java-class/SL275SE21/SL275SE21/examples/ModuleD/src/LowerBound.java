import java.util.List;

public class LowerBound {
    public static void addFoodToFoods(List<Food> order, Food food) {
        order.add(food);
    }

    public static void addFoodToFoodParents(List<? super Food> order, Food food) {
        order.add(food);
    }

    public static void addFood(List<? super Food> o, Food i) {
        o.add(i);
    }

    public static void addDrink(List<? super Drink> o, Drink i) {
        o.add(i);
    }

    public static void processOrder(List<? extends Product> o) {
        o.stream().forEach(p -> p.prepare());
    }

    public static void addProductAndProcessOrder(List<Product> o, Product i) {
        o.add(i);
        o.stream().forEach(p -> p.prepare());
    }

    public static void main(String[] args) {
        Product p1 = new Food("Cake", 2.99);
        Product p2 = new Drink("Tea", 1.99);
        Product p3 = new Food("Cookie", 2.99);
        List<Product> products = List.of(p1, p2, p3);
        List<Food> foods = List.of((Food) p1, (Food) p3);
        Food f = new Food("Cake", 2.99);
        addFoodToFoods(foods, f);
//        addFoodToFoods(products, f);
        addFoodToFoodParents(foods, f);
        addFoodToFoodParents(products, f);
    }
}

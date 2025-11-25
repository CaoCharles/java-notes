package practices;

public class HelloInterface {

    public static void main(String[] args) {
        Shirt shirt = new Shirt(100, "Nice Shirt", 'R', 12.25, 'M');
        Trousers trousers = new Trousers(101, "Nice Trousers", 'R', 10.99, 'S', 'M');
        CustomShirt customShirt = new CustomShirt(102, "My Shirt", 'B', 22.5, 10);
        
        Tent tent = new Tent(200, "Nice Tent", 3.2, 50, "Family");
        CampStove campStove = new CampStove(201, "Nice Stove", 1.5, 15, 'M');
        StoveFuel stoveFuel = new StoveFuel(202, "Stove fuel", 1.2, 20, 1);

        processReturn(shirt);
        processReturn(trousers);
//        processReturn(customShirt);
        processReturn(tent);
        processReturn(campStove);
//        processReturn(stoveFuel);
    }

    public static void processReturn(Returnable r) {
        String data =
                switch (r) {
                    case Clothing c -> c.getDesc() + ", " + c.getPrice();
                    case Outdoors o -> o.getDesc() + ", " + o.getWeight();
                    default -> "N/A";
                };
        System.out.println(data + ", " + r.doReturn());
    }
}

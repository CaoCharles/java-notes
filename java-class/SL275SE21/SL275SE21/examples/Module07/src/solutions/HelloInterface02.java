package solutions;

public class HelloInterface02 {

    public static void main(String[] args) {
        Shirt shirt = new Shirt(100, "Nice Shirt", 'R', 12.25, 'M');
        Trousers trousers = new Trousers(101, "Nice Trousers", 'R', 10.99, 'S', 'M');
        CustomShirt customShirt = new CustomShirt(102, "My Shirt", 'B', 22.5, 10);

        Tent tent = new Tent(200, "Nice Tent", 3.2, 50, "Family");
        CampStove campStove = new CampStove(201, "Nice Stove", 1.5, 15, 'M');
        StoveFuel stoveFuel = new StoveFuel(202, "Stove fuel", 1.2, 20, 1);

        printDetails(shirt);
        printDetails(trousers);
        printDetails(customShirt);
        printDetails(tent);
        printDetails(campStove);
        printDetails(stoveFuel);

        System.out.println();

        shirt.report();
        trousers.report();
        customShirt.report();
        tent.report();
        campStove.report();
        stoveFuel.report();
    }

    // 宣告名稱為printDetails的static方法, 接收一個Printable參數
    public static void printDetails(Printable p) {
        System.out.println(p.getDetails());
    }

}

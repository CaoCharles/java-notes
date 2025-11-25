public class Dog extends Animal {
    String name;

    void fetch(Ball ball) {
        ball.find();
        ball.chew();
    }
}

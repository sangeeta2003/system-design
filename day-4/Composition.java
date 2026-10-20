class Engine{
    void start(){
         System.out.println("Engine started");
    }
}
 class Car{
    private Engine engine;  // Car HAS-A Engine
    Car(){
        engine = new Engine();

    }
    void drives(){
        engine.start();
        System.out.println("Car is moving");
    }


 }
 public class Composition{
     public static void main(String[] args) {
Car car = new Car();
car.drives();
     }
 }
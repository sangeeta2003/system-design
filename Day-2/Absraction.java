interface Car{
    void startEngine();
    void shiftGear(int gear);
    void accelerate();
   void brake(); 
    void stopEngine();

}

class SportsCar implements Car{
    String brand;
    String model;
    boolean isEngineon = false;
    int currentSpeed = 0;
    int currentGear = 0;

    public SportsCar(String brand, String model){
        this.brand = brand;
        this.model = model;
    }

    @Override
    public void startEngine(){
        isEngineon = true;
         System.out.println(brand + " " + model + " : Engine starts with a roar!");
    }
    @Override
    public void shiftGear(int gear){
        if(!isEngineon){
             System.out.println(brand + " " + model + " : Engine is off! Cannot Cannot Shift Gear.");
             return;
        }
        this.currentGear = gear;
        System.out.println(brand + " " + model + " : Shifted to gear " + currentGear);
    }
     @Override
    public void accelerate() {
        if (!isEngineon) {
            System.out.println(brand + " " + model + " : Engine is off! Cannot accelerate.");
            return;
        }
        currentSpeed += 20;
        System.out.println(brand + " " + model + " : Accelerating to " + currentSpeed + " km/h");
    }
    
   @Override
public void brake() {
    currentSpeed -= 20;
    if(currentSpeed < 0)
        currentSpeed = 0;

    System.out.println(brand + " " + model +
            " : Braking! Speed is now " + currentSpeed + " km/h");
}
    @Override
    public void stopEngine() {
        isEngineon = false;
        currentGear = 0;
        currentSpeed = 0;
        System.out.println(brand + " " + model + " : Engine turned off.");
    }

}

public class Absraction{
    public static void main(String[] args) {
        Car myCar = new SportsCar("Ford","Mustang");
        myCar.accelerate();
        myCar.startEngine();
        myCar.shiftGear(1);
        myCar.stopEngine();
        myCar.brake();
    }

}
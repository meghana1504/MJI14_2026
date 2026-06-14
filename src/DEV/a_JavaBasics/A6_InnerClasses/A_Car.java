package DEV.a_JavaBasics.A6_InnerClasses;

public class A_Car {
    private String model;
    private boolean isEngineOn;
    A_Car(String model){
        this.model = model;
        this.isEngineOn = false;
    }

    class Engine{
        void start(){
            if(isEngineOn)
                System.out.println("Engine for " + model + " is already on");
            else{
                System.out.println("Engine for "+model+" turned on");
                isEngineOn = true;
            }
        }
        void stop(){
            if(isEngineOn) {
                System.out.println("Engine for " + model + " turned off");
                isEngineOn = false;
            }
            else
                System.out.println("Engine for "+model+" is already off");
        }
    }
}

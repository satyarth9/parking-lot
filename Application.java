public class Application {

    private CapacityManager capacityManager;
    public static void main(String[] args) {
         Application app = new Application();
         app.startCapacityManager();
    }

    public void startCapacityManager() {
        this.capacityManager = new CapacityManager();
    }
    
    public void loadCapacityModel(CapacityModel cm) {
        this.capacityManager.setCapacityModel(cm);
    }
}


/*
 * 
 * 
 * Vehicle type : nWheels = 2, 4, 8
 */
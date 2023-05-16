public class Application {

    private CapacityManager capacityManager;
    public static void main(String[] args) {
         Application app = new Application();
         app.startCapacityManager();
         CapacityModel cm = new CapacityModel(10, 4, 2);
         app.iniitalizeCapactiyManager(cm, new StadiumFeeModel());
         try {
            ParkingTicket pt1 = app.capacityManager.park(VehicleType.MOTORCYCLE);
            ParkingReceipt pr1 = app.capacityManager.remove(pt1.getTicketNumber(), 13*3600000l);
            System.out.println(pt1);
            System.out.println(pr1);
         } catch (RuntimeException re){
             re.printStackTrace();
         }
    }

    public void startCapacityManager() {
        this.capacityManager = new CapacityManager();
    }
    
    public void iniitalizeCapactiyManager(CapacityModel cm, FeeModel fm) {
        this.capacityManager.initialize(cm, fm);
    }
}

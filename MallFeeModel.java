public class MallFeeModel implements FeeModel{

    @Override
    public int getFee(VehicleType vehicleType, int duration) {
        if (vehicleType.getN_Wheels()==2) {
            return duration * 10;
        } else if (vehicleType.getN_Wheels()==4) {
            return duration * 20;
        } else if (vehicleType.getN_Wheels()==8) {
            return duration * 50;
        } else {
            throw new RuntimeException("unsupported vehicle type " + vehicleType.toString());
        }
    }
    
}

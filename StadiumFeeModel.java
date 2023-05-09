public class StadiumFeeModel implements FeeModel{

    @Override
    public int getFee(VehicleType vehicleType, int duration) {
        if (vehicleType.getN_Wheels() == 2) {
            if (duration < 4)
                return 30;
            else if (duration < 12)
                return 90;
            else
                return 90 + (duration-12) * 100;
        } else if (vehicleType.getN_Wheels() == 4) {
            if (duration < 4)
                return 60;
            else if (duration < 12)
                return 180;
            else
                return 180 + (duration-12) * 200;
        } else {
            throw new RuntimeException("Unsupported Vehicle Type : " + vehicleType.toString());
        }
    }
    
}

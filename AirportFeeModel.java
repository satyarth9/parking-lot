public class AirportFeeModel implements FeeModel {

    @Override
    public int getFee(VehicleType vehicleType, int duration) {
        if (vehicleType.getN_Wheels() == 2) {
            if (duration < 1)
                return 0;
            else if (duration < 8)
                return 40;
            else if (duration < 24)
                return 60;
            else
                return (duration / 24) * 80;
        } else if (vehicleType.getN_Wheels() == 4) {
            if (duration < 12)
                return 60;
            else if (duration < 24)
                return 80;
            else
                return (duration / 24) * 100;
        } else {
            throw new RuntimeException("Unsupported Vehicle Type : " + vehicleType.toString());
        }
    }
    
}
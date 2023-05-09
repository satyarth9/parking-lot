public enum VehicleType{
    SCOOTER(2),
    MOTORCYCLE(2),
    CAR(4),
    SUV(4),
    BUS(8),
    TRUCK(8);

    private final int nWheels;
    public int getN_Wheels(){
        return nWheels;
    }
    private VehicleType(int nWheels){
        this.nWheels = nWheels;
    }
}

// TODO : store n Wheels at the Enum level 
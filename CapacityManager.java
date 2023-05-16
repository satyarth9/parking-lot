import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CapacityManager {
    
    private CapacityModel capacityModel;
    private FeeModel feeModel;

    private static int n2Filled = 0;
    private static int n2Next = 0;
    private static int n4Filled = 0;
    private static int n4Next = 0;
    private static int n8Filled = 0;
    private static int n8Next = 0;
    private static int nReceipts = 0;

    private static List<String> n2SpotsFilled = new ArrayList<>();
    private static List<String> n4SpotsFilled = new ArrayList<>();
    private static List<String> n8SpotsFilled = new ArrayList<>();
    private static Map<String, ParkingTicket> ticketsDB = new LinkedHashMap<>();
    

    public void initialize(CapacityModel capacityModel, FeeModel feeModel){
        this.capacityModel = capacityModel;
        this.feeModel = feeModel;
    }

    public int calculateFare(FeeModel fm, VehicleType vt, int duration){
        return fm.getFee(vt, duration);
    }

    public ParkingTicket park(VehicleType vehicleType){
        int nWheels = vehicleType.getN_Wheels();
        String spotNumber = findFirstUnfilled(vehicleType);
        ParkingTicket pt = null;
        if (spotNumber.isEmpty()) {
            throw new RuntimeException(Constants.NO_ROOM_ERROR);
        }
        if (nWheels ==2 && n2Filled < this.capacityModel.getN2()){
            n2Filled++;
            n2Next++;
            String ticketNumer = "A-"+n2Next;
            pt = new  ParkingTicket(ticketNumer, spotNumber, System.currentTimeMillis());
            saveTicket(pt);
            return pt;
        } else if (nWheels == 4 && n4Filled < this.capacityModel.getN4()){
            n4Filled++;
            n4Next++;
            String ticketNumber = "B-"+n4Next;
            pt = new ParkingTicket(ticketNumber, spotNumber, System.currentTimeMillis());
            saveTicket(pt);
            return pt;
        } else if (nWheels == 8 && n8Filled < this.capacityModel.getN8()){
            n8Filled++;
            n8Next++;
            String ticketNumber = "C-"+n8Next;
            pt = new ParkingTicket(ticketNumber, spotNumber, System.currentTimeMillis());
            saveTicket(pt);
            return pt;
        }
        return null;
    }

    public ParkingReceipt remove(String tikcetNumber, long offset){
        if (!ticketsDB.containsKey(tikcetNumber))
            throw new RuntimeException(Constants.INVALID_PARKING_TICKET);
        ParkingTicket parkingTicket = ticketsDB.get(tikcetNumber);
        clearSpot(parkingTicket);
        VehicleType vt = vehicleTypeFromParkingTicket(parkingTicket);
        Long endtime = System.currentTimeMillis() + offset;
        int duration = (int) (endtime - parkingTicket.getEntryDateTime()) / (3600000); // in hours
        int fee = calculateFare(feeModel, vt, duration);
        nReceipts++;
        String receiptNumber = "R-"+nReceipts;
        return new ParkingReceipt(receiptNumber, tikcetNumber, parkingTicket.getEntryDateTime(), 
                endtime, fee);
    }

    private void clearSpot(ParkingTicket pt){
        if (pt.getTicketNumber().startsWith("A"))
            n2Filled--;
        else if (pt.getTicketNumber().startsWith("B"))
            n4Filled--;
        else if (pt.getTicketNumber().startsWith("C"))
            n8Filled--;
        ticketsDB.remove(pt.getTicketNumber());
    }

    private VehicleType vehicleTypeFromParkingTicket(ParkingTicket pt){
        if (pt.getTicketNumber().startsWith("A"))
            return VehicleType.MOTORCYCLE; // arbitrary choice, could also have been SCOOTER
        else if (pt.getTicketNumber().startsWith("B"))
            return VehicleType.CAR; // similar choice exists
        else if (pt.getTicketNumber().startsWith("C"))
            return VehicleType.BUS; // similar choice exists
        return null;
    }

    private void saveTicket(ParkingTicket pt){
        ticketsDB.put(pt.getTicketNumber(), pt);
    }

    private String findFirstUnfilled(VehicleType vehicleType){
        String freeSpot = "";
        List<String> searchList = n2SpotsFilled;
        String prefix = "A";
        int nSpots = this.capacityModel.getN2();
        if (vehicleType.getN_Wheels() == 4) {
            searchList = n4SpotsFilled;
            prefix = "B";
            nSpots = this.capacityModel.getN4();
        }
        else if (vehicleType.getN_Wheels() == 8) {
            searchList = n8SpotsFilled;
            prefix = "C";
            nSpots = this.capacityModel.getN8();
        }
        for (int i =0; i < nSpots; i++){
            if (!searchList.contains(prefix + (i+1))){
                searchList.add(prefix + (i+1));
                return prefix + (i+1);
            }
        }
        return freeSpot;
    }


}

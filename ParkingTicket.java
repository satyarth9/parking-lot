public class ParkingTicket {
    private String ticketNumber;
    private String spotNumber;
    private long entryDateTime;

    public ParkingTicket(String ticketNumber, String spotNumber, long entryDateTime){
        this.ticketNumber = ticketNumber;
        this.spotNumber = spotNumber;
        this.entryDateTime = entryDateTime;
    }

    public String getTicketNumber(){
        return this.ticketNumber;
    }

    public long getEntryDateTime(){
        return this.entryDateTime;
    }

    @Override
    public String toString(){
        return "Parking Ticket:\n\tticket number : " + this.ticketNumber + "\n\tspot number : " + this.spotNumber + "\n\tentry time : " + Util.longToDate(this.entryDateTime);
    }

}

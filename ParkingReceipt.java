public class ParkingReceipt {
    private String receiptNumber;
    private String ticketNumber;
    private long entryDateTime;
    private long exitDateTime;
    private int fee;

    public ParkingReceipt(String receiptNumber, String ticketNumber, long entryDateTime, long exitDateTime, int fee){
        this.receiptNumber = receiptNumber;
        this.ticketNumber = ticketNumber;
        this.entryDateTime = entryDateTime;
        this.exitDateTime = exitDateTime;
        this.fee = fee;
    }

    public String toString(){
        return "parking receipit:\n\treceipt number : " + this.receiptNumber + "\n\tticket number : " + this.ticketNumber + "\n\tentry time : " + Util.longToDate(entryDateTime) + "\n\texit time : " + Util.longToDate(exitDateTime) + "\n\tfee : " + this.fee;
    }
}

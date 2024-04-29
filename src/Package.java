import java.time.LocalDate;
import java.util.Collections;
public class Package implements Comparable <Package>,Cloneable{
    String trackingNumber;
    Address senderAddress,recipientAddress;
    LocalDate estimatedDeliveryDate= LocalDate.of(2023,5,12);

    public Package(String trackingNumber, Address senderAddress, Address recipientAddress, LocalDate estimatedDeliveryDate) {
        this.trackingNumber = trackingNumber;
        this.senderAddress = senderAddress;
        this.recipientAddress = recipientAddress;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Address getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(Address senderAddress) {
        this.senderAddress = senderAddress;
    }

    public Address getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(Address recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public LocalDate getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }
    public int compareTo(Package o) {
        int comparedResult=trackingNumber.compareTo(o.trackingNumber);
        if(comparedResult>0){
            return 1;
        } else if (comparedResult<0) {
            return -1;
        } else {
            return 0;
        }
    }
    public Package deepClone() throws CloneNotSupportedException{
        Package clone=(Package) super.clone();
        return clone;
    }

    @Override
    public String toString() {
        return "Package{" +
                "trackingNumber='" + trackingNumber + '\'' +
                ", senderAddress=" + senderAddress +
                ", recipientAddress=" + recipientAddress +
                ", estimatedDeliveryDate=" + estimatedDeliveryDate +
                '}';
    }
}

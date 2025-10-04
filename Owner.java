public class Owner {
    private String name;
    private int vehicleNumber;
    private int ContactNumber;
    public Owner(String name,int vehicleNumber,int contactNumber){
        this.vehicleNumber=vehicleNumber;
        this.name=name;
        this.ContactNumber=ContactNumber;

    }
    public void setName(String Name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

    public int getVehicleNumber() {
        return vehicleNumber;
    }

    public int getContactNumber() {
        return ContactNumber;
    }

    public void setVehicleNumber(int vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setContactNumber(int contactNumber) {
        ContactNumber = contactNumber;
    }

}

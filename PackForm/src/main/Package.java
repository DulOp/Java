
package main;

public class Package {
    
    private int packageId;
    private String speed;
    private String flow;
    private String contract_length;
    private String first_name;
    private String last_name;
    private String address;
    
    public Package() {
        
    }
    
    public Package(int packageId) {
        this.packageId = packageId;
    }
    
    public Package(int packageId, String speed, String flow, String contract_length, String first_name, String last_name, String address) {
        this.packageId = packageId;
        this.speed = speed;
        this.flow = flow;
        this.contract_length = contract_length;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
    }
    
    public int getPackageId() {
        return packageId;
    }
    
    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }
    
    public String getSpeed() {
        return speed;
    }
    
    public void setSpeed(String speed) {
        this.speed = speed;
    }
    
    public String getFlow() {
        return flow;
    }
    
    public void setFlow(String flow) {
        this.flow = flow;
    }
    
    public String getContractLength() {
        return contract_length;
    }
    
    public void setContractLength(String contract_length) {
        this.contract_length = contract_length;
    }
    
    public String getFirstName() {
        return first_name;
    }
    
    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }
    
    public String getLastName() {
        return last_name;
    }
    
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    
}

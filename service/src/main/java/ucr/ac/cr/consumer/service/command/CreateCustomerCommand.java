package ucr.ac.cr.consumer.service.command;

import jakarta.validation.constraints.NotBlank;
import ucr.ac.cr.consumer.service.model.Address;

public class CreateCustomerCommand {
    @NotBlank
    private String name;
    private String flooNetworkId;
    private Address address;

    // Constructor, getters y setters
    public CreateCustomerCommand() {}

    public CreateCustomerCommand(String name, String flooNetworkId, Address address) {
        this.name = name;
        this.flooNetworkId = flooNetworkId;
        this.address = address;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getFlooNetworkId() { return flooNetworkId; }
    public void setFlooNetworkId(String flooNetworkId) { this.flooNetworkId = flooNetworkId; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
}
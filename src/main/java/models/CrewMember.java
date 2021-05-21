package models;

public class CrewMember extends BaseModel{
    private String name;
    private String charge;

    public CrewMember(String name, String charge){
        this.name = name;
        this.charge = charge;
    }

    public CrewMember(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                "name='" + name + '\'' +
                ", charge='" + charge + '\'' +
                '}';
    }
}

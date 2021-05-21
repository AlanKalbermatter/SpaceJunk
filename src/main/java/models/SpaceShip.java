package models;

public class SpaceShip extends BaseModel{
    private String name;
    private String mission;
    private CrewMember crewMember;

    public SpaceShip(){}

    public SpaceShip(String name, String mission, CrewMember crewMember){
        this.name = name;
        this.mission = mission;
        this.crewMember = crewMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public CrewMember getCrewMember() {
        return crewMember;
    }

    public void setCrewMember(CrewMember crewMember) {
        this.crewMember = crewMember;
    }

    @Override
    public String toString() {
        return "SpaceShip{" +
                "name='" + name + '\'' +
                ", mission='" + mission + '\'' +
                ", crewMember=" + crewMember +
                '}';
    }
}

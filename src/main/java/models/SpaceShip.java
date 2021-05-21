package models;

import java.util.List;

public class SpaceShip extends BaseModel{
    private String name;
    private String mission;
    private CrewMember crewMember;
    private List<CrewMember> crewMembers;

    public SpaceShip(){}

    public SpaceShip(String name, String mission, CrewMember crewMember, List<CrewMember> crewMembers){
        this.name = name;
        this.mission = mission;
        this.crewMember = crewMember;
        this.crewMembers = crewMembers;
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
                ", crewMember=" + crewMember +'\'' +
                ", crewMemembers="+crewMembers +
                '}';
    }

    public List<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    public void setCrewMembers(List<CrewMember> crewMembers) {
        this.crewMembers = crewMembers;
    }
}

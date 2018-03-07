package ute.webservice.voiceagent;

/**
 * Created by nith on 10/19/17.
 */

public class RoomStatus{

    private String unit;
    private int availableBeds;

    RoomStatus(String _name, int _availableBeds) {
        this.unit = _name;
        this.availableBeds = _availableBeds;
    }


    @Override
    public String toString() {
        return "Unit " + this.unit + " has " + this.availableBeds + " available beds ";
    }

    public String getUnit(){return this.unit;}

    public int getAvailableBeds(){return this.availableBeds;}


}
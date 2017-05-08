package org.lebedko.device.monitor.dto;

import java.io.Serializable;

public class Accident implements Serializable {

    public Accident() {
    }

    private Long id;

    private String deviceName;

    private String receiveTime;

    private String accidentType;

    private String accident;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getDeviceName() { return deviceName; }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getReceiveTime() { return receiveTime; }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(String accidentType) {
        this.accidentType = accidentType;
    }

    public String getAccident() {
        return accident;
    }

    public void setAccident(String accident) {
        this.accident = accident;
    }
}

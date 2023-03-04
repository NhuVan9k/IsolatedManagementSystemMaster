/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author CHUNG
 */

public class Area {
    private int areaId;
    private String areaName;
    private String areaAddress;
    private String contact;

    public Area() {
    }

    public Area(int areaId, String areaName, String areaAddress, String contact) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.areaAddress = areaAddress;
        this.contact = contact;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaAddress() {
        return areaAddress;
    }

    public void setAreaAddress(String areaAddress) {
        this.areaAddress = areaAddress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "KhuCachLy{" + "areaId=" + areaId + ", areaName=" + areaName + ", areaAddress=" + areaAddress + ", contact=" + contact + '}';
    } 
}

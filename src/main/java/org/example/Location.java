package org.example;

public class Location
{
    private String restroId;
    private int latx;
    private int lony;

    public int getLatx() {
        return latx;
    }

    public String getRestroId() {
        return restroId;
    }

    public int getLony() {
        return lony;
    }

    public void setRestroId(String restroId) {
        this.restroId = restroId;
    }

    public void setLatx(int latx) {
        this.latx = latx;
    }

    public void setLony(int lony) {
        this.lony = lony;
    }

    @Override
    public String toString() {
        return "Location{" +
                "restroId='" + restroId + '\'' +
                ", latx=" + latx +
                ", lony=" + lony +
                '}';
    }
}

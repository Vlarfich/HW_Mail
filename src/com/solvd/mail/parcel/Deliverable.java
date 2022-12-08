package com.solvd.mail.parcel;

public interface Deliverable {
    public boolean isDelivered();

    public void setDelivered(boolean delivered1);

    public String getDeparture();

    public String getDestination();

    public int getID();
}

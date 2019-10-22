package main.java.entities;

import java.util.Date;

public class Circumstance extends GenericEntity<String, Circumstance> {
  private int roomNumber;
  private Date time;
  
  {
    
  }

  public Circumstance() {}
  
  public int getRoomNumber() {
    return roomNumber;
  }

  public void setRoomNumber(int roomNumber) {
    this.roomNumber = roomNumber;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }
}

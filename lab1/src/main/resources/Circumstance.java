package main.resources;

import java.util.Date;

public class Circumstance implements Identifiable {
  {
    
  }
  
  public Circumstance() {}

  public String getId() {
    return name;
  }

  public void setId(String name) {
    this.name = name;
  }

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

  private String name;
  private int roomNumber;
  private Date time;
}

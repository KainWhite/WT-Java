package main.java.entities;

import main.java.interfaces.Identifiable;

import java.util.Date;

public class Circumstance {
  private int id;
  private int roomNumber;
  private Date time;
  
  {
    
  }

  public Circumstance() {}

  public int getId() {
    return id;
  }

  public void setId(int name) {
    this.id = name;
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
}

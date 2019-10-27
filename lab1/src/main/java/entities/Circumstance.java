package main.java.entities;

import java.util.Date;

public class Circumstance extends GenericEntity<String, Circumstance> {
  private int roomNumber;
  private Date time;
  
  {
    roomNumber = -1;
    time = null;
  }

  public Circumstance() {}

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("Class: Circumstance\n");
    str.append(super.toString());
    if(roomNumber != -1) {
      str.append(" roomNumber: ").append(roomNumber).append("\n");
    }
    if(time != null) {
      str.append(" time: ").append(time).append("\n");
    }
    return str.toString();
  }
  public String toString(int identSpacesCount) {
    StringBuilder str = new StringBuilder();
    String spaces = getSpaceString(identSpacesCount);
    str.append(spaces).append("Class: Circumstance\n");
    str.append(super.toString(identSpacesCount));
    if(roomNumber != -1) {
      str.append(spaces)
          .append(" roomNumber: ").append(roomNumber).append("\n");
    }
    if(time != null) {
      str.append(spaces)
          .append(" time: ").append(time).append("\n");
    }
    return str.toString();
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

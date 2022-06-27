package model;

public class Room implements IRoom{
    String roomNumber;
    Double price;
    RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType type) {
        this.roomNumber = roomNumber;
        this.price = price;
        enumeration = type;
    }

    public Room(String roomNumber, RoomType type) {
        this.roomNumber = roomNumber;
        enumeration = type;
    }

    @Override
    public String toString() {
        return "Room" + roomNumber + "is a " + enumeration.toString()
                + "room" + ", with price " + price.toString();
    }

    /**
     * @return
     */
    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * @return
     */
    @Override
    public Double getRoomPrice() {
        return price;
    }

    /**
     * @return
     */
    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    /**
     * @return
     */
    @Override
    public boolean isFree() {
        return price == 0.0 ? true : false;
    }
}

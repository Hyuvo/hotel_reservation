package model;

public class FreeRoom extends Room{
    public FreeRoom(String roomNumber, RoomType type) {
        super(roomNumber, type);
        this.price = 0.0;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

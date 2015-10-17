package booking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private int floor;
    private String name;   

    @ManyToOne
    private RoomType type;
    
    @ManyToOne
    private Hotel hotel;

    public Room() {}
    
    public Room(long id, int floor, String name, RoomType type, Hotel hotel) {
    	this.id = id;
    	this.floor = floor;
    	this.name = name;

    	this.hotel = hotel;
    	hotel.getRooms().add(this);
    	
    	this.type = type; 
    	type.getRooms().add(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
    	return "Id: " + getId() + " Name: " + getName() ;
    }

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}
}


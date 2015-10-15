package booking.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;    
    
    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="hotel", orphanRemoval = true)
    private Collection<Room> rooms = new ArrayList<Room>();
    
    public Hotel() {}
    
    public Hotel(String name) {
    	this.name = name;
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
    	return "Id: " + getId() + ", Name: " + getName();
    }

	public Collection<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Collection<Room> rooms) {
		this.rooms = rooms;
	}

}

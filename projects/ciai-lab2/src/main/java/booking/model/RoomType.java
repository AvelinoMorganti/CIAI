package booking.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class RoomType {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String description;   

    @OneToMany(cascade=CascadeType.PERSIST, mappedBy="type")
    private List<Room> rooms = new ArrayList<Room>();
 
    public RoomType() {}
    
    public RoomType(String description) {
    	this.description = description;
    }
    
    public RoomType(long id, String description) {
    	this.id = id;
    	this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
    	return "Id: " + getId() + "\nName: " + getDescription();
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

}


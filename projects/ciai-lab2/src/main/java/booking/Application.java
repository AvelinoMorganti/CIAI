package booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import booking.model.Hotel;
import booking.model.Room;
import booking.model.RoomType;
import booking.repository.UserRepository;
import booking.repository.HotelRepository;
import booking.repository.RoomTypeRepository;
import booking.repository.RoomRepository;


@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	/**
	 * The main() method uses Spring Boot’s SpringApplication.run() method to launch an application.
	 * The run() method returns an ApplicationContext where all the beans that were created 
	 * either by your app or automatically added thanks to Spring Boot are.
	 * @param args
	 */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Autowired
    UserRepository users;
    HotelRepository hotels;
    //RoomRepository rooms;

    @Autowired
    RoomTypeRepository roomtypes;

    @Override
    @Transactional
    public void run(String... strings) {
    	    
    	log.info("Setting up seed data");
    	
    	hotels.deleteAll();
    	Hotel myHotels[] = {new Hotel("Marriot"), 
    						new Hotel("Intercontinental"), 
    						new Hotel("Trip"), 
    						new Hotel("Holiday Inn"), 
    						new Hotel("Tulip"), 
    						new Hotel("Hostel da Costa")};
    	
    	for(Hotel hotel : myHotels) hotels.save(hotel);
    	
    	//RoomType[] types = {new RoomType(1,"Single"), new RoomType(2,"Double")};
    	
    	//for(RoomType type:types) roomtypes.save(type);
    	
    	Hotel marriot = myHotels[0];
		/*Room[] roomArray = {new Room(1,12,"121",types[0],marriot), 
							new Room(2,12,"122",types[1],marriot), 
							new Room(3,13,"131",types[0],marriot), 
							new Room(4,13,"132",types[1],marriot)};		
		
		for(Room room: roomArray){ rooms.save(roomArray);}*/
		
		hotels.save(marriot);
    }
}



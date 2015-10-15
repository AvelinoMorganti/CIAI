
package booking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import booking.model.Room;
import booking.repository.RoomRepository;
import booking.util.RoomNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

/*
 * Mapping
 * GET  /hotels 			- the list of hotels
 * GET  /hotels/new			- the form to fill the data for a new hotel 
 * POST /hotels         	- creates a new hotel
 * GET  /hotels/{id} 		- the hotel with identifier {id}
 * GET  /hotels/{id}/edit 	- the form to edit the hotel with identifier {id}
 * POST /hotels/{id} 	 	- update the hotel with identifier {id}
 */

@Controller
@RequestMapping(value="/hotels/rooms")
public class RoomController {

    @Autowired
    RoomRepository rooms;

	// GET  /rooms 			- the list of rooms
    @RequestMapping(method=RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("rooms", rooms.findAll());
        return "rooms/index";
    }

	// GET  /hotels.json 			- the list of hotels
    @RequestMapping(method=RequestMethod.GET, produces={"text/plain","application/json"})
    public @ResponseBody Iterable<Room> indexJSON(Model model) {
        return rooms.findAll();
    }

    // GET  /hotels/new			- the form to fill the data for a new hotel
    @RequestMapping(value="/new", method=RequestMethod.GET)
    public String newHotel(Model model) {
    	model.addAttribute("room", new Room());
    	return "rooms/create";
    }
    
    // POST /hotels         	- creates a new hotel
    @RequestMapping(method=RequestMethod.POST)
    public String saveIt(@ModelAttribute Room room, Model model) {
    	rooms.save(room);
    	model.addAttribute("room", room);
    	return "redirect:/rooms";
    }
    
    // GET  /hotels/{id} 		- the hotel with identifier {id}
    @RequestMapping(value="{id}", method=RequestMethod.GET) 
    public String show(@PathVariable("id") long id, Model model) {
    	Room room = rooms.findOne(id);
    	if( room == null )
    		throw new RoomNotFoundException();
    	model.addAttribute("room", room );
    	model.addAttribute("name", room.getName());
    	model.addAttribute("floor", room.getFloor());
    	return "rooms/show";
    }
    
    // GET  /hotels/{id}.json 		- the hotel with identifier {id}
    @RequestMapping(value="{id}", method=RequestMethod.GET, produces={"text/plain","application/json"})
    public @ResponseBody Room showJSON(@PathVariable("id") long id, Model model) {
    	Room room = rooms.findOne(id);
    	if( room == null )
    		throw new RoomNotFoundException();
    	return room;
    }
    
    // GET  /hotels/{id}/edit 	- the form to edit the hotel with identifier {id}
    @RequestMapping(value="{id}/edit", method=RequestMethod.GET)
    public String edit(@PathVariable("id") long id, Model model) {
    	model.addAttribute("room", rooms.findOne(id));
    	return "rooms/edit";
    }
   
    // POST /hotels/{id} 	 	- update the hotel with identifier {id}
    @RequestMapping(value="{id}", method=RequestMethod.POST)
    public String editSave(@PathVariable("id") long id, Room room, Model model) {
    	rooms.save(room);
    	return "redirect:/";
    }
}








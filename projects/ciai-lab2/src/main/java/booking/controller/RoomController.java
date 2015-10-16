
package booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import booking.model.Room;
import booking.repository.HotelRepository;
import booking.repository.RoomRepository;

@Controller
@RequestMapping(value="/rooms")
public class RoomController {

	@Autowired
	RoomRepository rooms;

	// GET  /hotels/rooms 			- the list of rooms
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("rooms", rooms.findAll());
		return "rooms/index";
	}
}








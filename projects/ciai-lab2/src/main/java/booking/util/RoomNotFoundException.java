package booking.util;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Room")  // 404
public class RoomNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
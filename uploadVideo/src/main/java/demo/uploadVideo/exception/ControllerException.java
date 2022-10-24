package demo.uploadVideo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ControllerException  extends RuntimeException {
	
	private String errorCode ;
	private String errorMessage ;
	
	

}

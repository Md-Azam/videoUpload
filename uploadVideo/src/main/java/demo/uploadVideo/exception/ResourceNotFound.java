package demo.uploadVideo.exception;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@NoArgsConstructor
@Setter @Getter
@AllArgsConstructor
public class ResourceNotFound  extends RuntimeException  {
	
	
	private String errorCode ;
	private String errorMessage ;
	
	
	

	

}

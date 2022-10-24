package demo.uploadVideo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="video")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Videos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ;
	private String title ;
	private String description ;
	private String tags ;
	private String videoName ;
	private Date addedDate ;

	
}

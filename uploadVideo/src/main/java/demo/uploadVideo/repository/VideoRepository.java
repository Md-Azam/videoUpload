package demo.uploadVideo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.uploadVideo.entity.Videos;

@Repository
public interface VideoRepository extends JpaRepository<Videos, Integer> {
	
	
	
	
	

	

}

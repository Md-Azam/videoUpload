package demo.uploadVideo.service;

import java.util.List;

import demo.uploadVideo.configuration.UpdateModel;
import demo.uploadVideo.entity.Videos;

public interface VideoInterface {
	
	public Videos createPost(Videos videos);
	
	public Videos getVideosById(Integer id);
	
	public List<Videos> getAllVideos();
	
	public Videos updatePost(Videos videos , Integer id);
	
	public void deleteVideos(Integer id);
	
public UpdateModel updateModel(UpdateModel updateModel, int id);
}

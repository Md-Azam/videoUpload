package demo.uploadVideo.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import demo.uploadVideo.configuration.FileModel;
import demo.uploadVideo.configuration.UpdateModel;
import demo.uploadVideo.entity.Videos;
import demo.uploadVideo.exception.ControllerException;
import demo.uploadVideo.exception.ResourceNotFound;
import demo.uploadVideo.repository.VideoRepository;
import demo.uploadVideo.service.FileInterface;
import demo.uploadVideo.service.VideoInterface;

@RequestMapping("/api")
@RestController
@CrossOrigin("http://localhost:3000")
public class VideoController {

	@Value("${project.video}")
	private String path;

	@Autowired
	private VideoInterface service;

	@Autowired
	private FileInterface fileSevice;

	@Autowired
	private VideoRepository videoRepository;

	@PostMapping("/save")
	public ResponseEntity<?> saveVideo(@RequestBody Videos video) {
		try {
			Videos saveVideos = service.createPost(video);
			return new ResponseEntity<Videos>(saveVideos, HttpStatus.OK);
		} catch (ResourceNotFound e) {
			ControllerException controllerException = new ControllerException(e.getErrorCode(),
					e.getErrorMessage() + e.getMessage());
			return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
		}
	}

	//1. get Video by id ;
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getDataById(@PathVariable Integer id) {
		try {
			Videos video = service.getVideosById(id);
			return new ResponseEntity<Videos>(video, HttpStatus.CREATED);

		} catch (ResourceNotFound e) {
			ControllerException controllerException = new ControllerException(e.getErrorCode(),
					e.getErrorMessage() + e.getMessage());
			return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException controllerException = new ControllerException("504", "id not found");
			return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
		}
	}

	//2. Get List Of videos .
	@SuppressWarnings("unused")
	@GetMapping("/all")
	public ResponseEntity<?> getListofData() {
		Videos videos = new Videos();
		try {
			if (videos == null) {
				throw new ResourceNotFound("404", "data is null");
			}
			List<Videos> v = service.getAllVideos();
			return new ResponseEntity<List<Videos>>(v, HttpStatus.CREATED);
		} catch (Exception e) {
			ControllerException controllerException = new ControllerException("404", "Empty database is found");
			return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
		}
	}

	//3. Posting Video api .
	@PostMapping("/upload/{id}")
	public ResponseEntity<Videos> uploadVideo(@RequestParam("video") MultipartFile video, @PathVariable Integer id)
			throws IOException {
		Videos v = this.service.getVideosById(id);
		FileModel fileModel = this.fileSevice.uploadVideo(path, video);
		v.setVideoName(fileModel.getVideoFileName());
		Videos uploadVideo = this.service.updatePost(v, id);
		return new ResponseEntity<Videos>(uploadVideo, HttpStatus.OK);
	}

	//4.To play video .
	@GetMapping(value = "/play/{id}", produces = MediaType.ALL_VALUE)
	public void downloadImage(@PathVariable int id, HttpServletResponse response) throws IOException {
		Optional<Videos> video = videoRepository.findById(id);
		InputStream resource = this.fileSevice.getResource(path, video.get().getVideoName(), id);
		response.setContentType(MediaType.ALL_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

	//5. Delete videos by id .
	@DeleteMapping("/{id}")
	public String deleteVideo(@PathVariable Integer id) throws IOException {
		Optional<Videos> video = videoRepository.findById(id);
		Path exactPath = Paths.get(path + File.separator + video.get().getVideoName());
		System.out.println(exactPath);
		try {
			Files.deleteIfExists(exactPath);

		} catch (Exception e1) {
			e1.getMessage();
			System.out.println(e1.getMessage()+"can not delete now ");
		}
		this.service.deleteVideos(id);

		return "video deleted successfully";
	}
	
	//Update DataModel of Video .
	@PutMapping("/update/{id}")
	public ResponseEntity<UpdateModel> setVideoData( @RequestBody UpdateModel updateModel,@PathVariable int id){
		try {
		service.updateModel(updateModel, id);
		return new ResponseEntity<UpdateModel>(updateModel, HttpStatus.OK);	
		}catch(Exception e) {
			throw new ResourceNotFound("404","user id not found");
		}
	}
}

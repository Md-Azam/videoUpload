package demo.uploadVideo.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystemNotFoundException;

import org.springframework.web.multipart.MultipartFile;

import demo.uploadVideo.configuration.FileModel;

public interface FileInterface {

	FileModel uploadVideo(String path, MultipartFile file) throws IOException ;
	
	InputStream getResource(String path,String fileName , int id) throws FileNotFoundException ;

}

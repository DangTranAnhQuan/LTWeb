package com.example.demo.services;

import java.nio.file.Path;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
	void init();

	void delete(String storeFilename) throws Exception;

	Path load(String filename);

	void store(MultipartFile file, String storeFilename);

	String getSorageFilename(MultipartFile file, String id);

	Resource loadAsResource(String filename);
}

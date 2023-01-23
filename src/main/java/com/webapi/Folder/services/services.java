package com.webapi.Folder.services;

import java.io.IOException;
//import java.util.List;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.webapi.Folder.exception.ResponseNotFound;
import com.webapi.Folder.model.files;
import com.webapi.Folder.model.folder1;
import com.webapi.Folder.repository.files_repo;
import com.webapi.Folder.repository.folder_repo;

@Service
public class services {
	
	@Autowired
	private folder_repo folder_repo;
	@Autowired
	private files_repo files_repo;
	
	
	public files store_file(MultipartFile file,Long folder_id) {
		String file_name=StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if(file_name.contains("..")) {
				throw new ResponseNotFound("invalid");
			}
			files file1=new files(file_name,file.getContentType(),file.getBytes(),folder_id);
			return  files_repo.save(file1);
		}
		catch(IOException e) {
			throw new ResponseNotFound("invalid");
		}
		
	}
	
//	public folder1 getfile(Long fileid) {
//		return folder_repo.findById(fileid).orElseThrow(null);//()-> new ResponseNotFound("invlaid"));
//	}
	public List<folder1> getall() {	
		return folder_repo.findAll();
	}

	public folder1 getbyid(Long folder_id,String folder_name) {
		folder1 file =folder_repo.findById(folder_id)
				.orElseThrow(null);//() -> new ResponseNotFound("no such id exist: "+folder_id));
		 if(file.getFolder_name().equals(folder_name)) {
			   return file;
		   }
		   else {
			   return null;
		   }  
	
	}

	public folder1 update_folders(Long folder_id, String folder_name) {
		folder1 fold =folder_repo.findById(folder_id)
				.orElseThrow(null);//() -> new ResponseNotFound("no such id exist: "+folder_id));
		fold.setFolder_name(folder_name);
		return folder_repo.save(fold);
	}

	

	

}

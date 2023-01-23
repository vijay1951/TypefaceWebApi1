package com.webapi.Folder.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webapi.Folder.exception.ResponseNotFound;
import com.webapi.Folder.model.files;
import com.webapi.Folder.model.folder1;
import com.webapi.Folder.payload.Response;
import com.webapi.Folder.repository.files_repo;
//import com.webapi.Folder.services.services;
//Read files by type
//@GetMapping("files/{file_type}") 
//
//public folder1 getFileByType(@PathVariable(name="file_type") String file_type) {
//	return service.getAllFileByType(file_type);
//} 
import  com.webapi.Folder.repository.folder_repo;
import com.webapi.Folder.services.services;

@RequestMapping("/webapi")
@RestController
public class folder_controller {
    
	public folder_controller() {
		
	}
	@Autowired
	private folder_repo folder_repo;
	@Autowired
	private files_repo files_repo;
	@Autowired
	private services folder_service;
	
	//creating an folder
	@PostMapping("/folder/{folder_name}/{folder_id}")
	public ResponseEntity<folder1> create_folder(@PathVariable String folder_name,@PathVariable Long folder_id){
			folder1 fold = new folder1(folder_id,folder_name);
			folder1 fold2=folder_repo.save(fold);
			return ResponseEntity.ok(fold2);
	}
		
	//update folder name by id 
	@PutMapping("/folder/{folder_name}/{folder_id}")
	public ResponseEntity<String> update_folders(@PathVariable String folder_name, @PathVariable Long folder_id){
		folder_service.update_folders(folder_id,folder_name);
		return ResponseEntity.ok("folder updated");
	}	
	//Read all folders
	@GetMapping("/folders")
	public List<folder1> getAllFolders(){
		   return folder_service.getall();
	} 
	//Read folders by id
	@GetMapping("folders1/{folder_name}/{folder_id}") 
	public folder1 getFolderById(@PathVariable long folder_id,@PathVariable String folder_name) {
		   return folder_service.getbyid(folder_id,folder_name);
		  
	}

	//delete folder by id
	@DeleteMapping("/folders/{folder_name}/{folder_id}")
	public ResponseEntity<String> delete_folders(@PathVariable Long folder_id,@PathVariable String folder_name){
		folder1 file =folder_repo.findById(folder_id)
				.orElseThrow(null);//() -> new ResponseNotFound("no such id exist: "+folder_id));
		if(file.getFolder_name().equals(folder_name)) {
			 List<files> obj=files_repo.findAll();
			   for(files f:obj) {
				   if(f.getFolder_id()==folder_id) {
					   files_repo.delete(f);
				   }
			   }
			folder_repo.delete(file);
			return ResponseEntity.ok("done");
		}
		else {
		    return ResponseEntity.ok("does not deleted");
		}
	}
	//creating an file
		@PostMapping("/folder/{folder_name}/{folder_id}/{file_name}")
		public ResponseEntity<files> create_files(@PathVariable String folder_name,@PathVariable Long folder_id,@RequestBody files file){
			   folder_repo.findById(folder_id)
					         .orElseThrow(null);//() -> new ResponseNotFound("no such id exist: "+folder_id));
			   files file1=new files(file.getFile_name(),file.getFile_type(),file.getData(),folder_id);
			   files file2=files_repo.save(file1);
				 return ResponseEntity.ok(file2);	   
		}
	//update an file
		@PutMapping("/folder/{folder_name}/{folder_id}/{file_name}/{file_id}")
		public ResponseEntity<String> update_files(@PathVariable Long folder_id,@PathVariable String folder_name,@PathVariable Long file_id,@RequestBody files file){
				folder1 fold =folder_repo.findById(folder_id)
							.orElseThrow(null);//() -> new ResponseNotFound("no such id exist: "+folder_id));
				if(fold.getFolder_name().equals(folder_name))
                {
					files file1 =files_repo.findById(file_id)
							.orElseThrow(null);//() -> new ResponseNotFound("no such id exist: "+file_id));
					file1.setFile_name(file.getFile_name());
					file1.setFile_type(file.getFile_type());
					@SuppressWarnings("unused")
					files file2=files_repo.save(file1);
					return ResponseEntity.ok("file_updated");
				}
				else {
					return ResponseEntity.ok("file not updated");
				}
		}
	 // read all files in a specific folder
		@SuppressWarnings("null")
		@GetMapping("folders/{folder_name}/{folder_id}/display") 
		   public List<files> getAllFiles(@PathVariable Long folder_id){
			   List<files> file= files_repo.findAll();
			   
			   List<files> filterd_file = new ArrayList<files>();
			   for(files f:file) {
				   System.out.println(f.getFolder_id());
				   if(f.getFolder_id()==folder_id) {
					filterd_file.add(f);
				   }
			   }
			   return filterd_file;
		   }
	  //delete a file by id	 
			@DeleteMapping("/folders/{folder_name}/{folder_id}/{file_name}/{file_id}")
			public ResponseEntity<String> delete_files(@PathVariable Long folder_id,@PathVariable Long file_id,@PathVariable String folder_name ){
				folder1 fold =folder_repo.findById(folder_id).orElseThrow(null);//() -> new ResponseNotFound("no such id exist: "+folder_id));
				//folder1 fold =folder_repo.findById(folder_id).orElseThrow(null);
				files file =files_repo.findById(file_id)
						.orElseThrow(null);//() -> new ResponseNotFound("no such id exist: "+file_id));
				if(fold.getFolder_name().equals(folder_name)) {
					files_repo.delete(file);
					return ResponseEntity.ok("done");
				}
				else {
				    return ResponseEntity.ok("Doesn't exist");
				}
				//return null;
			}
	 //uploading file
	            
				@PostMapping("/folders/{folder_name}/{folder_id}/{file_name}")
				public ResponseEntity<String> upload_files(@RequestParam("file") MultipartFile file,@PathVariable Long folder_id,@PathVariable String file_name) {
					
					@SuppressWarnings("unused")
					folder1 fold =folder_repo.findById(folder_id)
							.orElseThrow(null);//() -> new ResponseNotFound("no such id exist: "+folder_id));
					files file2 =folder_service.store_file(file,folder_id);
					String file_url=ServletUriComponentsBuilder.fromCurrentContextPath()
							        .path("/download/")
							        .path(file2.getFile_name())
							        .toUriString();
				    new Response(file2.getFile_name(),file_url,file.getContentType(),file.getSize());
					return ResponseEntity.ok("uploaded");
					
				 }	
			
}

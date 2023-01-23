package com.webapi.Folder;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapi.Folder.exception.ResponseNotFound;
import com.webapi.Folder.model.files;
import com.webapi.Folder.model.folder1;
import com.webapi.Folder.payload.Response;
import com.webapi.Folder.repository.files_repo;
import com.webapi.Folder.repository.folder_repo;

@AutoConfigureMockMvc
@SpringBootTest
class FolderApplicationTests {

		@Autowired
		private MockMvc mockMvc;
		@Autowired
		private folder_repo folder_repo;
		@Autowired
		private files_repo files_repo;
//		@Autowired
//		private services s;
//		@BeforeEach
//		public void setUp() {
//		  folderService = mock(services.class);
//		}
		
//		@MockBean
//		private services folderService;

		@Test
		public void testGetAllFolders() throws Exception {
	        mockMvc.perform(get("/webapi/folders"))
	            .andExpect(status().isOk()); 
	    }
		
		@Test
		public void testGetFolderById() throws Exception {
		  mockMvc.perform(post("/webapi/folder/Folder1/5"))
	      .andExpect(status().isOk());
		  
		  @SuppressWarnings("unused")
		MvcResult result = mockMvc.perform(get("/webapi/folders1/Folder1/5"))
		            .andExpect(status().isOk())
		            .andReturn();

		  }
		@Test
		public void testCreateFolder() throws Exception {
			 mockMvc.perform(post("/webapi/folder/vijay/4"))
	         .andExpect(status().isOk());
		}
		@Test
		public void testUpdatefolders() throws Exception{
			 mockMvc.perform(post("/webapi/folder/vijay1/6"))
	                .andExpect(status().isOk());
			 mockMvc.perform(put("/webapi/folder/vijay/6"))
	                        .andExpect(status().isOk());
		}
	    @Test
	    public void deleteFolderById() throws Exception {
	    	 mockMvc.perform(post("/webapi/folder/file_vijay/7"))
	         .andExpect(status().isOk());
	    	
//	    	 mockMvc.perform(post("/webapi/folder/file_vijay/7/vijay.txt"))
//		                .andExpect(status().isOk())
//		                .andReturn();
	    	 mockMvc.perform(delete("/webapi/folders/file_vijay/6"))
             .andExpect(status().isOk())
             .andExpect(content().string("does not deleted"));
	    	 
	        mockMvc.perform(delete("/webapi/folders/file_vijay/7"))
	                .andExpect(status().isOk())
	                .andExpect(content().string("done"));

	        Optional<folder1> deletedFolder = folder_repo.findById(7L);
	        assertFalse(deletedFolder.isPresent());
	    }
	    @Test
	    public void createFile() throws Exception {
	    	Long l=(long) 2;
	        files folder = new files("vijay.txt", "text", "dummy content".getBytes(),l);
	        files_repo.save(folder);
	        
	        files file = new files("vijay.txt", "text", "dummy content".getBytes(),l);
	        ObjectMapper objectMapper = new ObjectMapper();
	        String json = objectMapper.writeValueAsString(file);

	         mockMvc.perform(post("/webapi/folder/Folder2/2/vijay.txt")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json))
	                .andExpect(status().isOk())
	                .andReturn();

	    }
	    @Test
	    public void testupdateFile() throws Exception {
	        files folder = new files("vijay1.txt", "text", "dummy content".getBytes(),1L);
	        files_repo.save(folder);
	       

	        files file2 = new files("vijay3.txt", "text", "dummy content".getBytes(),1L);
	        ObjectMapper objectMapper = new ObjectMapper();
	        String json = objectMapper.writeValueAsString(file2);
	        @SuppressWarnings("unused")
			MvcResult result = mockMvc.perform(put("/webapi/folder/Folder1/2/vijay3.txt/"+folder.getFile_id())
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json))
	                .andExpect( status().isOk())
	                .andReturn();
	        @SuppressWarnings("unused")
			MvcResult result1 = mockMvc.perform(put("/webapi/folder/Folder1/1/vijay3.txt/"+folder.getFile_id())
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(json))
	                .andExpect( status().isOk())
	                .andReturn();
	    }
	    @Test
	    public void getAllFilesByFolderId() throws Exception {
	        folder1 folder1 = new folder1(3L, "Fold1");
	        folder1 folder2 = new folder1(4L, "Fold2");
	        folder_repo.save(folder1);
	        folder_repo.save(folder2);

	        files file1 = new files("f1.txt", "text", "dummy content".getBytes(),3L);
	        files file2 = new files("f2.txt", "text", "dummy content".getBytes(),4L);
	        files file3 = new files("f3.txt", "text", "dummy content".getBytes(),3L);
	        files_repo.save(file1);
	        files_repo.save(file2);
	        files_repo.save(file3);

	         mockMvc.perform(get("/webapi/folders/Fold1/3/display"))
	                .andExpect(status().isOk())
	                .andReturn();
	    }
	    @Test
	    public void deleteFile() throws Exception {
	        folder1 folder = new folder1(5L, "delete2");
	        folder_repo.save(folder);
	        files file1 = new files("delete_file.txt", "text", "dummy content".getBytes(),5L);
	        files file=files_repo.save(file1);
	        @SuppressWarnings("unused")
			MvcResult result1 = mockMvc.perform(delete("/webapi/folders/delete2/4/delete_file.txt/"+file.getFile_id()))
	        		.andExpect(status().isOk())
	                .andExpect(content().string("Doesn't exist"))
	                .andReturn();
	        
	        @SuppressWarnings("unused")
			MvcResult result = mockMvc.perform(delete("/webapi/folders/delete2/5/delete_file.txt/"+file.getFile_id()))
	                .andExpect(status().isOk())
	                .andExpect(content().string("done"))
	                .andReturn();
	        Optional<files> deletedFile = files_repo.findById(5L);
	        assertFalse(deletedFile.isPresent());
	    }
	    @Test
	     public void testResponse() {
	    	 Response r=new Response("hai","hello","txt",1L);
	    	 Assertions.assertEquals("hai",r.getFileName());
	    	 Assertions.assertEquals("hello",r.getFileDownloadUri());
	    	 Assertions.assertEquals("txt",r.getFileType());
	    	 Assertions.assertEquals(1,r.getSize());
	    	 Response r1=new Response();
	    	 r1.setFileName("helo");
	    	 r1.setFileDownloadUri("hfa");
	    	 r1.setFileType("txt");
	    	 r1.setSize(400);
	    	 Assertions.assertEquals("helo",r1.getFileName());
	    	 Assertions.assertEquals("hfa",r1.getFileDownloadUri());
	    	 Assertions.assertEquals("txt",r1.getFileType());
	    	 Assertions.assertEquals(400,r1.getSize());
	    	 folder1 f=new folder1();
	    	 f.setFolder_id(1);
	    	 Assertions.assertEquals(1,f.getFolder_id());
	     }
	    @Test
	    public void testException() {
	    	new ResponseNotFound("hai");
	    }
	    @Test
	    public void test() {
	    	String[] args = { "arg1", "arg2" };
	    	FolderApplication.main(args);
	    	
	    }
	    @Test
	    public void testFileUpload() throws Exception {
	    	
	    	 MockMultipartFile file = new MockMultipartFile("file", "namw.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());
	         mockMvc.perform(MockMvcRequestBuilders.multipart("/webapi/folders/Folder1/1/name1")
	        		 .file(file)
	                 .contentType("application/json")
	                
	                 .header("Content-Disposition", "attachment; filename="+file.getName()))
	                 .andExpect(MockMvcResultMatchers.status().isOk());
	     }

	}

	



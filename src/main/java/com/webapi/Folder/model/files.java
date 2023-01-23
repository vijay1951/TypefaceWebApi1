package com.webapi.Folder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="files")
public class files {
	
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long file_id;
	  
	    @Column(name="file_name")
        private String file_name;
	    
	    @Column(name="file_type")
        private String file_type;
	    
	    @Lob
	    private byte[] data;
	    
	    @Column(name="folder_id")
	    private Long folder_id;
	    
		public files() {
        	
        }

		public long getFile_id() {
			return file_id;
		}

		public void setFile_id(long file_id) {
			this.file_id = file_id;
		}

		public String getFile_name() {
			return file_name;
		}

		public void setFile_name(String file_name) {
			this.file_name = file_name;
		}

		public String getFile_type() {
			return file_type;
		}

		public void setFile_type(String file_type) {
			this.file_type = file_type;
		}

		public byte[] getData() {
			return data;
		}

		public void setData(byte[] data) {
			this.data = data;
		}

		public Long getFolder_id() {
			return folder_id;
		}

		public files(String file_name, String file_type, byte[] data, Long folder_id) {
			super();
			this.file_name = file_name;
			this.file_type = file_type;
			this.data = data;
			this.folder_id = folder_id;
		}

		public void setFolder_id(Long folder_id) {
			this.folder_id = folder_id;
		}

	
		
		
}

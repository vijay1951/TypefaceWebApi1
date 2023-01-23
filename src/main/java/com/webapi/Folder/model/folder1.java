package com.webapi.Folder.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="folders")
public class folder1 {
	   
	    @Id
        private long folder_id;
	    
	    @Column(name="folder_name")
	    private String folder_name;
	    
	  
        public folder1() {
        	
        }
		public folder1(long folder_id, String folder_name) {
			super();
			this.folder_id = folder_id;
			this.folder_name = folder_name;
		}
		
		public long getFolder_id() {
			return folder_id;
		}

		public void setFolder_id(long folder_id) {
			this.folder_id = folder_id;
		}

		public String getFolder_name() {
			return folder_name;
		}

		public void setFolder_name(String folder_name) {
			this.folder_name = folder_name;
		}
}

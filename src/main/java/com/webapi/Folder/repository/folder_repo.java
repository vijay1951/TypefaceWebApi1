package com.webapi.Folder.repository;

//import java.util.List;
//@Query("SELECT * FROM files2 f WHERE f.file_type=file_type")
//Optional<MultipartFile> findByFile_type(String file_type);
//@Query("select * from files1 s where s.file_type = :file_type")
//	public folder1 findByType(String file_type);
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.webapi.Folder.model.folder1;
@Component
@Repository
public interface folder_repo extends JpaRepository<folder1,Long>{
	
}


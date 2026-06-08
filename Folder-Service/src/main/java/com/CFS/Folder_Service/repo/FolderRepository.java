package com.CFS.Folder_Service.repo;

import com.CFS.Folder_Service.model.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<FolderEntity, Long> {

    List<FolderEntity> findByParentId(Long parentId);
    FolderEntity findByName(String name);
}

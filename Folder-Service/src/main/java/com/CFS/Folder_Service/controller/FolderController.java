package com.CFS.Folder_Service.controller;

import com.CFS.Folder_Service.model.FolderEntity;
import com.CFS.Folder_Service.repo.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/folders")
public class FolderController {

    @Autowired
    private FolderRepository folderRepository;

    @GetMapping
    public List<FolderEntity> getAllFolders(){
        return folderRepository.findAll();
    }

    @GetMapping("/{id}")
    public FolderEntity getFolderById(@PathVariable Long id){
        return folderRepository.findById(id).orElse(null);
    }

    @GetMapping("/name/{name}")
    public FolderEntity getFolderByName(@PathVariable String name){
        return folderRepository.findByName(name);
    }

    @PostMapping
    public FolderEntity createFolder(@RequestBody FolderEntity folder){
        return folderRepository.save(folder);
    }

    @DeleteMapping
    public void deleteFolderById(@PathVariable Long id){
        folderRepository.deleteById(id);
        System.out.println("Folder Deleted ");
    }

    @GetMapping("/parent/{parentId}")
    public List<FolderEntity> getFoldersByParentId(@PathVariable Long parentId){
        return folderRepository.findByParentId(parentId);
    }

    @PostMapping("/create")
    public Map<String, Object> createNewFolder(@RequestBody Map<String, Object>  request){

        try {
            String name = (String) request.get("name");
            Long parentId = (Long) request.get("parentId") != null ?
                    Long.valueOf(request.get("parentId").toString()) : null;

            FolderEntity folder = new FolderEntity();
            folder.setId(System.currentTimeMillis());
            folder.setName(name);
            folder.setParentId(parentId);

            FolderEntity save = folderRepository.save(folder);

            return Map.of("success", true, "folder", save);

        }catch (Exception e){
            return Map.of("success", false, "message", e.getMessage());
        }
    }
}

package com.cfs.search_service.controller;

import com.cfs.search_service.client.FileSearchClient;
import com.cfs.search_service.client.FolderSearchClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private FileSearchClient fileSearchClient;

    @Autowired
    private FolderSearchClient folderSearchClient;

    @GetMapping
    public Map<String,Object> search(@RequestParam String query){
        List<Map<String,Object>> allFiles = fileSearchClient.getAllFiles();
        List<Map<String,Object>> allFolders = folderSearchClient.getAllFolders();

        List<Map<String,Object>> fileResult = allFiles.stream()
                .filter(f-> f.get("name").toString().toLowerCase().contains(query.toLowerCase()))
                .toList();

        List<Map<String,Object>> folderResult = allFolders.stream()
                .filter(f-> f.get("name").toString().toLowerCase().contains(query.toLowerCase()))
                .toList();

        Map<String,Object> result = new HashMap<>();
        result.put("files",fileResult);
        result.put("folders",folderResult);
        return result;
    }

    @GetMapping("/files")
    public List<Map<String,Object>> searchFiles(@RequestParam String query){
        List<Map<String,Object>> allFiles = fileSearchClient.getAllFiles();

        return allFiles.stream()
                .filter(f-> f.get("name").toString().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }

    @GetMapping("/folder")
    public List<Map<String,Object>> searchFolders(@RequestParam String query){
        List<Map<String,Object>> allFolders = folderSearchClient.getAllFolders();

        return  allFolders.stream()
                .filter(f-> f.get("name").toString().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }
}

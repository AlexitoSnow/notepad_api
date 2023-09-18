package com.snow.bloc_notas_api.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.snow.bloc_notas_api.models.File;
import com.snow.bloc_notas_api.repositories.FileRepository;

/**
 * File controller
 * Manage all requests related to files
 */
@RestController
@CrossOrigin // Allow requests from any origin
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    /**
     * Get all files
     * @return File list
     */
    @GetMapping("/files")
    public ResponseEntity<List<File>> getFiles() {
        return ResponseEntity.status(HttpStatus.OK).body(fileRepository.findAll());
    }

    /**
     * Get all titles
     * @return List of titles
     */
    @GetMapping("/files/titles")
    public ResponseEntity<List<String>> getTitlesFromFiles() {
        List<String> titles = new LinkedList<>();
        for (File file : fileRepository.findAll()) {
            titles.add(file.getTitle());
        }
        return ResponseEntity.status(HttpStatus.OK).body(titles);
    }

    /**
     * Get the content of a file
     * @param title File title
     * @return Content of the file, or null if it doesn't exist
     */
    @GetMapping("/file/{title}")
    public ResponseEntity<String> getContentOf(@PathVariable(value = "title") String title) {
        if (!fileRepository.existsById(title)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(fileRepository.findById(title).get().getContent());
    }

    /**
     * Create an empty file
     * @param title File title
     * @return true if it was created, false if it already exists
     */
    @PostMapping("/file/{title}")
    public ResponseEntity<Boolean> createEmptyFile(@PathVariable(value = "title") String title) {
        if (fileRepository.existsById(title)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
        }
        fileRepository.save(new File(title));
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    /**
     * Update the content of a file
     * @param title File title
     * @param content New content
     * @return true if it was updated, false if it doesn't exist
     */
    @PutMapping("/file/{title}")
    public ResponseEntity<Boolean> updateArchivo(@PathVariable(value = "title") String title, @RequestBody(required = false) String content) {
        if (!fileRepository.existsById(title)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        File fileToUpdate = fileRepository.findById(title).get();
        fileToUpdate.setContent(content);
        fileRepository.save(fileToUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    /**
     * Rename a file
     * @param oldTitle Actual file title
     * @param newTitle New file title
     * @return true if it was renamed, false if it doesn't exist or the new title already exists
     */
    @PutMapping("/file/rename/{old-title}/{new-title}")
    public ResponseEntity<Boolean> renameArchivo(@PathVariable(value = "old-title") String oldTitle, @PathVariable(value = "new-title") String newTitle) {
        if (!fileRepository.existsById(oldTitle)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        if (fileRepository.existsById(newTitle)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(false);
        }
        File fileToRename = fileRepository.findById(oldTitle).get();
        fileRepository.deleteById(oldTitle);
        fileToRename.setTitle(newTitle);
        fileRepository.save(fileToRename);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    /**
     * Delete a file
     * @param title File title
     * @return true if it was deleted, false if it doesn't exist
     */
    @DeleteMapping("/file/{title}")
    public ResponseEntity<Boolean> deleteArchivo(@PathVariable(value = "title") String title) {
        if (!fileRepository.existsById(title)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        fileRepository.deleteById(title);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    /**
     * Delete all files
     * Only avaible in localhost
     * @return true if all files were deleted
     */
    @CrossOrigin(origins = "http://localhost:9090")
    @DeleteMapping("/files")
    public ResponseEntity<Boolean> deleteFiles() {
        fileRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
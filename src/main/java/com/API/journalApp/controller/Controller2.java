package com.API.journalApp.controller;

import com.API.journalApp.entity.JournalEntry;
import com.API.journalApp.entity.Users;
import com.API.journalApp.services.JournalEntryService;
import com.API.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("journal")
public class Controller2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping ("{userName}")    
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser(@PathVariable String userName){
        Users user = userService.findByUserName(userName);
        List<JournalEntry> getAll = user.getJournalEntries();
        if (getAll!= null && !getAll.isEmpty()){
            return new ResponseEntity<>(getAll, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping ("{userName}")  
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry firstEntry, @PathVariable String userName){
        try {
            journalEntryService.saveEntry(firstEntry, userName);
            return new ResponseEntity<>(firstEntry,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")  
    public  ResponseEntity<JournalEntry> getJournalIdById(@PathVariable ObjectId myId ){
       Optional <JournalEntry> byId = journalEntryService.findById(myId);
        if (byId.isPresent()){
            return new ResponseEntity<>(byId.get(), HttpStatus.OK);
        }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{myId}")  
    public  ResponseEntity<?> deleteJournalById(@PathVariable ObjectId myId, @PathVariable String userName ){
         journalEntryService.deleteById(myId, userName);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping ("id/{userName}/{id}") 
    public  ResponseEntity<?> updateEntriesById(@PathVariable ObjectId id,
                                                @RequestBody JournalEntry newEntry,
                                                @PathVariable String userName){
        JournalEntry oldEntry = journalEntryService.findById(id).orElse(null);
        if (oldEntry!= null){
            oldEntry.setTitle(newEntry.getTitle() != null&& !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent()!= null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


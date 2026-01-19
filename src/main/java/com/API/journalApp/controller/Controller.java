package com.API.journalApp.controller;

import com.API.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("_Journal")
public class Controller {

    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

//    @PostMapping   //registering entries in journal
//    public boolean createEntry(@RequestBody JournalEntry firstEntry){
//        journalEntries.put(firstEntry.getId(), firstEntry);
//        return  true;
//    }

    @GetMapping("id/{myId}")  //get a single entry from the journal
    public  JournalEntry getJournalIdById(@PathVariable Long myId ){
        return journalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")  //used for deletion of entries for here
    public  JournalEntry deleteJournalById(@PathVariable Long myId ){
        return journalEntries.remove(myId);
    }

    @PutMapping ("/id/{id}")  //for updating the data in the entries
    public  JournalEntry updateEntriesById(@PathVariable Long id, @RequestBody JournalEntry firstEntry){
        return  journalEntries.put(id, firstEntry);
    }
}


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

    @PostMapping   
    public boolean createEntry(@RequestBody JournalEntry firstEntry){
        journalEntries.put(firstEntry.getId(), firstEntry);
        return  true;
    }

    @GetMapping("id/{myId}")  
    public  JournalEntry getJournalIdById(@PathVariable Long myId ){
        return journalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")  
    public  JournalEntry deleteJournalById(@PathVariable Long myId ){
        return journalEntries.remove(myId);
    }

    @PutMapping ("/id/{id}")  
    public  JournalEntry updateEntriesById(@PathVariable Long id, @RequestBody JournalEntry firstEntry){
        return  journalEntries.put(id, firstEntry);
    }
}


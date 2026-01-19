package com.API.journalApp.services;

import com.API.journalApp.entity.JournalEntry;
import com.API.journalApp.entity.Users;
import com.API.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository; //injecting repository in this class

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            Users user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);

            user.getJournalEntries().add(saved);   //mapped   every entry with their username
            userService.saveEntry(user);            //and saved
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveEntry(JournalEntry oldEntry) {  //yeh put ke liye kyunki usmein username chhaiye hi nhi
        journalEntryRepository.save(oldEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        Users user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x-> x.getId().equals(id));  //turant ke turant deleted entry ka reference hat jaaega
        userService.saveEntry(user);                            //khair next save pe spring hud hi hata deta hai agar manually delete na hua ho
        journalEntryRepository.deleteById(id);
    }
}

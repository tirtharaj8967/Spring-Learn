package com.spring.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.Entity.JournalEntry;
import com.spring.Entity.User;
import com.spring.Repository.JournalEntryRepository;
import com.spring.Repository.UserRepository;

@Component
public class JournalEntryService {

	@Autowired
	private JournalEntryRepository journalrepo;
	
	@Autowired
	private UserService userService;
	
	//Save 
	public void saveJournal(JournalEntry journalEntry, String userName) {
		journalEntry.setDate(LocalDate.now());
		User user = userService.findByUserName(userName);
		JournalEntry entry= journalrepo.save(journalEntry);
		user.getJournalEntries().add(entry);
		userService.saveUser(user);
	}
	
	public void saveJournal(JournalEntry journalEntry) {
		journalEntry.setDate(LocalDate.now());
		journalrepo.save(journalEntry);

	}
	
	//Get ALL
	public List<JournalEntry> getAllJournals(){
		return journalrepo.findAll();
	}
	
	//Get By ID
	public JournalEntry getJournalById(ObjectId id) {
		return journalrepo.findById(id).orElse(null);
	}
	
//	Delete by ID
	public boolean deleteJournal(ObjectId id, String userName) {
		JournalEntry entry = getJournalById(id);
		User user = userService.findByUserName(userName);
//		user.getJournalEntries().remove(entry);
		user.getJournalEntries().removeIf(x -> x.getId().equals(id));
		journalrepo.deleteById(id);
		userService.saveUser(user);
		
		return true;
	}
	
//	Delete All
	public void deleteAllJournals() {
		 journalrepo.deleteAll();
		 
	}
}

package com.spring.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.Entity.JournalEntry;
import com.spring.Repository.JournalEntryRepository;

@Component
public class JournalEntryService {

	@Autowired
	private JournalEntryRepository journalrepo;
	
	public void saveJournal(JournalEntry journalEntry) {
		journalEntry.setDate(LocalDate.now());
		journalrepo.save(journalEntry);
	}
	
	public List<JournalEntry> getAllJournals(){
		return journalrepo.findAll();
	}
	
	public JournalEntry getJournalById(ObjectId id) {
		return journalrepo.findById(id).orElse(null);
	}
	
	/*
	 * public JournalEntry updateJournal(ObjectId id, JournalEntry newEntry) {
	 * 
	 * return oldEntry; }
	 */
	
	public boolean deleteJournal(ObjectId id) {
		JournalEntry entry = getJournalById(id);
		if(entry != null) {
			journalrepo.deleteById(id);
		}
		return true;
	}
	public void deleteAllJournals() {
		 journalrepo.deleteAll();
		 
	}
}

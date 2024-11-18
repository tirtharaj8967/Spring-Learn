package com.spring.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.Entity.JournalEntry;
import com.spring.Repository.JournalEntryRepository;

@Component
public class JournalEntryService {

	@Autowired
	private JournalEntryRepository journalrepo;
	
	public void saveJournal(JournalEntry journalEntry) {
		journalrepo.save(journalEntry);
	}
	
	public List<JournalEntry> getAllJournals(){
		return journalrepo.findAll();
	}
}

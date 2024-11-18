package com.spring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.Entity.JournalEntry;
import com.spring.Service.JournalEntryService;

@RestController
@RequestMapping("/mainjournal")
public class JournalEntryMainController {

	@Autowired
	private JournalEntryService journalEntryService;
	
	@PostMapping("/create")
	public boolean createJournal(JournalEntry journalEntry) {
		journalEntryService.saveJournal(journalEntry);
		return true;
	}
	
	@GetMapping("getAll")
	public List<JournalEntry> getAll() {
		return journalEntryService.getAllJournals();
	}
}

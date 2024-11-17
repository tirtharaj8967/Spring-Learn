package com.spring.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.Entity.JournalEntry;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
 
	public Map<Long, JournalEntry> entries = new HashMap<>();
	
	@GetMapping
	public List<JournalEntry> getJournals(){
		return new ArrayList<>(entries.values());
	}
	
	@PostMapping
	public boolean addJournal(@RequestBody JournalEntry myentry) {
		entries.put(myentry.getId(), myentry);
		return true;
	}
	
	@GetMapping("/id/{myId}")
	public JournalEntry getJournalbyId(@PathVariable Long myId) {
		return entries.get(myId);
	}
	
	@DeleteMapping("id/{myId}")
	public boolean deleteJournal(@PathVariable Long myId) {
		entries.remove(myId);
		return true;
	}
	
	@PutMapping("id/{myId}")
	public JournalEntry updateJournal(@PathVariable Long myId, @RequestBody JournalEntry entry) {
		return entries.put(myId, entry);
	}
}

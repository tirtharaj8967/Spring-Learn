package com.spring.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.Entity.JournalEntry;
import com.spring.Entity.User;
import com.spring.Service.JournalEntryService;
import com.spring.Service.UserService;

@RestController
@RequestMapping("/mainjournal")
public class JournalEntryMainController {

	@Autowired
	private JournalEntryService journalEntryService;
	@Autowired
	private UserService userService;

	@PostMapping("createJournal/{userName}")
	public ResponseEntity<?> createJournal(@RequestBody JournalEntry journalEntry, @PathVariable String userName) {
		try {
			journalEntryService.saveJournal(journalEntry,userName);
			return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}
	}

	@GetMapping("{userName}")
	public ResponseEntity<?> getAllJournalEntriesofUser(@PathVariable String userName) {
		User user= userService.findByUserName(userName);
		List<JournalEntry> entries= user.getJournalEntries();
		if(user!=null && !entries.isEmpty()) {
			
			return new ResponseEntity<>(entries, HttpStatus.OK);
		} 
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		
	}

	@GetMapping("getJournal/{id}")
	public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId id) {
		Optional<JournalEntry> journalEntry = Optional.ofNullable(journalEntryService.getJournalById(id));
		if (journalEntry.isPresent()) {
			return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("updateJournal/{userName}/{myId}")
	public ResponseEntity<JournalEntry> updateJournal(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry) {
		
		 JournalEntry oldEntry = journalEntryService.getJournalById(myId); 
		  if(oldEntry != null) {
		  oldEntry.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
		  oldEntry.setContent(newEntry.getContent()!=null &&!newEntry.getContent().equals("") ? newEntry.getContent():  oldEntry.getContent()); 
		  journalEntryService.saveJournal(oldEntry);
			return new ResponseEntity<JournalEntry>(oldEntry, HttpStatus.OK);
		  } 
		  else {
			  return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
		  }
		
	}

	@DeleteMapping("deleteJournal/{userName}/{id}")
	public ResponseEntity<JournalEntry> deleteJournal(@PathVariable ObjectId id, @PathVariable String userName) {
		journalEntryService.deleteJournal(id, userName);
		return new ResponseEntity<JournalEntry>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("deleteAllJournals")
	public ResponseEntity<JournalEntry> deleteAllJournals() {
		journalEntryService.deleteAllJournals();
		return new ResponseEntity<JournalEntry>(HttpStatus.NO_CONTENT);
	}
}

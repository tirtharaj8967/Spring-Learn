package com.spring.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.Entity.JournalEntry;



public interface JournalEntryRepository extends MongoRepository<JournalEntry, String>{

}
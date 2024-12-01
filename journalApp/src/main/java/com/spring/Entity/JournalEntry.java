package com.spring.Entity;

import java.sql.Date;
import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "journals")
@Data
public class JournalEntry {

	@Id
	private ObjectId id;
	private String title;
	private String content;
	private LocalDate date;
	

	
	
}

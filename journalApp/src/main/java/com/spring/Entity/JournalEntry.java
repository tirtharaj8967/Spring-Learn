package com.spring.Entity;

import java.sql.Date;
import java.time.LocalDate;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "journals")
@Data
@NoArgsConstructor
public class JournalEntry {

	@Id
	private ObjectId id;
	@NonNull
	private String title;
	@NonNull
	private String content;
	@NonNull
	private LocalDate date;
	

	
	
}

package com.fundonotes.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private int id;
	private String fname;
	private String lname;
	private String email;
	private String password;
	
	private LocalDateTime userCreated;
	private LocalDateTime userUpdated;
	private boolean emailVerified;
	
//	@OneToMany(targetEntity = Note.class)
//	@JoinColumn(name = "user_id")
//	private List<Note> notes;

//	@OneToMany(targetEntity = Label.class)
//	@JoinColumn(name = "user_id")
//	private List<Label> labels;
}

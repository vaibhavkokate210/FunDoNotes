package com.fundouser.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false,unique = false)
	private String fname;
	@Column(nullable = false,unique = false)
	private String lname;
	@Column(nullable = false,unique = true)
	private String email;
	@Column(nullable = false,unique = false)
	private String password;
	@CreationTimestamp
	private LocalDateTime userCreated;
	@UpdateTimestamp
	private LocalDateTime userUpdated;
	@Column(nullable = true)
	private boolean emailVerified;
	
//	@OneToMany(targetEntity = Note.class)
//	@JoinColumn(name = "user_id")
//	private List<Note> notes;

//	@OneToMany(targetEntity = Label.class)
//	@JoinColumn(name = "user_id")
//	private List<Label> labels;
}

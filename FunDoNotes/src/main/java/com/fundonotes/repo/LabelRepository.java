package com.fundonotes.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fundonotes.entity.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Integer> {
	Label findByLabelName(String labelName); 
	Label findByLabelName(Label labelName); 

}

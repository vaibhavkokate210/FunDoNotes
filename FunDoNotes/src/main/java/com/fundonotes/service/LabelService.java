package com.fundonotes.service;

import java.util.List;

import com.fundonotes.entity.Label;


public interface LabelService {
	public Label createLable(String token, String labelName);
	
	public List<Label> displayAllLabels(String token);
	
	public void deleteLabel(String token, int labelId);
	
	public Label updateLabel(String token, int labelId, String labelName);
}

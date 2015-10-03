 package com.elberto.services.diary;

import java.util.List;

import com.elberto.domain.Action;


public interface DiaryManagementService 
{
	
	public void recordAction(Action action);
	
	
	public List<Action> getAllIncompleteActions(String requiredUser);
}

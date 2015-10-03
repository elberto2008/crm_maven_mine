package com.elberto.dataaccess;

import java.util.List;

import com.elberto.domain.Action;
import com.elberto.exceptions.RecordNotFoundException;

// FOR USE IN A LATER CHAPTER - PLEASE IGNORE UNTIL THEN

public interface ActionDao 
{
	public void create(Action newAction);
	public List<Action> getIncompleteActions(String userId);
	public void update(Action actionToUpdate) throws RecordNotFoundException;
	public void delete(Action oldAction) throws RecordNotFoundException;
}

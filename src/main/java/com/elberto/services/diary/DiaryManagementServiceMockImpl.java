package com.elberto.services.diary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.elberto.domain.Action;

public class DiaryManagementServiceMockImpl implements DiaryManagementService {
	
	
	private Set<Action> allActions = new HashSet<Action>();

	
	public void recordAction(Action action) {
		allActions.add(action);
	}

	
	public List<Action> getAllIncompleteActions(String requiredUser) 
	{
		List<Action> results = new ArrayList<Action>();
		
		for (Action next: allActions)
		{
			if (next.getOwningUser().equals(requiredUser) && !next.isComplete())
			{
				results.add(next);
			}
		}
		return results;
	}

}

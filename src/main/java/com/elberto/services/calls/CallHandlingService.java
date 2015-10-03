package com.elberto.services.calls;

import java.util.Collection;

import com.elberto.domain.Action;
import com.elberto.domain.Call;
import com.elberto.services.customers.CustomerNotFoundException;

public interface CallHandlingService 
{
	// DO NOT IMPLEMENT THIS INTERFACE UNTIL A LATER CHAPTER!
	
	
	public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException;
}

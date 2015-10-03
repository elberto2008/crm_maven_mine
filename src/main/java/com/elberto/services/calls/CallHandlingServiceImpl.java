package com.elberto.services.calls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elberto.domain.Action;
import com.elberto.domain.Call;
import com.elberto.services.customers.CustomerManagementService;
import com.elberto.services.customers.CustomerNotFoundException;
import com.elberto.services.diary.DiaryManagementService;

@Transactional
@Service
public class CallHandlingServiceImpl implements CallHandlingService 
{
	private CustomerManagementService customerService;
	private DiaryManagementService diaryService;
	
	@Autowired
	public CallHandlingServiceImpl(CustomerManagementService cms, DiaryManagementService dms)
	{
		this.customerService = cms;
		this.diaryService = dms;
	}
	
	
	
	public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException 
	{
		// 1: call the customer service to record the call
		customerService.recordCall(customerId, newCall);
		
		// 2: call the diary service to record the actions
		for (Action nextAction: actions)
		{
			diaryService.recordAction(nextAction);			
		}
	}

}

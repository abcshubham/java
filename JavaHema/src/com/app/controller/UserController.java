package com.app.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IUserDao;
import com.app.pojos.UserExpense;
import com.app.pojos.UserIncome;
import com.app.pojos.UserPojos;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController 
{
    @Autowired
    private IUserDao dao;
    
    public UserPojos u1 = new UserPojos();
 
    public UserController() 
    {
 	    System.out.println("in the ctor"+getClass().getName());
    }
 
    @PostMapping("/login")
 	public ResponseEntity<String> loginuser(@RequestBody UserPojos u ,HttpSession hs)
 	{ 
     	System.out.println("email"+u.getEmail()+"password"+u.getPassword());
 		try
 		{
 			UserPojos u2= dao.ValidateUser(u.getEmail(),u.getPassword());
 			u1=u2;
 		    //hs.setAttribute("user",u1);
 		    System.out.println("User In In Login Controller="+u1.getId());
 		   for (UserExpense uex : u1.getExpense()) {
			
 			   System.out.println("User Expence= "+uex);
		}

 		    	if(u1.getRole().equals("user"))
 				{
 		    		return new ResponseEntity<>("user login succesful",HttpStatus.OK);
 				
 				}
 				else
 				{
 					return new ResponseEntity<>("admin login succesful",HttpStatus.OK);
 					
 				}
 		    
 		}
 		catch (RuntimeException e)
 		{
 			System.out.println(" err in the login "+e);
 			return new ResponseEntity<>("login failed "+e.getMessage(),HttpStatus.OK);
 		}
 	}
    
    @PostMapping("/register")
   	public ResponseEntity<String> registerUser(@RequestBody UserPojos u,HttpSession hs)
   	{
    	System.out.println("rest service: reg user "+u);
    	try
    	{
    		return new ResponseEntity<>(dao.registerUser(u),HttpStatus.OK);
    	}
    	catch (RuntimeException e)
    	{
			System.out.println("err in the reg"+e);
			return new ResponseEntity<>("user login failed "+e.getMessage(),HttpStatus.OK);
		}
   	}
    
    @PostMapping("/transaction-expense")
  	public ResponseEntity<String> registerUser(@RequestBody UserExpense u,HttpSession hs)
  	{
   	System.out.println("rest service: reg user "+u);
   	try
   	{
   		System.out.println("In User Reg controller");
   		System.out.println("User ID="+u1.getId());
         u.setU1(u1);
   		return new ResponseEntity<>(dao.incomeregister(u),HttpStatus.OK);
   	}
   	catch (RuntimeException e)
   	{
			System.out.println("err in the reg"+e);
			return new ResponseEntity<>("user login failed "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
   	
  	}
   
    @GetMapping("/expense-report")
	public ResponseEntity<List<UserExpense>> findAll()
	{
		System.out.println("int the list controller");
	
		System.out.println(u1.getId());
		return new ResponseEntity<>(dao.getalllist(u1.getId()),HttpStatus.OK);
				
	}
    
    @DeleteMapping("/{id}")
	public ResponseEntity<String> deletemovie(@PathVariable int id)
	{
		System.out.println(" in delete stock");
		try
		{
			return new ResponseEntity<>(dao.deleteexpense(id),HttpStatus.OK);
		}
		catch (RuntimeException e) 
		{
			return new ResponseEntity<>("expense deletion failed "+e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updatemovie(@PathVariable int id,@RequestBody UserExpense update)
	{
		System.out.println("in the expense ctor"+id+" "+update);
		UserExpense abc = dao.expense(id);
        update.setU1(u1);
		if(abc==null)
		return new ResponseEntity<String>("Invalid expense ID", HttpStatus.NOT_FOUND);
		update.setAssetid(abc.getAssetid());
		
		return new ResponseEntity<UserExpense>(dao.updateExpense(update), HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<UserExpense> getexpense(@PathVariable int id)
	{
		System.out.println("in get expense "+id);
		return new ResponseEntity<UserExpense>(dao.expense(id), HttpStatus.OK);
	}
	
	
	@PostMapping("/transaction-income")
  	public ResponseEntity<String> registerUser(@RequestBody UserIncome u,HttpSession hs)
  	{
   	System.out.println("rest service: reg user "+u);
   	try
   	{
   		System.out.println("In User Reg controller");
   		System.out.println("User ID="+u1.getId());
        u.setU(u1);
   		return new ResponseEntity<>(dao.expenseregister(u),HttpStatus.OK);
   	}
   	catch (RuntimeException e)
   	{
	   System.out.println("err in the reg"+e);
	   return new ResponseEntity<>("user income failed "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
   	
  	}

	 @GetMapping("/expense-income")
	 	public ResponseEntity<List<UserIncome>> findincome()
	 	{
	 		System.out.println("int the list controller");
	 	
	 		
	 		return new ResponseEntity<>(dao.getlistincome(u1.getId()),HttpStatus.OK);
	 				
	 	}
	
	 @DeleteMapping("/income/{id}")
		public ResponseEntity<String> deleteIncome(@PathVariable int id)
		{
			System.out.println(" in delete income");
			try
			{
				return new ResponseEntity<>(dao.deleteincome(id),HttpStatus.OK);
			}
			catch (RuntimeException e) 
			{
				return new ResponseEntity<>("income deletion failed "+e.getMessage(),HttpStatus.NOT_FOUND);
			}
		}
		@PutMapping("/income/{id}")
		public ResponseEntity<?> updateIncome(@PathVariable int id,@RequestBody UserIncome update)
		{
			System.out.println("in the income ctor"+id+" "+update);
			UserIncome abc = dao.income(id);
	        update.setU(u1);
			if(abc==null)
			return new ResponseEntity<String>("Invalid Stock ID", HttpStatus.NOT_FOUND);
			update.setBillid(abc.getBillid());
			
			return new ResponseEntity<UserIncome>(dao.updateIncome(update), HttpStatus.OK);
		}
		
		
		@GetMapping("/income/{id}")
		public ResponseEntity<UserIncome> getincome(@PathVariable int id)
		{
			System.out.println("in get income "+id);
			return new ResponseEntity<UserIncome>(dao.income(id), HttpStatus.OK);
		}
	    
		 
	    @GetMapping("/totalexpense")
		public ResponseEntity<Double> totalexpense()
		{
			System.out.println("int the list controller");
		
			System.out.println("user id"+u1.getId());
			return new ResponseEntity<>(dao.getexpense(u1.getId()),HttpStatus.OK);
					
		}
	    
	    @GetMapping("/totalincome")
		public ResponseEntity<Double> totalincome()
		{
			System.out.println("int the list controller");
		
			System.out.println("user id"+u1.getId());
			return new ResponseEntity<>(dao.getincome(u1.getId()),HttpStatus.OK);
					
		}
	    
	   @GetMapping("/setting")
	   public ResponseEntity<UserPojos> getsetting()
	   {
		   System.out.println(" in the setting controller");
		   System.out.println("user id"+u1);
		   return new ResponseEntity<>(u1,HttpStatus.OK);
	   }
	   
		@PutMapping("/setting/{id}")
		public ResponseEntity<?> updatepojo(@PathVariable int id,@RequestBody UserPojos update)
		{
			System.out.println("in the income ctor"+id+" "+update);
			UserPojos abc = dao.setting(u1);
	        
	        if(abc==null)
				return new ResponseEntity<String>("Invalid Stock ID", HttpStatus.NOT_FOUND);
	        update.setId(id);
				
				return new ResponseEntity<UserPojos>(dao.setting(update), HttpStatus.OK);
		}
		
  
}
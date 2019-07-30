package com.app.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
import com.app.pojos.UserAddExpense;
import com.app.pojos.UserAddMop;
import com.app.pojos.UserComplain;
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
    
    @Autowired
    private JavaMailSender sender;
    
    public UserPojos u1 = new UserPojos();
 
    public UserController() 
    {
 	    System.out.println("in the ctor"+getClass().getName());
    }
 
    @PostMapping("/login")
 	public ResponseEntity<String> loginuser(@RequestBody UserPojos u ,HttpSession hs)
 	{ 
     	System.out.println("email"+u.getEmail()+"password"+u.getPassword());
     	System.out.println("in the login "+u.getRole());
 		try
 		{
 			UserPojos u2= dao.ValidateUser(u.getEmail(),u.getPassword());
 			u1=u2;
 			System.out.println(u2);
 			System.out.println(u1);
 		    System.out.println("User In In Login Controller="+u1.getId());
 		    System.out.println("in the login "+u1.getRole());
 		   for (UserExpense uex : u1.getExpense()) 
 		   {
			System.out.println("User Expence= "+uex);
		   }
            if(u1.getRole().equals("user"))
 				{
 		         return new ResponseEntity<>("user login succesful",HttpStatus.OK);
 				}
 				else if(u1.getRole().equals("admin"))
 				{
 					return new ResponseEntity<>("admin login succesful",HttpStatus.OK);
 				}
 				else
 				{
 					return new ResponseEntity<>("invalid",HttpStatus.OK);
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
    	   SimpleMailMessage mesg= new SimpleMailMessage();
     	   mesg.setTo(u.getEmail());
     	   mesg.setSubject("U Have register succ.");
     	   mesg.setText("THanku!");
     	   sender.send(mesg);
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
	  return new ResponseEntity<>(dao.ValidateUser(u1.getEmail(),u1.getPassword()),HttpStatus.OK);
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
		
	@GetMapping("/expensemonth/{id}")
	public ResponseEntity<Double> totalexpense(UserExpense u,@PathVariable int id)
	{
	 System.out.println("int the list expense"+id);
	 System.out.println("user id"+u1.getId());
	 return new ResponseEntity<>(dao.getexpensemon(u1.getId(),id),HttpStatus.OK);
	}
	    
	@GetMapping("/incomemonth/{id}")
	public ResponseEntity<Double> totalincome(UserIncome a,@PathVariable int id)
	{
	  System.out.println("int the list income"+id);
	  return new ResponseEntity<>(dao.getincomemon(u1.getId(),id),HttpStatus.OK);
	}
	
	@GetMapping("/expenseabc/{id}")
	public ResponseEntity<Double> totalexpenseyr(UserExpense u,@PathVariable int id)
	{
		System.out.println("user id"+u1.getId());
		System.out.println("coming id"+id);
		return new ResponseEntity<>(dao.getexpenseyear(u1.getId(),id),HttpStatus.OK);
	}
	    
	@GetMapping("/yearabc/{id}")
	public ResponseEntity<Double> totalincomeyr(UserIncome u,@PathVariable int id)
	{
	   System.out.println("int the list income"+id);
	   return new ResponseEntity<>(dao.getincomeyear(u1.getId(),id),HttpStatus.OK);
	}
	    
	@GetMapping("/logout")
    public ResponseEntity<?> logout()
	{
	    u1=null;
	    return new ResponseEntity<>("logout sussesful",HttpStatus.OK);
	}
	    
	@GetMapping("/admin")
	public ResponseEntity<?> alluser()
	{
     	return new ResponseEntity<>(dao.totaluser("user"),HttpStatus.OK);
    }
	    
	@GetMapping("/userDetails")
	public ResponseEntity<List<UserPojos>> findAllUser()
	{
	  System.out.println("int the list controller");
	  System.out.println(u1.getId());
	  return new ResponseEntity<>(dao.getlistuser("user"),HttpStatus.OK);
	}
	    
	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<String> deletuser(@PathVariable int id)
	{
	  System.out.println(" in delete income"+id);
	  try
		{
		 return new ResponseEntity<>(dao.deleteuser(id),HttpStatus.OK);
		}
		catch (RuntimeException e) 
		{
		  return new ResponseEntity<>("income deletion failed "+e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
     @GetMapping("/adminsetting")
	 public ResponseEntity<UserPojos> getadminsetting()
	 {
		System.out.println(" in the setting controller");
	    System.out.println("user id"+u1);
	    return new ResponseEntity<>(dao.ValidateUser(u1.getEmail(),u1.getPassword()),HttpStatus.OK);
	 }
		   
	@PutMapping("/adminsetting/{id}")
	public ResponseEntity<?> updateadmin(@PathVariable int id,@RequestBody UserPojos update)
	{
	 System.out.println("in the income ctor"+id+" "+update);
     UserPojos abc = dao.setting(u1);
	        
		if(abc==null)
		  return new ResponseEntity<String>("Invalid Stock ID", HttpStatus.NOT_FOUND);
		  update.setId(id);
		 
		  return new ResponseEntity<UserPojos>(dao.setting(update), HttpStatus.OK);
	 }
			
	@PostMapping("/complaint")
	public ResponseEntity<String> registerComp(@RequestBody UserComplain u,HttpSession hs)
    {
		System.out.println("rest service: reg user "+u);
		try
		  {
		    System.out.println("In User registerComp");
	        u.setU2(u1);
		   	return new ResponseEntity<String>(dao.complain(u),HttpStatus.OK);
		   }
		catch (RuntimeException e)
		   	{
			 System.out.println("err in the reg"+e);
			 return new ResponseEntity<String>("user complain register fail "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
		   	
	}
	
	
	 @GetMapping("/useradmincomp")
	 public ResponseEntity<List<UserComplain>> findallComp()
	{
	   return new ResponseEntity<>(dao.getusercomp(),HttpStatus.OK);
	}
	  
	 @DeleteMapping("/deleteusercomp/{id}")
	 public ResponseEntity<String> deleteComp(@PathVariable int id)
	{
		 System.out.println(" in delete income");
		try
		{
		 return new ResponseEntity<>(dao.deletecomp(id),HttpStatus.OK);
		}
		catch (RuntimeException e) 
		{
		return new ResponseEntity<>("income deletion failed "+e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	  
		
	 
	  @PostMapping("/adminuserregister")
	   	public ResponseEntity<String> registerUserAdmin(@RequestBody UserPojos u,HttpSession hs)
	   	{
	    	System.out.println("rest service: reg user "+u);
	    	try
	    	{
	    	   SimpleMailMessage mesg= new SimpleMailMessage();
	     	   mesg.setTo(u.getEmail());
	     	   mesg.setSubject("U Have register succ.");
	     	   mesg.setText("THanku!");
	     	   sender.send(mesg);
	    	   return new ResponseEntity<>(dao.registerUserAdmin(u),HttpStatus.OK);
	    	}
	    	catch (RuntimeException e)
	    	{
				System.out.println("err in the reg"+e);
				return new ResponseEntity<>("user login failed "+e.getMessage(),HttpStatus.OK);
			}
	   	} 
	  
	  @GetMapping("/mop")
		public ResponseEntity<List<UserAddMop>> findAllmop()
		{
			System.out.println("int the list controller");
		
			System.out.println(u1.getId());
			return new ResponseEntity<>(dao.getallmop(u1.getId()),HttpStatus.OK);
					
		}
	  
	  @PostMapping("/savemop")
	  	public ResponseEntity<String> savemopayment(@RequestBody UserAddMop u,HttpSession hs)
	  	{
	   	System.out.println("rest service: reg user "+u);
	   	try
	   	{
	   		System.out.println("In User savemodeofpayment");
	         u.setU3(u1);
	   		return new ResponseEntity<>(dao.savemop(u),HttpStatus.OK);
	   	}
	   	catch (RuntimeException e)
	   	{
				System.out.println("err in the reg"+e);
				return new ResponseEntity<>("user login failed "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	   	
	  	}
	  
	  @DeleteMapping("/mop/{id}")
		 public ResponseEntity<String> deletemop(@PathVariable int id)
		{
			 System.out.println(" in delete income");
			try
			{
			 return new ResponseEntity<>(dao.deletemop(id),HttpStatus.OK);
			}
			catch (RuntimeException e) 
			{
			return new ResponseEntity<>("mop deletion failed "+e.getMessage(),HttpStatus.NOT_FOUND);
			}
		}
	
	  @GetMapping("/savecatexp")
		public ResponseEntity<List<UserAddExpense>> findAllcat()
		{
			System.out.println("int the list controller");
		
			System.out.println(u1.getId());
			return new ResponseEntity<>(dao.getallcat(u1.getId()),HttpStatus.OK);
					
		}
	  
	  @DeleteMapping("/mopexpense/{id}")
		 public ResponseEntity<String> deletecat(@PathVariable int id)
		{
			 System.out.println(" in delete income");
			try
			{
			 return new ResponseEntity<>(dao.deletecatexp(id),HttpStatus.OK);
			}
			catch (RuntimeException e) 
			{
			return new ResponseEntity<>("mop deletion failed "+e.getMessage(),HttpStatus.NOT_FOUND);
			}
		}
	  
	  @PostMapping("/saveexpensecat")
	  	public ResponseEntity<String> savemocat(@RequestBody UserAddExpense u,HttpSession hs)
	  	{
	   	System.out.println("rest service: reg user "+u);
	   	try
	   	{
	   		System.out.println("In User savemodeofpayment");
	         u.setU4(u1);
	   		return new ResponseEntity<>(dao.categorytype(u),HttpStatus.OK);
	   	}
	   	catch (RuntimeException e)
	   	{
				System.out.println("err in the reg"+e);
				return new ResponseEntity<>("user login failed "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	   	
	  	}
	
	  
	 
}
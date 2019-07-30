package com.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.UserAddExpense;
import com.app.pojos.UserAddMop;
import com.app.pojos.UserComplain;
import com.app.pojos.UserExpense;
import com.app.pojos.UserIncome;
import com.app.pojos.UserPojos;

@Repository
@Transactional
public class UserDaoImpl implements IUserDao {

	@Autowired
	private SessionFactory sf;
	
    public UserDaoImpl() 
	{
		System.out.println("in the ctor of"+getClass().getName());
	}
	@Override
	public UserPojos ValidateUser(String email, String password) 
	{
		String jpql = "select u from UserPojos u where u.email=:email and u.password=:password";
		
	    return sf.getCurrentSession().createQuery(jpql,UserPojos.class)
	    .setParameter("email", email).setParameter("password",password).getSingleResult();
	
	
	}
	@Override
	public String registerUser(UserPojos p) {
		p.setRole("user");
		sf.getCurrentSession().persist(p);
		return "user registration succesful";
	}
	
	@Override
	public String incomeregister(UserExpense expense) 
	{
		sf.getCurrentSession().persist(expense);
		return "expense register succesful";
	}
	@Override
	public List<UserExpense> getalllist(Integer id) 
	{
		String jpql="select c from UserExpense c where c.u1.id=:id";
		List<UserExpense> u = sf.getCurrentSession().createQuery(jpql,UserExpense.class)
				.setParameter("id",id).getResultList();
		System.out.println("all the list "+u);
		return u;
	}
	@Override
	public String deleteexpense(Integer id)
	{
		Session sh = sf.getCurrentSession();
		
	   UserExpense u =sh.get(UserExpense.class, id);
	   if(u!=null)
	   {
		   u.getU1().getExpense().remove(u);
		  
		   u.setU1(null);
		   sh.delete(u);
		   return "expense is deleted"+id;
	   }
	   return "expense is deleted";
	}
	@Override
	public UserExpense updateExpense(UserExpense u) {
		sf.getCurrentSession().update(u);
		return u;
	}
	@Override
	public UserExpense expense(Integer id) {
		
		System.out.println("this is id of"+id);
		
		return sf.getCurrentSession().get(UserExpense.class,id);
		
	}
	@Override
	public String expenseregister(UserIncome income) {
		
		sf.getCurrentSession().persist(income);
		return "income register succesful";
	}
	@Override
	public List<UserIncome> getlistincome(Integer id) {
		
		String jpql="select c from UserIncome c where c.u.id=:id";
		List<UserIncome> u = sf.getCurrentSession().createQuery(jpql,UserIncome.class)
				.setParameter("id",id).getResultList();
		System.out.println("all the list "+u);
		return u;
	}
	
	@Override
	public String deleteincome(Integer id) {
		
		Session sh = sf.getCurrentSession();
		
		   UserIncome ua =sh.get(UserIncome.class, id);
		   if(ua!=null)
		   {
			   ua.getU().getIncome().remove(ua);
			  
			   ua.setU(null);
			   sh.delete(ua);
			   return "expense is deleted"+id;
		   }
		   return "expense is deleted";
	}
	@Override
	public UserIncome updateIncome(UserIncome income) {
		sf.getCurrentSession().update(income);
		return income;
	}
	@Override
	public UserIncome income(Integer id) 
	{
    System.out.println("this is id of"+id);
		
    return sf.getCurrentSession().get(UserIncome .class,id);
	}
	@Override
	public Double getexpense(Integer id) {
		
	  String jpql = "select sum(e.amount) from UserExpense e where e.u1.id=:id";
		return sf.getCurrentSession().createQuery(jpql,Double.class)
				.setParameter("id", id).getSingleResult();
	}
	@Override
	public Double getincome(Integer id) {
		  String jpql = "select sum(e.amount) from UserIncome e where e.u.id=:id";
			return sf.getCurrentSession().createQuery(jpql,Double.class)
					.setParameter("id", id).getSingleResult();
	}
	@Override
	public Double getexpensemon(Integer id,Integer ab) {
		String jpql = "select sum(e.amount) from UserExpense e where e.u1.id=:id and month(e.date)=:ab";
		return sf.getCurrentSession().createQuery(jpql,Double.class)
				.setParameter("id", id).setParameter("ab",ab).getSingleResult();
	}
	@Override
	public Double getincomemon(Integer id,Integer ab) {
		String jpql = "select sum(e.amount) from UserIncome e where e.u.id=:id and month(e.date)=:ab";
		return sf.getCurrentSession().createQuery(jpql,Double.class)
				.setParameter("id", id).setParameter("ab",ab).getSingleResult();
	}

	@Override
	public UserPojos setting(UserPojos update) {
		sf.getCurrentSession().update(update);
		return update;
	}
	@Override
	public UserPojos formfillsetting(Integer id) {
		
		 System.out.println("this is id of"+id);
			
		 return sf.getCurrentSession().get(UserPojos.class,id);
	}
	@Override
	public Double getexpenseyear(Integer id,Integer ab) {
		System.out.println("in id"+id+"in ab"+ab);
		String jpql = "select sum(e.amount) from UserExpense e where e.u1.id=:id and year(e.date)=:ab";
		return sf.getCurrentSession().createQuery(jpql,Double.class)
				.setParameter("id", id).setParameter("ab",ab).getSingleResult();
	}
	@Override
	public Double getincomeyear(Integer id,Integer ab) {
		String jpql = "select sum(e.amount) from UserIncome e where e.u.id=:id and year(e.date)=:ab";
		return sf.getCurrentSession().createQuery(jpql,Double.class)
				.setParameter("id", id).setParameter("ab",ab).getSingleResult();
	}
	@Override
	public int totaluser(String ab) {
		System.out.println(" in ab"+ab);
	    String jpql ="select e from UserPojos e where e.role=:ab";
	  // System.out.println(sf.getCurrentSession().createQuery(jpql,UserPojos.class).setParameter("ab",ab)
			//   .getSingleResult());
	    
	  List<UserPojos> u= sf.getCurrentSession().createQuery(jpql,UserPojos.class).setParameter("ab",ab)
			   .getResultList();
	  System.out.println("Raman "+u.size());
	  return u.size();
	}
	@Override
	public List<UserPojos> getlistuser(String ab) {
		String jpql="select c from UserPojos c where c.role=:ab";
		List<UserPojos> u = sf.getCurrentSession().createQuery(jpql,UserPojos.class)
				.setParameter("ab",ab).getResultList();
		System.out.println("all the list "+u);
        return u;
	}
	@Override
	public String deleteuser(Integer id) 
	{
		Session sh = sf.getCurrentSession();
		
		   UserPojos u =sh.get(UserPojos.class, id);
		   if(u!=null)
		   {
			   sh.delete(u);
			   return "user is deleted"+id;
		   }
		   return "user is deleted";
	}
	@Override
	public UserPojos formAdminsetting(Integer id) {
		 System.out.println("this is id of"+id);
			
		 return sf.getCurrentSession().get(UserPojos.class,id);
	}
	@Override
	public UserPojos adminsetting(UserPojos update) {
		sf.getCurrentSession().update(update);
		return update;
	}
	
	@Override
	public String complain(UserComplain compl) {
		sf.getCurrentSession().persist(compl);
		return "compl register succesful";
	}
	@Override
	public List<UserComplain> getusercomp() 
	{
		String jpql="select u.compid,u2.email,u2.fname,u.complain from usercomp u inner join user u2 on u.id=u2.id order by u.compid desc";
		List<UserComplain> u = sf.getCurrentSession().createSQLQuery(jpql).getResultList();
		System.out.println("all the list "+u);
		return u;
	}
	@Override
	public String deletecomp(Integer id) {
		
		Session sh = sf.getCurrentSession();
		
		   UserComplain ua =sh.get(UserComplain.class, id);
		   if(ua!=null)
		   {
			   ua.getU2().getComp().remove(ua);
		       ua.setU2(null);
			   sh.delete(ua);
			   return "comp is deleted"+id;
		   }
		   return "comp is deleted";
	}
	@Override
	public String registerUserAdmin(UserPojos p) {
		p.setRole("user");
		sf.getCurrentSession().persist(p);
		return "user admin registration succesful";
	}
	@Override
	public List<UserAddMop> getallmop(Integer id) {
		String jpql="select c from UserAddMop c where c.u3.id=:id";
		List<UserAddMop> u = sf.getCurrentSession().createQuery(jpql,UserAddMop.class)
				.setParameter("id",id).getResultList();
		System.out.println("all the list "+u);
		return u;
	}
	@Override
	public String savemop(UserAddMop mop) {
		sf.getCurrentSession().persist(mop);
		return "income register succesful";
	}
	@Override
	public String deletemop(Integer id) {
		Session sh = sf.getCurrentSession();
		
		   UserAddMop ua =sh.get(UserAddMop.class, id);
		   if(ua!=null)
		   {
			    ua.getU3().getComp().remove(ua);
		        ua.setU3(null);
			   //sh.delete(ua);
			   return "comp is deleted"+id;
		   }
		   return "comp is deleted";
	}
	@Override
	public String categorytype(UserAddExpense expense) {
		sf.getCurrentSession().persist(expense);
		return "income register succesful";
	}
	@Override
	public String deletecatexp(Integer id) {
		Session sh = sf.getCurrentSession();
		
		   UserAddExpense ua =sh.get(UserAddExpense.class, id);
		   if(ua!=null)
		   {
			    ua.getU4().getComp().remove(ua);
		        ua.setU4(null);
			   //sh.delete(ua);
			   return "comp is deleted"+id;
		   }
		   return "comp is deleted";
	}
	@Override
	public List<UserAddExpense> getallcat(Integer id) {
		
		String jpql="select c from UserAddExpense c where c.u4.id=:id";
		List<UserAddExpense> u = sf.getCurrentSession().createQuery(jpql,UserAddExpense.class)
				.setParameter("id",id).getResultList();
		System.out.println("all the list "+u);
		return u;
	}
	
	
	
	
	
	
	
	
}

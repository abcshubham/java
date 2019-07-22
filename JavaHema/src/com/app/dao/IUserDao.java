package com.app.dao;

import java.util.List;
import com.app.pojos.UserExpense;
import com.app.pojos.UserIncome;
import com.app.pojos.UserPojos;

public interface IUserDao 
{
//login
UserPojos ValidateUser(String email,String password);
//register
String registerUser(UserPojos p);
//expense register
String incomeregister(UserExpense expense);
//get list of expense
List<UserExpense> getalllist(Integer id);
//delete expense
String deleteexpense(Integer id);
//update expense
UserExpense updateExpense(UserExpense u);
//update expense form fill
UserExpense expense(Integer id);
//update expense by month
Double getexpensemon(Integer id);
//get allexpense
Double getexpense(Integer id);
//income register
String expenseregister(UserIncome income);
//get list income
List<UserIncome> getlistincome(Integer id);
//delete income
String deleteincome(Integer id);
//update income
UserIncome updateIncome(UserIncome income);
//update expense form fill
UserIncome income(Integer id);
//get allincome
Double getincome(Integer id);
// update income by month
Double getincomemon(Integer id);
// setting fill
UserPojos formfillsetting(Integer id);
//update setting
UserPojos setting(UserPojos update);

}

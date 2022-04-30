<%@page import="com.webCustomerTracker.springdemo.constants.SortUtils"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>WCT: LIST CUSTOMERS</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css?version=10"/>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	

	<div id="container">
		<div id="content">
			
			<input type="button" value="Add Customer" 
				onclick="location.href='showFormForAdd'"
				class="add-button"
			/>
			
			<form:form action="search" method="GET">
				Search Customer: <input type="text" name="searchName"/>
				<input type="submit" value="search" class="add-button"/>
			</form:form>
			
			<c:url var="sortLinkFirstName" value="/customer/list">
				<c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>"/>			
			</c:url>
			
			<c:url var="sortLinkLastName" value="/customer/list">
				<c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>"/>			
			</c:url>
			
			<c:url var="sortLinkEmail" value="/customer/list">
				<c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>"/>			 
			</c:url>
			
			<table>
				<tr>
					<th><a class="whiteColor" href="${sortLinkFirstName}">First name</a></th>
					<th><a class="whiteColor" href="${sortLinkLastName}">Last name</a></th>
					<th><a class="whiteColor" href="${sortLinkEmail}">Email</a></th>
					<th>Actions</th>		
				</tr>
				<c:forEach var="customer" items="${customers}" >
					
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${customer.id}"/>
					</c:url>
					
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${customer.id}"/>
					</c:url>
					
					<tr>
						<td>${customer.firstName}</td>
						<td>${customer.lastName}</td>
						<td>${customer.email}</td>
						<td><a href="${updateLink}">Update</a> 
						| 
						<a href="${deleteLink}" 
						onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a></td>
					</tr>
				</c:forEach>
				
			</table>
		</div>
	</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
  <title>Contact</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js" integrity="sha384-ODmDIVzN+pFdexxHEHFBQH3/9/vQ9uori45z4JjnFsRydbmQbmL5t1tQ0culUzyK" crossorigin="anonymous"></script>

<!-- Nav Bar -->

<nav class="navbar" style="background-color: #e3f2fd">
  <div class="container-fluid" >
    <a class="navbar-brand">Welcome ${sessionScope.get("userSession")}</a>

    <div style="display: flex; justify-content: space-between;">
      <form action="/user-contacts">
        <button class="btn btn-outline-secondary" type="submit" style="margin-right: 5px;">Home</button>
      </form>
      <form action="/user-contacts/logout">
        <button class="btn btn-outline-danger" type="submit">LogOut</button>
      </form>

    </div>
  </div>
</nav>

<p style="color: green; text-align: center; font-size: 18px; margin-top: 15px">${unBlockOk}</p>



  <h4 style="margin-left: 15px; text-align: center">Your Black List</h4>


<!-- Table Contact -->

<table class="table table-striped-columns"
       style="margin-top:20px ;" >
  <thead>
  <tr style="text-align: center">
    <th scope="col">Name</th>
    <th scope="col">Phone</th>
    <th scope="col">Home Phone</th>
    <th scope="col">Work Phone</th>
    <th scope="col">Tags</th>
    <th scope="col">Group</th>
    <th scope="col">Options</th>
  </tr>
  </thead>
  <tbody>

  <c:forEach var="contact" items="${contactList}">

    <tr style="text-align: center">
      <c:if test="${contact.isBlock() == true}">
        <td>${contact.contactName}</td>
        <td>${contact.phone}</td>
        <td>${contact.homePhone}</td>
        <td>${contact.workPhone}</td>
        <td>${contact.tags}</td>
        <td>${contact.groupName}</td>
        <td style="display: flex; justify-content: space-evenly;">

          <a href="${pageContext.request.contextPath}/user-contacts/delete-contact?id=${contact.contactId}">
            <button type="button" class="btn btn-danger">Delete</button></a>

          <a href="${pageContext.request.contextPath}/user-contacts/unblock-contact?id=${contact.contactId}">
            <button type="button" class="btn btn-light">UnBlock</button></a>

        </td>
      </c:if>
    </tr>
  </c:forEach>

  </tbody>
</table>
</body>
</html>
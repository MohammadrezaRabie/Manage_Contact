<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Contact</title>
</head>
<body>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" href="../New folder/tst_style.css">
    <title>Document</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js" integrity="sha384-ODmDIVzN+pFdexxHEHFBQH3/9/vQ9uori45z4JjnFsRydbmQbmL5t1tQ0culUzyK" crossorigin="anonymous"></script>

<style>
    <%@ include file="css/edit_contact_style.css"%>
</style>

<div class="login-page">
    <div class="form">

        <p style="color: red; text-align: center; font-size: 18px; margin-top: 15px">${errorContactExist}</p>
        <p style="color: red; text-align: center; font-size: 18px; margin-top: 15px">${errorTags}</p>
        <p style="color: red; text-align: center; font-size: 18px; margin-top: 15px">${errorFieldNameNull}</p>
        <p style="color: red; text-align: center; font-size: 18px; margin-top: 15px">${errorPhoneNumber}</p>


        <c:if test="${not empty errors}">
            <c:forEach items="${errors}" var="err">

                <p style="color: red; text-align: center; font-size: 18px; margin-top: 15px">${err.defaultMessage}</p>

            </c:forEach>
        </c:if>

        <h3>Edit Contact</h3><br>
        <form class="login-form" action="/user-contacts/update-contact" method="POST">


            <input type="hidden" name="contactId" value="${cont.contactId}"><br>

            <label for="name">Name</label><br>
            <input id="name" type="text" name="contactName" value="${cont.contactName}"><br>


            <label for="phone">Phone</label><br>
            <input id="phone" type="text" name="phone" value="${cont.phone}" ><br>


            <label for="home_phone">Home Phone</label><br>
            <input id="home_phone" type="text" name="homePhone" value="${cont.homePhone}"><br>

            <label for="work_phone">Work Phone</label><br>
            <input id="work_phone" type="text" name="workPhone" value="${cont.workPhone}"><br>

            <label for="tag">Tags</label><br>
            <input id="tag" type="text" name="tags" value="${cont.tags}"><br>

            <select name="groupName" style="margin-top: 20px; width: 200px; margin-bottom: 20px" class="form-select" aria-label="Default select example">
                <option value="${cont.groupName}" selected>${cont.groupName}</option>
                <option value="Contacts">Contacts</option>
                <option value="Friends" >Friends</option>
                <option value="Co_Workers" >Co Workers</option>
            </select><br>


            <button type="submit" style="margin-bottom: 15px;">Save</button>

        </form>

        <form action="/user-contacts">
            <button class="mybutton">Cancel</button>
        </form>
    </div>
</div>
</body>
</html>
</body>
</html>

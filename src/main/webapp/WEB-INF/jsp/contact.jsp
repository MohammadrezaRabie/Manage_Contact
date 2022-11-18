<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <title>Contact</title>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"
        integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js"
        integrity="sha384-ODmDIVzN+pFdexxHEHFBQH3/9/vQ9uori45z4JjnFsRydbmQbmL5t1tQ0culUzyK"
        crossorigin="anonymous"></script>

<!-- Nav Bar -->

<nav class="navbar" style="background-color: #e3f2fd">
    <div class="container-fluid">
        <a class="navbar-brand">Welcome ${sessionScope.get("userSession")}</a>


        <form action="${pageContext.request.contextPath}
        /user-contacts/search-contacts?search=${pageContext.request.getParameter("search")}"
              method="get" class="d-flex" role="search">
            <input class="form-control me-2" name="search" type="search" placeholder="Search Contact"
                   aria-label="Search" style="margin:5px">
            <button class="btn btn-outline-success" type="submit" style="margin:5px">Search</button>
        </form>


        <div style="display: flex; justify-content: space-between;">
            <form action="/user-contacts/show-black-list">
                <button class="btn btn-outline-dark" type="submit" style="margin-right: 5px;">Black List</button>
            </form>
            <form action="/user-contacts/logout">
                <button class="btn btn-outline-danger" type="submit">LogOut</button>
            </form>


        </div>
    </div>
</nav>

<%--Messages--%>

<p style="color: green; text-align: center; font-size: 18px; margin-top: 15px">${addContactOk}</p>
<p style="color: green; text-align: center; font-size: 18px; margin-top: 15px">${blockOk}</p>
<p style="color: green; text-align: center; font-size: 18px; margin-top: 15px">${deleteOk}</p>
<p style="color: green; text-align: center; font-size: 18px; margin-top: 15px">${updateOk}</p>
<p style="color: red; text-align: center; font-size: 18px; margin-top: 15px">${errorContactExist}</p>
<p style="color: red; text-align: center; font-size: 18px; margin-top: 15px">${errorTags}</p>
<p style="color: red; text-align: center; font-size: 18px; margin-top: 15px">${errorFieldNameNull}</p>
<p style="color: red; text-align: center; font-size: 18px; margin-top: 15px">${errorPhoneNumber}</p>

<%--Errors--%>

<c:if test="${not empty errors}">
    <c:forEach items="${errors}" var="err">

        <p style="color: red; text-align: center; font-size: 18px; margin-top: 15px">${err.defaultMessage}</p>

    </c:forEach>
</c:if>

<div style="display: flex ; justify-self: center; justify-content: space-between; margin-top: 20px;">
    <h4 style="margin-left: 15px;">Your Contact</h4>
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"
            style="margin-right: 15px;">
        Add Contact
    </button>
</div>


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Add Contact</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                <form action="/user-contacts/add-contacts" method="post" onsubmit="return validateForm()">

                    <label for="name">Name</label><br>
                    <input id="name" type="text" name="contactName"><br>
                    <div id='errorDivName' class='col-xs-12 pull-right' style="color: red"></div>

                    <label for="phone">Phone</label><br>
                    <input id="phone" type="text" name="phone"><br>
                    <div id='errorDivPhone' class='col-xs-12 pull-right' style="color: red"></div>

                    <label for="home_phone">Home Phone</label><br>
                    <input id="home_phone" type="text" name="homePhone"><br>

                    <label for="work_phone">Work Phone</label><br>
                    <input id="work_phone" type="text" name="workPhone"><br>

                    <label for="tag">Tags</label><br>
                    <input id="tag" type="text" name="tags"><br>

                    <select name="groupName" style="margin-top: 20px; width: 200px; margin-bottom: 20px"
                            class="form-select" aria-label="Default select example">
                        <option selected value="Contacts">Contacts</option>
                        <option value="Friends">Friends</option>
                        <option value="Co_Workers">Co Workers</option>
                    </select>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Add Contact</button>
                    </div>


                </form>
            </div>

        </div>
    </div>
</div>

<!-- Table Contact -->

<table class="table table-striped-columns"
       style="margin-top:20px ;">
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
                <c:if test="${contact.isBlock() == false}">
                    <td>${contact.contactName}</td>
                    <td>${contact.phone}</td>
                    <td>${contact.homePhone}</td>
                    <td>${contact.workPhone}</td>
                    <td>${contact.tags}</td>
                    <td>${contact.groupName}</td>
                    <td style="display: flex; justify-content: space-evenly;">

                        <a href="${pageContext.request.contextPath}/user-contacts/edit?id=${contact.contactId}">
                            <button type="button" class="btn btn-secondary">Edit</button>
                        </a>

                        <a href="${pageContext.request.contextPath}/user-contacts/delete-contact?id=${contact.contactId}">
                            <button type="button" class="btn btn-danger">Delete</button>
                        </a>

                        <a href="${pageContext.request.contextPath}/user-contacts/block-contact?id=${contact.contactId}">
                            <button type="button" class="btn btn-light">Block</button>
                        </a>
                    </td>
            </tr>
        </c:if>


    </c:forEach>

    </tbody>
</table>


<div style="text-align: center; margin-top: 20px;">
    Total Contacts : ${totalItems} - Page : ${currentPage} of ${totalPages}
</div>

<nav aria-label="Page navigation example" style="display: flex; justify-content: center; margin-top: 30px">
    <ul class="pagination justify-content-end">

        <c:if test="${currentPage > 1}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/user-contacts/contact-list?page=${currentPage - 1}"
                   tabindex="-1">Previous</a>
            </li>
        </c:if>

        <c:forEach var="i" begin="1" end="${totalPages}">

            <li id="li" class="page-item"><a class="page-link"
                                             href="${pageContext.request.contextPath}/user-contacts/contact-list?page=${i}">${i}</a>
            </li>

        </c:forEach>


        <c:if test="${currentPage < totalPages}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/user-contacts/contact-list?page=${currentPage + 1}">Next</a>
            </li>
        </c:if>

    </ul>
</nav>
</body>
</html>
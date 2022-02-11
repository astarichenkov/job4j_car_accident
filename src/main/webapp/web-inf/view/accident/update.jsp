<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
          crossorigin="anonymous"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

  <title>Accident</title>
</head>

<body>
<div class="container pt-3">

  <a href="<c:url value='/create'/>">Добавить инцидент</a>

  <div class="card">
    <div class="card-body">
      <table class="table table-hover" id="table">
        <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Имя</th>
          <th scope="col">Описание</th>
          <th scope="col">Адрес</th>
        </tr>
        </thead>
        <tbody>
        <jsp:useBean id="accidents" scope="request" type="java.util.List"/>
        <c:forEach var="accident" items="${accidents}">
          <tr>
            <th scope="row"><c:out value="${accident.id}"/></th>
            <td><c:out value="${accident.name}"/></td>
            <td><c:out value="${accident.text}"/></td>
            <td><c:out value="${accident.address}"/></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>
</body>
</html>
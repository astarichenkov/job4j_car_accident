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
  <form action="<c:url value='/save?id=${accident.id}'/>" method='POST'>
    <div class="mb-3">
      <label for="name" class="form-label">Имя</label>
      <input type="text" class="form-control" name="name" id="name" value="<c:out value="${accident.name}"/>">
    </div>
    <div class="mb-3">
      <label for="address" class="form-label">Адрес</label>
      <input type="text" class="form-control" name="address" id="address" value="<c:out value="${accident.address}"/>">
    </div>
    <div class="mb-3">
      <label for="text" class="form-label">Описание</label>
      <textarea class="form-control" name="text" id="text" rows="3"><c:out value="${accident.text}"/></textarea>
    </div>
    <button type="submit" class="btn btn-primary">Сохранить</button>
  </form>
</div>
</body>
</html>
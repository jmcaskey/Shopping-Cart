<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title>Admin Edit</title>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta name="robots" content="noindex, nofollow">
  <meta name="googlebot" content="noindex, nofollow">
  <meta name="viewport" content="width=device-width, initial-scale=1">


      <link rel="stylesheet" type="text/css" href="//stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
      <script type="text/javascript" src="//stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
      <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
      <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular-resource.min.js"></script>
      <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular-route.min.js"></script>
  <style type="text/css">
    body {
  background: #f9fafb;
}

.row {
  background: #f8f9fa;
  margin-top: 25px;
  margin-bottom: 20px;
}

td {
  padding: 10px;
}

.items {
  border: solid 1px #6c757d;
}

.actions {
  margin-top: 10px;
}

.col {
  padding: 10px;
}

.quantity {
  width: 40px;
}

  </style>

</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <img src="https://png.icons8.com/ios-glyphs/80/000000/small-business.png">
      <p class="navbar-brand">Welcome, Admin </p>
    </div>
    <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    <ul class="nav navbar-nav navbar-right">
      <li><a onclick="document.forms['logoutForm'].submit()"><span class="glyphicon-log-in"></span> Logout</a></li>
    </ul>
  </div>
</nav>
<div ng-app="myApp" ng-init="item={}" ng-controller="myCtrl">
  <div class="container">
    <div class="row header">
      <div class="col">
        <h1>
          Edit Items:
        </h1>
        <hr />
      </div>
    </div>
    <div class="row">
      <div class="col-sm-12">
        <table>
          <tr>
            <td>
              Name:</td>
            <td>
              <input type="text" ng-model="item.name"><br>
            </td>
          </tr>
          <tr>
            <td>
              Description:</td>
            <td>
              <input type="text" ng-model="item.description"><br>
            </td>
          </tr>
          <tr>
            <td>
              Price:</td>
            <td>
              <input type="text" ng-model="item.price"><br>
            </td>
          </tr>
          <tr>
            <td>
              Image source:</td>
            <td>
              <input type="text" ng-model="item.image"><br>
            </td>
          </tr>
          <tr>
            <td></td>
            <td>
              <button ng-click="submit()">Submit</button>
            </td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
  var app = angular.module('myApp', []);
  app.controller('myCtrl', function($scope, $location, $http) {
        $http.get("/item?id=" + $location.absUrl().substring($location.absUrl().indexOf("?") + 1))
          .then(function(response) {
            $scope.item = response.data;
            console.log(response.data);
          });

        $scope.submit = function() {
        	$scope.item.price = +$scope.item.price;
          $http.post("/item/edit?id=" + ($location.absUrl().substring($location.absUrl().indexOf("?") + 1)), $scope.item)
            .then(function(response) {
            	window.location.replace($location.absUrl().substring(0, $location.absUrl().indexOf("/edit")) + "/home.jsp");
            });
          }
        });

</script>
</body>
</html>

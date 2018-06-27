<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title></title>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <meta name="robots" content="noindex, nofollow">
  <meta name="googlebot" content="noindex, nofollow">
  <meta name="viewport" content="width=device-width, initial-scale=1">


  <script
    type="text/javascript"
    src="/js/lib/dummy.js"
    
  ></script>


      <link rel="stylesheet" type="text/css" href="//stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
      <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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

.storeitems {
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
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Confirmation</h4>
        </div>
        <div class="modal-body">
          <p id="text">Error in executing order submission.</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <img src="https://png.icons8.com/ios-glyphs/80/000000/small-business.png">
      <p class="navbar-brand">Welcome, Shopper </p>
    </div>
    <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    <ul class="nav navbar-nav navbar-right">
      <li><a onclick="document.forms['logoutForm'].submit()"><span class="glyphicon-log-in"></span> Logout</a></li>
    </ul>
  </div>
</nav>
<div ng-app="myApp" ng-init="items=[]" ng-controller="myCtrl">
  <div class="container">
  
    <div class="row header">
      <div class="col">
        <h1>
          Browse
        </h1>
        <hr />
      </div>
    </div>
    <div class="row">
      <div class="col-sm-10">
        <div class="row storeitems" ng-repeat="item in items">
          <div class="col-sm-2">
            <image src="{{item.image}}"></image>
          </div>
          <div class="col-sm-2">
            {{item.name}}
          </div>
          <div class="col-sm-4">
            {{item.description}}
          </div>
          <div class="col-sm-2">
            {{item.price}}
          </div>
        </div>
      </div>
      <div class="col-sm-2">
      </div>
    </div>
    <div class="row">
      <form>
      Name:
      <input type="text" ng-model="p.name"><br />
      Email Address:
      <input type="text" ng-model="p.email"><br />
      Address:
      <input type="text" ng-model="p.address"><br />
      <button type="button"  ng-click="order()" data-toggle="modal" data-target="#myModal">Order</button>
      </form>
    </div>
  </div>
  
</div>

<script type="text/javascript">

  var app = angular.module('myApp', []);
  app.controller('myCtrl', function($scope, $http) {
	$scope.p = {"name":"name", "email": "sample@email.com", "address":"sample address St."}
    $http.get("/order")
    .then(function(response) {
    	$scope.items = response.data.items;
    });

    $scope.order = function() {
      $http.post("/order/confirm", $scope.p)
      .then(function(response) {
    	  if (response.data !== "customer/checkout.jsp?error") {
    		  document.getElementById("text").innerText = response.data;
    	  } else {
    		  document.getElementById("text").innerText = "Error submitting order";
    	  }
      });
    }
  });

</script>
</body>
</html>

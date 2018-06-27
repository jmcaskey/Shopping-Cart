
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title>Customer Home</title>
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
          <div class="col-sm-2 actions">
            <input type="text" value="1" class="quantity" ng-model="item.quantity">
            <button ng-click="addToCart(item)">
         Order
         </button>
          </div>
        </div>
      </div>
      <div class="col-sm-2">
        <a href="/customer/checkout.jsp">
      <img src="https://png.icons8.com/dotty/80/000000/shopping-cart.png">
      Checkout
      </a>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
  var app = angular.module('myApp', []);
  app.controller('myCtrl', function($scope, $location, $http) {
    $http.get("/item/list")
    .then(function(response) {
    	$scope.items = response.data;
    });

    $scope.addToCart = function(item) {
      if (isNaN(item.quantity)) {
    	  alert("Error: Quantity must be numeric.")
      } else {
      $http.post("/order/add", item )
      .then(function(response) {
      	window.location.reload();
      });
      }
    }
  });

</script>
</body>
</html>

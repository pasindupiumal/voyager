<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored ="false" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">

    <title>View Routes</title>
    <meta charset="UTF-8">
    <style type="text/css">
        #insertBox {

            width: 90%;
            margin-left: auto;
            margin-right: auto;
            margin-top: 5%;
        }

        header {

            font-size: 18px;
        }
    </style>
    
    <script type="text/javascript">
    
    	function checkIt(){
    		
    		var selection = confirm("Are you sure you want to delete this route?");
    		
    		if (selection == true) {
    		  
    			form.submit();
    		} 
    		else{
    			return false;
    		}
    	}
    	
    </script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</head>

<header>

    <nav class="navbar navbar-expand-lg navbar-light bg-primary">
        <a style="font-size: 24px; color:white; font-weight: bold" class="navbar-brand" href="#">VOYAGER</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent ">
            <ul class="navbar-nav mr-auto ">
                <li class="nav-item dropdown">
                    <a style="color:white; font-weight: bold" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Route Management
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/voyager/Routes/AddNewRoute">Add New Route</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/voyager/Routes/ViewAllRoutes">View All Routes</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a style="color:white; font-weight: bold" class="nav-link" href="/voyager/Fees/ViewAllRoutes">Fee Management</a>
                </li>

                <li class="nav-item dropdown">
                    <a style="color:white; font-weight: bold" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        User Management
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">Add New User</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">View All Users</a>
                    </div>
                </li>

            </ul>
            <form class="form-inline my-2 my-lg-0" style="color: black">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button style="color: white; font-weight: bold" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                &nbsp;&nbsp;&nbsp;

                <ul class="navbar-nav mr-auto ">
                    <li class="nav-item dropdown">
                        <a style="color:white; font-weight: bold" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            My Account
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">View Profile</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Settings</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Sign Out</a>
                        </div>
                    </li>
                </ul>

                &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
            </form>
        </div>
    </nav>
</header>

<body>

    <div id="insertBox">

        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Route ID</th>
                    <th scope="col">Route Name</th>
                    <th scope="col">Route Number</th>
                    <th scope="col">Total Distance (KM)</th>
                    <th scope="col">Halt ID</th>
                    <th scope="col">Halt Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
            	
            	<c:forEach var="route" items="${routeList}">
            		<tr>
            			<td> <c:out value="${route.routeID}"/></td>
	                	<td> <c:out value="${route.routeName}"/></td>
	                	<td> <c:out value="${route.routeNumber}"/></td>
	                	<td> <c:out value="${route.totalDistance}"/></td>
	                	<td>
	                		<c:forEach var="halt" items="${route.haltList}">
	                			<p> <c:out value="${halt.haltID}"/> </p>
	                		</c:forEach>
	                	</td>
	                	<td>
	                		<c:forEach var="halt" items="${route.haltList}">
	                			<p> <c:out value="${halt.haltName}"/> </p>
	                		</c:forEach>
	                	</td>
	                	<td>
	                		<c:forEach var="halt" items="${route.haltList}">
	                			<p> <c:out value="${halt.price}"/> </p>
	                		</c:forEach>
	                	</td>
	                	<td>
	                		<c:forEach var="halt" items="${route.haltList}">
	                			<p>			
		                			<a href="/voyager/Routes/UpdateRoute?id=<c:out value='${halt.haltID}'/>"> Update </a> 
			                		&nbsp;&nbsp;&nbsp; &nbsp;
			                		<a style="color:red" href="/voyager/Routes/DeleteRoute?id=<c:out value='${halt.haltID}'/>" onClick="return checkIt();"> Delete </a>
	                			 </p>
	                		</c:forEach>
	                	</td>
            		</tr>
            	</c:forEach>

            </tbody>
        </table>
    </div>



</body>

</html>
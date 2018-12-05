<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
	  
	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
	
		<h1>eZoo <small>Assign Feeding Schedule</small></h1>
		<hr class="paw-primary">
		<form action="assignFeedingSchedule" method="post">
			<div class="form-group">
				<input type="hidden" name="animalToAssign" value="${animalToAssign}">
				<select class="custom-select" name="selected_schedule">
      				<option>Select a Feeding Schedule to Assign</option>
      				<c:forEach var="feedingSchedule" items="${feedingSchedules}">
      					<option>${feedingSchedule.getFeedingScheduleID()}</option>
      				</c:forEach>     							
    			</select>
    			<div>
		      		<button type="submit" name="assignBtn"
		  
		      			class="btn btn-primary">Assign</button>
		    	</div>
		    	<div>
		      		<button type="button" name="backBtn"
		      			 
		      			class="btn btn-info">Back</button>
		    	</div>
		  	</div>
		  </form>
	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />
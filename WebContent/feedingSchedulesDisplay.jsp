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
 
		<h1>eZoo <small>Feeding Schedules</small></h1>
		<hr class="paw-primary">
		<table class="table table-striped table-hover table-responsive ezoo-datatable">
			<thead>
				<tr>				
					<th class="text-center">Feeding Schedule ID</th>
					<th class="text-center">Feeding Time</th>
					<th class="text-center">Recurrence</th>
					<th class="text-center">Food</th>
					<th class="text-center">Notes</th>
					<th class="text-center">Animals</th>
					<th class="text-center"></th>
				</tr>
			</thead>
			<tbody>
			<!-- Need to capture the table values somehow -->
				<c:forEach var="feedingSchedule" items="${feedingSchedules}">
					<tr>
						
						<td><c:out value="${feedingSchedule.getFeedingScheduleID()}" /></td>
						
						<td><c:out value="${feedingSchedule.getFeedingTime()}" /></td>
						<td><c:out value="${feedingSchedule.getRecurrence()}" /></td>
						<td><c:out value="${feedingSchedule.getFood()}" /></td>
						<td><c:out value="${feedingSchedule.getNotes()}" /></td>
						<td>
							<select class="custom-select">
      							<option value="0">Animals:</option>
      							
      							<c:forEach var="animalName" items="${feedingSchedule.getAssignedAnimalsByName()}">
      							<option>${animalName}</option>
      							</c:forEach>
    						</select>
                    	</td>
    
                    	<td>
                    	<!-- Use button values to decide what to do in servlet -->
                    	<form action="feedingSchedulesDisplay" method="post">
							<div class="form-group">
								<input type="hidden" id="feedingScheduleID" name="feedingScheduleID"
								 value="<c:out value="${feedingSchedule.getFeedingScheduleID()}" />">
		    					<div class="col-sm-1">
		      						<button type="submit" name="updateBtn" 
		      						 value="<c:out value="${feedingSchedule.getFeedingScheduleID()}" />"
		      						 class="btn btn-primary">Update</button>
		    					</div>
		    					<div class="col-sm-offset-4 col-sm-1">
		      						<button type="submit" name="deleteBtn"
		      						 value="<c:out value="${feedingSchedule.getFeedingScheduleID()}" />" 
		      						 class="btn btn-danger">Delete</button>
		    					</div>
		  					</div>
		  				</form>
		  				</td>
					</tr>	
				</c:forEach>
			</tbody>
		</table>	

	  </div>
	</header>

	<!-- Footer -->
	<jsp:include page="footer.jsp" />
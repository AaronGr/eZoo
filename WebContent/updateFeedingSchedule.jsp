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
	
		<h1>eZoo <small>Update Feeding Schedule</small></h1>
		<hr class="paw-primary">
		
		<form action="updateFeedingSchedule" method="post" class="form-horizontal">
		
		  <div class="form-group">
		  	<input type="hidden" class="form-control" id="id" name="id" value="${feedingScheduleToUpdate.getFeedingScheduleID()}"/>
		    <label for="feedingTime" class="col-sm-4 control-label">Feeding Time</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="feedingTime" name="feedingTime" placeholder="Feeding Time"
		       value="${feedingScheduleToUpdate.getFeedingTime()}" required="required" />
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="recurrence" class="col-sm-4 control-label">Recurrence</label>
		    <div class="col-sm-4">
				<input type="text" class="form-control" id="recurrence" name="recurrence" placeholder="Recurrence" 
				 value="${feedingScheduleToUpdate.getRecurrence()}" required="required"/>
			</div>	
		  </div>
		  <div class="form-group">
		    <label for="food" class="col-sm-4 control-label">Food</label>
		    <div class="col-sm-4">
				<input type="text" class="form-control" id="food" name="food" placeholder="Food"
				 value="${feedingScheduleToUpdate.getFood()}" required="required"/>
			</div>
		  </div>
		  <div class="form-group">
		    <label for="notes" class="col-sm-4 control-label">Notes</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="notes" name="notes" placeholder="Notes"
		       value="${feedingScheduleToUpdate.getNotes()}"  required="required"/>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-primary">Update</button>
		    </div>
		    <div class="col-sm-1">
		      <button type="submit" class="btn btn-info">Back</button>
		    </div>
		  </div>
		</form>
	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />
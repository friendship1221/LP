<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<portlet:actionURL name="sendWeatherInfor" var="sendWeatherInforURL">
</portlet:actionURL>

<form action="<%=sendWeatherInforURL%>" method="post" id="sendWeatherInfor">
<input type="submit" value="Send Weather Infor"></input>
</form>

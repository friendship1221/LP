<%@page import="com.vh.model.Shop"%>
<%@page import="com.vh.service.ShopLocalServiceUtil"%>
<%@page import="com.vh.util.VHConstant"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>

<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="/WEB-INF/tld/liferay-theme.tld" prefix="liferay-theme"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<liferay-theme:defineObjects/>
<portlet:defineObjects />

<%
Shop shop = (Shop) GetterUtil.getObject(request.getAttribute("shop"), null);
%>

<c:choose>
	<c:when test="<%=null==shop%>">
		<div>
			<liferay-ui:message key="you-do-not-have-permission-to-access-the-requested-resource"/>
		</div>
	</c:when>
	<c:otherwise>
		<aui:form action="action" method="post" name="name">
			<aui:input type="text" name="shopName" value="<%=shop.getShopName()%>" label="name"></aui:input>
			<aui:input type="text" name="shopAddress" value="<%=shop.getShopAddress() %>" label="address"></aui:input>
			<aui:input type="checkbox" name="allowEdit" checked="<%=shop.getAllowEditing() > 0%>"></aui:input>
			<aui:button type="submit" name="save" value="save"></aui:button>
		</aui:form>
	</c:otherwise>
	<aui:button name="back" value="back" onclick="history.go(-1)" />
</c:choose>

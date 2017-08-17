<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="javax.portlet.ActionRequest"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.vh.service.ShopLocalServiceUtil"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="javax.portlet.RenderResponse"%>
<%@page import="com.vh.util.VHConstant"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ taglib uri="http://liferay.com/tld/security"
	prefix="liferay-security"%>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="/WEB-INF/tld/liferay-portlet-ext.tld"
	prefix="liferay-portlet-ext"%>
<%@ taglib uri="/WEB-INF/tld/liferay-theme.tld" prefix="liferay-theme"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="<%=request.getContextPath()%>/js/jquery.confirm.js" ></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.confirm.css" />

<liferay-theme:defineObjects/>
<portlet:defineObjects />

<%
// get search item content
String shopName = GetterUtil.getString(request.getAttribute("shopName"), StringPool.BLANK);
String shopAddress = GetterUtil.getString(request.getAttribute("shopAddress"), StringPool.BLANK);
int delta = GetterUtil.getInteger(request.getAttribute("delta"), VHConstant.LiferayConst.DELTA_PAGE);


// iterator
PortletURL iteratorShopURL = renderResponse.createActionURL();
//iteratorShopURL.setParameter(arg0, arg1)
iteratorShopURL.setWindowState(LiferayWindowState.NORMAL);
%>

<%-- define --%>
<c:set var="no-results"><liferay-ui:message key="there-are-no-results"/></c:set>

<%-- action --%>
<portlet:actionURL var="searchShopURL" name="searchShop" windowState="<%=LiferayWindowState.NORMAL.toString()%>"/>
<portlet:actionURL var="updateShopURL" name="updateShop"></portlet:actionURL>
<portlet:resourceURL var="checkAllowEditURL">
	<portlet:param name="<%=ActionRequest.ACTION_NAME %>" value="checkAllowEdit"/>
</portlet:resourceURL>

<%-- Form search --%>
<div id="SearchForm">
	<aui:form action="<%=searchShopURL%>" method="post" name="searchShop">
		
	</aui:form>
</div>

<%--  List all shop --%>
<div id="ListShop">
	<liferay-ui:search-container id="ShopContainer" var="searchContainter"
		delta="<%=delta%>" deltaConfigurable="true"  
		iteratorURL="<%=iteratorShopURL%>" emptyResultsMessage="there-are-no-results">
		<liferay-ui:search-container-results
			results="<%=ShopLocalServiceUtil.searchShop(shopName, shopAddress, 
					searchContainter.getStart(), searchContainter.getEnd())%>"
			total="<%=ShopLocalServiceUtil.countShop(shopName, shopAddress) %>">
			</liferay-ui:search-container-results>

		<liferay-ui:search-container-row className="com.vh.model.Shop"
			modelVar="shop" indexVar="shopIndex" escapedModel="true">
			<liferay-ui:search-container-column-text name="STT" cssClass="textAlignCenter">
				<%=String.valueOf(shopIndex + searchContainter.getStart()) %>
			</liferay-ui:search-container-column-text>
			<liferay-ui:search-container-column-text name="first-name">
				<%=shop.getShopName()%>
			</liferay-ui:search-container-column-text>
			<liferay-ui:search-container-column-text name="primary-address">
				<%=shop.getShopAddress()%>
			</liferay-ui:search-container-column-text>
			<liferay-ui:search-container-column-text name="actions">
				<span id="CapNhat" onclick="clickUpdateShop(<%=String.valueOf(shop.getShopId())%>)">
					<liferay-ui:message key="update"/></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator paginate="true"></liferay-ui:search-iterator>
	</liferay-ui:search-container>
</div>

<script type="text/javascript">
function clickUpdateShop(idShop){
	
	$.ajax({
		async: false, //blocks window close
		type: "POST",
		dataType : "json",
		url : "${checkAllowEditURL}",
		data: {"idShop":idShop,}, 
		success : function(result) {
			if(result.isAllowEdit){
				location.href = "${updateShopURL}" 
					+ "&_shopmanagement_WAR_LFMessageBusportlet_idShop=" + idShop;
			}
			else{
				// inform
				$.confirm({
					'title'		: "<liferay-ui:message key='com.vh.capnhatshop'/>",
					'message'	: "<liferay-ui:message key='com.vh.khongthecapnhatshop'/>",
					'buttons'	: {
						'<liferay-ui:message key="ok"/>': {
							'class' : 'blue',
							'action': function() {
								
							}
						}
					}
				});
			}
		}
	});
}// end function clickUpdateShop

</script>

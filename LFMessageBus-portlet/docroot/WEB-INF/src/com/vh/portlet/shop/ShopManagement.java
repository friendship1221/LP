package com.vh.portlet.shop;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.vh.model.Shop;
import com.vh.service.ShopLocalServiceUtil;
//import com.liferay.portal.kernel.messaging.Message;
//import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.vh.util.BaseMVCPortlet;
import com.vh.util.UniParamUtil;
//import com.vh.util.VHConstant;
import com.vh.util.VHConstant;

/**
 * Portlet implementation class ShopManagement
 */
public class ShopManagement extends BaseMVCPortlet {
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) 
			throws IOException, PortletException {
//		Message messageWeather = new Message();
//		Message messageExchangeRate = new Message();
//		
//		// get weather info
//		messageWeather.put(VHConstant.MessageConfig.NAME, VHConstant.MessageConfig.NAME_WEATHER);
//		messageWeather.put(VHConstant.MessageConfig.TYPE, VHConstant.MessageConfig.TYPE_CITY);
//		messageWeather.put(VHConstant.MessageConfig.VALUE, VHConstant.MessageConfig.VALUE_DEFAULT);
//		
//		// get exchange rate info
//		messageExchangeRate.put(VHConstant.MessageConfig.NAME, VHConstant.MessageConfig.NAME_EXCHANGERATE);
//		messageExchangeRate.put(VHConstant.MessageConfig.TYPE, VHConstant.MessageConfig.TYPE_MONEY);
//		
//		MessageBusUtil.sendMessage(VHConstant.MessageConfig.DESTINATION_INFO, messageWeather);
//		MessageBusUtil.sendMessage(VHConstant.MessageConfig.DESTINATION_INFO, messageExchangeRate);
		
		super.doView(renderRequest, renderResponse);
	}
	
	public void searchShop(ActionRequest actReq, ActionResponse actRes){
		// get and
//		actReq.setAttribute(arg0, arg1);
		actRes.setRenderParameter(VHConstant.LiferayConst.JSP_PAGE, VHConstant.JSP_URL.ShopView);
	}
	
	public void updateShop(ActionRequest actReq, ActionResponse actRes){
		long idShop = GetterUtil.getLong(actReq.getParameter("idShop"), 0);
		Shop shop = null;
		_log.info("updateShop" + String.valueOf(idShop));

		try {
			shop = ShopLocalServiceUtil.fetchShop(idShop);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		actReq.setAttribute("shop", shop);
		actRes.setRenderParameter(VHConstant.LiferayConst.JSP_PAGE, VHConstant.JSP_URL.ShopEdit);
	}
	
	public void checkAllowEdit(ResourceRequest resourceReq, ResourceResponse resourceRes){
		boolean isAllowEdit = false;
		long idShop = GetterUtil.getLong(resourceReq.getParameter("idShop"), 0);
		_log.info("checkAllowEdit_Getter" + String.valueOf(idShop));
		if(0 == idShop)
			idShop = UniParamUtil.getLong(resourceReq, "idShop", 0);
		Shop shop = null;
		_log.info("checkAllowEdit_UniPraram" + String.valueOf(idShop));

		try {
			shop = ShopLocalServiceUtil.fetchShop(idShop);
			isAllowEdit = shop.getAllowEditing() > 0;
			_log.info("isAllowEdit" + isAllowEdit);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		// json return 
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();
		jsonObj.put("isAllowEdit", isAllowEdit);
		try {
			writeJSON(resourceReq, resourceRes, jsonObj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static Log _log = LogFactoryUtil.getLog(ShopManagement.class);
}

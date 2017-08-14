package com.vh.portlet.shop;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.vh.util.VHConstant;

/**
 * Portlet implementation class ShopManagement
 */
public class ShopManagement extends MVCPortlet {
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) {
		Message messageWeather = new Message();
		Message messageExchangeRate = new Message();
		
		// get weather info
		messageWeather.put(VHConstant.MessageConfig.NAME, VHConstant.MessageConfig.NAME_WEATHER);
		messageWeather.put(VHConstant.MessageConfig.TYPE, VHConstant.MessageConfig.TYPE_CITY);
		messageWeather.put(VHConstant.MessageConfig.VALUE, VHConstant.MessageConfig.VALUE_DEFAULT);
		
		// get exchange rate info
		messageExchangeRate.put(VHConstant.MessageConfig.NAME, VHConstant.MessageConfig.NAME_EXCHANGERATE);
		messageExchangeRate.put(VHConstant.MessageConfig.TYPE, VHConstant.MessageConfig.TYPE_MONEY);
		
		MessageBusUtil.sendMessage(VHConstant.MessageConfig.DESTINATION_INFO, messageWeather);
		MessageBusUtil.sendMessage(VHConstant.MessageConfig.DESTINATION_INFO, messageExchangeRate);
	}

}

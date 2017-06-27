package com.vh.portlet.weather;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.vh.util.VHConstant;

/**
 * Portlet implementation class Weather
 */
public class WeatherManagement extends MVCPortlet {
	public void sendWeatherInfor(ActionRequest actReq, ActionResponse actRes){
		Message message = new Message();
		message.put(VHConstant.MessageConfig.NAME, VHConstant.MessageConfig.NAME_WEATHER);
		message.put(VHConstant.MSG_WEATHER.WEATHER_HUMID, "80" );
		message.put(VHConstant.MSG_WEATHER.WEATHER_TEMPERATER, "30" );
		
		MessageBusUtil.sendMessage(VHConstant.MessageConfig.DESTINATION_INFO, message);
		System.out.println("WeatherManagement - sendWeatherInfor");
	}

}

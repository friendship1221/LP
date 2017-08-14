package com.vh.portlet.shop;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.StringPool;
import com.vh.util.VHConstant;

public class ShopMessagingImpl implements MessageListener{
	public void receive(Message message) {
		try {
			doReceive(message);
		} catch (Exception e) {
			_log.error("Unable to process message " + message, e);
			System.out.println("Unable to process message " + e.toString());
		}
	}

	protected void doReceive(Message message) {
		String val01 = StringPool.BLANK;
		String val02 = StringPool.BLANK;
		// Receives message...
		String name = message.getString(VHConstant.MessageConfig.NAME);
		if( name.equalsIgnoreCase(VHConstant.MessageConfig.NAME_WEATHER)){
			// check Periodic period time and get information from weather infor
			val01 = message.getString(VHConstant.MSG_WEATHER.WEATHER_HUMID);
			val02 = message.getString(VHConstant.MSG_WEATHER.WEATHER_TEMPERATER);
			
			System.out.println("ShopMessagingImpl - doReceive:" + val01 + ";" + val02);
			// insert into weather table
			
			// call Shop refresh (ajax)
			
		}
		else{
			System.out.println("ShopMessagingImpl - doReceive:" + name);
		}
	}

	private static Log _log = LogFactoryUtil
			.getLog(ShopMessagingImpl.class);
}

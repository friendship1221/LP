package com.vh.portlet.staff;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.StringPool;
import com.vh.util.VHConstant;

public class StaffMessagingImpl implements MessageListener{

	@Override
	public void receive(Message message) {
		try {
			doReceive(message);
		} catch (Exception e) {
			e.printStackTrace();
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
			
			System.out.println("StaffMessagingImpl - doReceive:" + val01 + ";" + val02);
			// insert into weather table
			
			// call Shop refresh (ajax)
			
		}else{
			System.out.println("StaffMessagingImpl - doReceive:" + name);
		}
	}
}

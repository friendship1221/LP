package com.vh.util;

public class VHConstant {
	public interface MessageConfig{
		final String NAME = "name";
		final String TYPE = "type";
		final String VALUE = "value";
		
		final String NAME_WEATHER = "weather";
		final String NAME_EXCHANGERATE = "exchange rate";

		final String TYPE_CITY = "city";
		final String TYPE_REGINON = "region";
		final String TYPE_GOLD = "gold";
		final String TYPE_MONEY = "money";
		final String TYPE_STOCK = "stock";
		
		final String VALUE_DEFAULT = "default";
		
		final String DESTINATION_INFO = "message/info";
	}
	
	public interface MSG_WEATHER{
		final String WEATHER_HUMID = "humid";
		final String WEATHER_TEMPERATER = "temperature";
	}
	
	public interface LiferayConst {
		final String JSP_PAGE = "JSP_PAGE";
		final int DELTA_PAGE = 20;
	}
	
	public interface JSP_URL{
		final String ShopView = "/html/shop/view.jsp";
		final String ShopEdit = "/html/shop/edit.jsp";
	}
}

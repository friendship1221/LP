/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.vh.service.impl;

import java.util.Collections;
import java.util.List;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.vh.model.Shop;
import com.vh.model.impl.ShopImpl;
import com.vh.service.base.ShopLocalServiceBaseImpl;

/**
 * The implementation of the shop local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.vh.service.ShopLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author friendship
 * @see com.vh.service.base.ShopLocalServiceBaseImpl
 * @see com.vh.service.ShopLocalServiceUtil
 */
public class ShopLocalServiceImpl extends ShopLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.vh.service.ShopLocalServiceUtil} to access the shop local service.
	 */
	
	@SuppressWarnings("unchecked")
	public List<Shop> searchShop(String name, String address, int start, int end){
		List<Shop> lstShop = null;
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(ShopImpl.class, 
				PortalClassLoaderUtil.getClassLoader());
		if(!name.isEmpty()){
			query.add(PropertyFactoryUtil.forName("ShopName").eq(name));
		}
		if(!address.isEmpty()){
			query.add(PropertyFactoryUtil.forName("ShopAddress").eq(address));
		}
		query.addOrder(OrderFactoryUtil.asc("ShopName"));
		try {
			lstShop = dynamicQuery(query, start, end);
		} catch (SystemException e) {
			lstShop = Collections.emptyList();
			e.printStackTrace();
		}
		return lstShop;
	}
	
	public int countShop(String name, String address){
		int count = 0;
		DynamicQuery query = DynamicQueryFactoryUtil.forClass(ShopImpl.class, 
				PortalClassLoaderUtil.getClassLoader());
		if(!name.isEmpty()){
			query.add(PropertyFactoryUtil.forName("ShopName").eq(name));
		}
		if(!address.isEmpty()){
			query.add(PropertyFactoryUtil.forName("ShopAddress").eq(address));
		}
		try {
			count = (int) dynamicQueryCount(query);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return count;
	}
}
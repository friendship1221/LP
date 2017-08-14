/**
 *  Class	:	BaseMVCPortlet
 *
 *  Version	: 	1.0 
 *	=======================================================
 *  History	: 
 *
 *  DATE             AUTHOR      DESCRIPTION 
 *  -------------------------------------------------------- 
 *  27/2/2013      	Dev Team      Create new
 */

package com.vh.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.ActionRequest;
import javax.portlet.MimeResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * A base portlet. All implemented MVC portlets must extend this portlet.
 */
public abstract class BaseMVCPortlet extends MVCPortlet {

	@Override
	public void init() throws PortletException {

		super.init();
	}

	@Override
	public void render(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {

		// _log.debug( "render" );
		super.render(request, response);
	}

	@Override
	public void include(String path, RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {

		PortletRequestDispatcher portletRequestDispatcher = getPortletContext()
				.getRequestDispatcher(path);
		if (portletRequestDispatcher == null) {
			// _log.error(path + " is not a valid include");
		} else {
			portletRequestDispatcher.include(renderRequest, renderResponse);
		}
	}

	// JSON
	protected void writeJSON(PortletRequest portletRequest,
			MimeResponse mimeResponse, Object json) throws IOException {

		mimeResponse.setContentType(ContentTypes.APPLICATION_JSON);

		PortletResponseUtil.write(mimeResponse, json.toString());

		mimeResponse.flushBuffer();
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException,
			PortletException {

		callResourceMethod(resourceRequest, resourceResponse);
	}

	/**
	 * 
	 * @param resourceRequest
	 * @param resourceResponse
	 * @return
	 * @throws PortletException
	 */
	protected boolean callResourceMethod(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws PortletException {

		String actionName = ParamUtil.getString(resourceRequest,
				ActionRequest.ACTION_NAME);

		// String actionName = resourceRequest.getResourceID();

		if (Validator.isNull(actionName)) {

			return false;
		}

		try {
			// Method method = MethodCache.get( _classesMap,
			// _methodsMap,
			// getClass().getName(),
			// actionName,
			// new Class[] {
			// ResourceRequest.class,
			// ResourceResponse.class
			// } );
			Method method = getResourceMethod(actionName);
			method.invoke(this, resourceRequest, resourceResponse);
			return true;
		} catch (NoSuchMethodException nsme) {
			try {
				super.serveResource(resourceRequest, resourceResponse);
				return true;
			} catch (Exception e) {
				throw new PortletException(nsme);
			}
		} catch (InvocationTargetException ite) {
			Throwable cause = ite.getCause();
			if (cause != null) {
				throw new PortletException(cause);
			} else {
				throw new PortletException(ite);
			}
		} catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected Method getResourceMethod(String actionName)
			throws NoSuchMethodException {

		Method method = _actionMethods.get(actionName);

		if (method != null) {
			return method;
		}

		Class<?> clazz = getClass();

		method = clazz.getMethod(actionName, ResourceRequest.class,
				ResourceResponse.class);

		_actionMethods.put(actionName, method);

		return method;
	}

	private Map<String, Method> _actionMethods = new ConcurrentHashMap<String, Method>();
}

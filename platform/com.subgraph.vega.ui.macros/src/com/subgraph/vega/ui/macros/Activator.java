package com.subgraph.vega.ui.macros;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import com.subgraph.vega.api.http.requests.IHttpRequestEngineFactory;
import com.subgraph.vega.api.model.IModel;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.subgraph.vega.ui.macros"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	private ServiceTracker<IModel, IModel> modelTracker;
	private ServiceTracker<IHttpRequestEngineFactory, IHttpRequestEngineFactory> httpRequestEngineFactoryServiceTracker;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		modelTracker = new ServiceTracker<IModel, IModel>(context, IModel.class.getName(), null);
		modelTracker.open();
		
		httpRequestEngineFactoryServiceTracker = new ServiceTracker<IHttpRequestEngineFactory, IHttpRequestEngineFactory>(context, IHttpRequestEngineFactory.class.getName(), null);
		httpRequestEngineFactoryServiceTracker.open();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public IModel getModel() {
		return modelTracker.getService();
	}

	public IHttpRequestEngineFactory getHttpRequestEngineFactoryService() {
		return httpRequestEngineFactoryServiceTracker.getService();
	}

}

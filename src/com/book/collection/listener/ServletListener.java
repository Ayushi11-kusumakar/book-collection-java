package com.book.collection.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

public class ServletListener implements ServletContextListener {

	private ServletContext context;

	public void contextInitialized(ServletContextEvent contextEvent) {
		System.out.println("Context Initialized");

		context = contextEvent.getServletContext();
		String log4jConfigFile = context.getInitParameter("log4j");
		String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;

		PropertyConfigurator.configure(fullPath);
	}

	public void contextDestroyed(ServletContextEvent contextEvent) {
		System.out.println("Context Destroyed");
	}
}

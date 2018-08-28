package com.green.example.dao;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
	private static BeanFactory beanFactory;

	static {
		beanFactory = (BeanFactory) new ClassPathXmlApplicationContext(new String[] { "home.xml" });
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}
}

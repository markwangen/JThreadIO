package org.mark.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TestCGLib {
	public static class InfoManager {
		public void create() {
			System.out.println("Create a record");
		}
	}
	
	public static class InfoManagerFactory {
		public static InfoManager getInstance(CGLibProxy proxy) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(InfoManager.class);
			enhancer.setCallback(proxy);
			return (InfoManager)enhancer.create();
		}
	}
	
	public static class CGLibProxy implements MethodInterceptor {
		private String name;
		
		public CGLibProxy(String name) {
			this.name = name;
		}
		
		@Override
		public Object intercept(Object arg0, Method arg1, Object[] arg2,
				MethodProxy arg3) throws Throwable {
			if(!name.equals("Test")) {
				System.out.println("Name is not Test, cglib ignored");
				return null;
			}
			System.out.println("cglib proxy called");
			return arg3.invokeSuper(arg0, arg2);
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InfoManager manager = InfoManagerFactory.getInstance(new CGLibProxy("Test"));
		manager.create();
		
		InfoManager manager2 = InfoManagerFactory.getInstance(new CGLibProxy("Test2"));
		manager2.create();
	}

}

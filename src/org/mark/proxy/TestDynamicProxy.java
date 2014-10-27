package org.mark.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestDynamicProxy {
	public static interface Hello {
		public void sayHello();
	}
	
	public static class HelloImpl implements Hello	{

		@Override
		public void sayHello() {
			// TODO Auto-generated method stub
			System.out.println("say hello");
		}
		
	}
	
	public static class ProxyTest implements InvocationHandler {
		Object originalObj;
		
		Object bind(Object originalObj) {
			this.originalObj = originalObj;
			
			return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(), originalObj.getClass().getInterfaces(), this);
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			System.out.println("DynamicProxy invocated!");
			return method.invoke(originalObj, args);
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hello hello = (Hello) new ProxyTest().bind(new HelloImpl());
		hello.sayHello();
	}

}

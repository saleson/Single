package com.single.app.component.metadate.parse;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import com.single.app.component.metadate.ModelFramework;

/**
 * 模型解析器的创建工厂.
 * 传递解析器的完整类路径,参数类型,参数对象,然后创建指定的模型解析器.
 * 这个解析器对象是代理类型,在解析器调用parse()方法时,会执行监听器中的beforeParse()方法和afterParsed()方法
 * (前提是监听器没有过时,也就是监听器的方法isDeprecated()返回的是false).
 * @author Saleson. 
 * Computer by Administrator.
 *
 * @param <T>
 */
public class ParserFactory {

	/**
	 * 
	 * @param className
	 * @param constructionParams
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static ModelParser getParser(String className, Object... constructionParams) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		@SuppressWarnings("unchecked")
		Class<ModelParser> clazz = (Class<ModelParser>) Class.forName(className);
		return getParser(clazz, constructionParams);
	}
	
	/**
	 * 
	 * @param clazz
	 * @param constructionParams
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static <T extends ModelParser> T getParser(Class<T> clazz, Object... constructionParams) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		@SuppressWarnings({ "rawtypes" })
		Class[] paramClass = new Class[constructionParams.length];
		for(int i=0;i<constructionParams.length;i++){
			paramClass[i] = constructionParams[i].getClass();
		}
		return getParser(clazz, paramClass, constructionParams);
	}
	
	/**
	 * 
	 * @param clazz
	 * @param paramTypes
	 * @param constructionParams
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	public static <T extends ModelParser> T getParser(Class<T> clazz, Class[] paramTypes, Object[] constructionParams) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		//Constructor<T> constructor = clazz.getConstructor(paramClass);
		//T parser = new ParserDynamicProxy<T>().newclass(constructor.newInstance(constructionParams));
		T parser = (T) new ParserCglibProxy<T>().getProxy(clazz, paramTypes, constructionParams);
		ParsingProcessContext context = new ParsingProcessContext(parser);
		parser.setParsingContext(context);
		context.isFactoryGenerated(true);
		return parser;
	}
	
	
	
	/**
	 * 解析器的代理类.
	 * 用于调用监听器的beforeParse()方法和afterParsed()方法.
	 * @author Saleson. 
	 * Computer by Administrator.
	 *
	 * @param <T>
	 */
	private static class ParserCglibProxy<T extends ModelParser> implements MethodInterceptor {
		
		private Method[] parseMethods = new Method[3];
		private Method finishMethod;
		 @SuppressWarnings({ "rawtypes", "unchecked" })
		public T getProxy(Class<T> clazz, Class[] argumentTypes, Object[] arguments) throws NoSuchMethodException, SecurityException {
			 String invokeMethod = "parse";
			 Class[] invokeMethodsParamType = {File.class, InputStream.class, Reader.class};
			 int i=0;
			 for(;i<invokeMethodsParamType.length;i++){
				 parseMethods[i] = clazz.getMethod(invokeMethod, invokeMethodsParamType[i]);
			 }
			 finishMethod = clazz.getMethod("finish");
			 //clazz.getMethod(invokeMethod, parameterTypes)
			 Enhancer enhancer = new Enhancer();
			 enhancer.setSuperclass(clazz); 
			 enhancer.setCallback(this);
			 return (T) enhancer.create(argumentTypes, arguments); 
		}
		 
		
		@SuppressWarnings("unchecked")
		public synchronized Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
			ModelParser parser = (ModelParser) obj;
			Object result = null;
			//synchronized (parser) {
				result=proxy.invokeSuper(obj, args); 
				if(ArrayUtils.contains(parseMethods, method)){
					ParserUtil.invokeParsingListenerInvoke(parser, (List<ModelFramework>) result);
					parser.isParsing(false);
				}else if(method == finishMethod){
					ParserUtil.invokeParsingListenerAfterParsed(parser);
				}
			//}
			return result;
		}
	}
	
	/**
	 * 解析器的代理类.
	 * 该代理创建的是接口的实现类,而不是代理传递进来的对象的类型.
	 * @author Saleson. 
	 * Computer by Administrator.
	 *
	 * @param <T>
	 */
	/*private static class ParserDynamicProxy<T extends ModelParser> implements InvocationHandler{
		private T parser;
		
		@SuppressWarnings("unchecked")
		public T newclass(T parser){
			this.parser = parser;
			return (T) Proxy.newProxyInstance(parser.getClass().getClassLoader(), parser.getClass().getInterfaces(), this);
		}
		@Override
		public Object invoke(Object arg0, Method method, Object[] args) throws Throwable {
			if(method.getName().equals("parse")){
				System.out.println("sjdk");
			}
			return method.invoke(parser, args);
		}
	}*/
	
}

package jp.nw.async;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AsyncProcess {

	
	/**
	 * 非同期で行われるApplicationの実行関数
	 * @param className
	 */
	@SuppressWarnings("deprecation")
	protected static void execute(String className, String[] params) {
		try {
			Class<?> asyncClass = Class.forName(className);
			Object asyncObj = asyncClass.getDeclaredConstructor().newInstance();
			
			
			Method asyncMethod = asyncClass.getMethod("execute");
			
			// 実行引数存在チェック
			asyncMethod.invoke(asyncObj);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException 
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO 自動生成された catch ブロック
			System.out.println(e.toString());
		}
	}

}

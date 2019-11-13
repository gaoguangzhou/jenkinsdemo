package com.example.demo.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import javassist.ClassMap;
import javassist.ClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class MyAgent {
	public static void premain(String args, Instrumentation instrumentation) throws Exception {
		instrumentation.addTransformer(new ClassFileTransformer() {
			@Override
			public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
					ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
				if("com/example/demo/agent/MyServer".equals(className)) {
					try {
						/**
				         * 1、拷贝一个新的方法
				         * 2、修改原方法名
				         * 3、加入监听代码
				         */
						ClassPool pool = ClassPool.getDefault();
				        CtClass ctClass = pool.get("com.example.demo.agent.MyServer");
				        CtMethod ctMethod = ctClass.getDeclaredMethod("sayHello");
				        CtMethod copyMethod = CtNewMethod.copy(ctMethod, ctClass, new ClassMap());
				        ctMethod.setName("sayHello$agent");
				        copyMethod.setBody("{\n" +
				        		"System.out.println(\"hello name:\"+$1+\",message:\"+$2);"+
				                "    long begin = System.nanoTime();\n" +
				                "    try {\n" +
				                "        return sayHello$agent($1,$2);\n" +
				                "    } finally {\n" +
				                "        System.out.println(System.nanoTime() - begin);}\n" +
				                "    }");
				        ctClass.addMethod(copyMethod);
				        return ctClass.toBytecode();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return null;
			}
		}, true);
    }
}

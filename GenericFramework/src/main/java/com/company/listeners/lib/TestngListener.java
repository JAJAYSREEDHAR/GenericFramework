package com.company.listeners.lib;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import org.testng.IDataProviderListener;
import org.testng.IDataProviderMethod;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IAnnotationTransformer;

import com.company.testng.utils.Reporter;

public abstract class TestngListener extends Reporter
		implements IAnnotationTransformer, IRetryAnalyzer, IDataProviderListener {
	int maxTry = 2;

	@Override
	public boolean retry(ITestResult result) {
		System.out.println(result.getStatus());
		String testName = result.getName();
		if (testName.equals("test1")) {
			System.out.println(testName);
			return true;
		}
		return false;
	}

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		String name = testMethod.getName();
		System.out.println(name);

	}

	@Override
	public void afterDataProviderExecution(IDataProviderMethod arg0, ITestNGMethod arg1, ITestContext arg2) {

	}

	@Override
	public void beforeDataProviderExecution(IDataProviderMethod dp, ITestNGMethod meth, ITestContext test) {
		List<Integer> indices = dp.getIndices();
		for (Integer integer : indices) {
			System.out.println(integer);
		}

	}
}

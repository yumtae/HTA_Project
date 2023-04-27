package com.passtoss.myhome.common;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

public class AroundAdvice {

	

	private static final Logger logger = LoggerFactory.getLogger(AroundAdvice.class);
	/*
	  1. ProceedingJoinPoint 인터페이스는  JoinPoint를 상속했기 때문에 JoinPoint가 가진 모든 메서드를 지원합니다.
	  2. Around Advice 에서만 ProceedingJoinPoint 를 매개변수로 사용하는데 이 곳에서 procceed()메서드가 필요하기 때문입니다
	  3. Around Advice 인 경우 비즈니스 메서드 실행 전과 후에 실행 되는데 비즈니스 메서드를 호출하기 위해서는
	  		ProceedingJoinPoint 의 proceed()메서드가 필요합니다
	  		즉, 클라이언트의 요청을 가로챈 어드바이스는 클라이언트가 호출한 비즈니스 메서드(ServiceImpl의 get으로 시작하는 메서드)를
	  			호출하기 위해 ProceedingJoinPoint 객체를 매개 변수로 받아 proceed()메서드를 통해서
	  			비즈니스 메서드를 호출할 수 있습니다
	  			
	  * proceed()메서드 실행 후 메소드의 반환값을 리턴해야 합니다
	 * */
	
	@Around("execution(* com.passtoss.myhome.controller.BoardController.*(..))")
	public Object aroundLog(ProceedingJoinPoint proceeding) throws Throwable {
	
		
		logger.info("=============================================================");
		logger.info("[AroundAdvice] : 비즈니스 메서드 수행 전입니다.");
		logger.info("=============================================================");
		StopWatch sw = new StopWatch();
		sw.start();
		
		// 이 코드를 기준으로 전과 후로 나뉩니다
		Object result = proceeding.proceed();
		
		sw.stop();
		
		logger.info("=============================================================");
		logger.info("[AroundAdvice] : 비즈니스 메서드 수행 후입니다.");
		
		
		//호출되는 메서드에 대한 정보를 구합니다.
		Signature sig = proceeding.getSignature();
		
		
		/*
		 * import org.aspectj.lang.Signature 인터페이스는 호출되는 메서드와 관련된 정보를 제공합니다.
		 * String getName() : 메서드의 이름을 구한다.
		 * */
		
		
		/*
		 * 1.  proceeding.getTarget().getClass().getSimpleName() : Target클래스의 이름을 가져옵니다.
		 * 2. Object[] getArgs() : 클라이언트가 메서드를 호출할 때 남겨준 인자 목록을 Object 배열로 리턴합니다
		 * */
		
		//Object[] getArgs() :  클라이언트가 메서드를 호출할 때 넘겨준 인자 목록을 Object 배열로 리턴합니다.
		
		
		// BoardServiceImpl.getBoardList([1,10])
		logger.info("[Around Advice의 after] : " +  proceeding.getTarget().getClass().getSimpleName() + "." + 
							sig.getName() + "(" + Arrays.toString( proceeding.getArgs()) + ")"   );
		
		//getBoardList() 메소드 수행 시간  3초
		logger.info("[Around Advice의 after] : " + proceeding.getSignature().getName() + "() 메소드 수행 시간 : "
					+sw.getTotalTimeMillis() + "(ms)초");
		
		// com.naver.myboard2.BoardServiceImpl
		logger.info("[Around Advice의 after] : " + proceeding.getTarget().getClass().getName());
		
		logger.info("proceeding.proceed() 실행 후 반한값" + result);
		
		
		logger.info("=============================================================");
		
	
		return result;
		
	}
	
	
	
	
}

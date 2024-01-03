package dhgudtmxhs.dhgudtmxhsSpringBoot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* dhgudtmxhs.dhgudtmxhsSpringBoot..*(..))")
                        // 패키지명 > 밑 > 클래스명 > 파라미터 타입 즉 패키지 하위엔 다 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{

        long start = System.currentTimeMillis();
        System.out.println("START :" + joinPoint.toString());
        try{
            Object result = joinPoint.proceed(); // 다음메소드로 진행
            return joinPoint.proceed(); // 통해 가짜 클래스에서 실제 클래스로 연결된다고 함.

        }finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "MS");
        }




    }

}

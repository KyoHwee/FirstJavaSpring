package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component  //AOP기능 Spring Bean에 등록
@Aspect     //AOP기능을 사용하기 위하여 등록해야 함 + springconfig에 등록해야함
public class TimeTraceAop {
    @Around("execution(* hello.hellospring..*(..))") //hello.hellospring패키지의 하위에 전부 AOP기능 적용
    //AOP는 적용대상에 넣어둔 메소드가 실행될때마다 무언가 기능을 끼워넣는 것(기능은 jointPoint를 사용)
    //서비스 기능만 시간 측정 원하면 * hello.hellospring.service..*(..) 로 설정(적용 대상을 @Around로 설정하는것)
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start=System.currentTimeMillis();
        System.out.println("START : "+joinPoint.toString()); //어떤 메소드가 호출되는지 출력한다
        try{
            Object result=joinPoint.proceed(); //다음 메소드로 진행되게 해준다
            return result;
        } finally {
            long finish=System.currentTimeMillis();
            long timeMs=finish-start;
            System.out.println("END : "+joinPoint.toString()+" "+ timeMs +"ms");//
        }


        }
}

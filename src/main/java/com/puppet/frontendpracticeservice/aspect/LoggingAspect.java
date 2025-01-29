package com.puppet.frontendpracticeservice.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Аспект помогает логировать вызовы методов контроллеров и сервисов
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.puppet.frontendpracticeservice.controller.*.*(..)) ||" +
            " execution(* com.puppet.frontendpracticeservice.service.*.*(..))")
    public void pointCut() {
    }

    /**
     * Метод срабатывает перед вызовом сервиса или контроллера.
     * Проверяет на наличие аннотации @Service, логирует его имя, сигнатуру и переданные аргументы.
     *
     * @param joinPoint объект, представляющий доступ к метаданным метода, на который указывает pointcut.
     */
    @Before("pointCut()")
    public void logBefore(JoinPoint joinPoint) {
        String typeClass = joinPoint.getTarget().getClass().isAnnotationPresent(Service.class) ?
                "сервис" : "контроллер";
        log.info("Вызван {}: {} c аргументами: {}", typeClass, joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * Аспект логирует результат выполнения запроса,
     * дополнительно логирует вызов методов, если уровень установлен DEBUG.
     *
     * @param joinPoint объект, представляющий доступ к метаданным метода, на который указывает pointcut.
     * @return результат выполнения целевого метода.
     * @throws Throwable исключения, которые может выбросить целевой метод.
     */
    @Around("pointCut()")
    public Object LogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        if (log.isDebugEnabled()) {
            log.debug("Вызван метод {}.{} с аргументами: ({})", className, methodName, Arrays.toString(args));
        }

        Object result = joinPoint.proceed();
        if (log.isDebugEnabled()) {
            log.debug("Метод {} вернул результат: {}", methodName, result);
        }
        return result;
    }

    /**
     * Логирует информацию об исключениях.
     * Метод срабатывает после того, как целевой метод выбросил исключение,
     * логирует имя метода, класс, тип исключения и его сообщение.
     *
     * @param joinPoint объект, представляющий доступ к метаданным метода, на который указывает pointcut.
     * @param e         объект исключения.
     */
    @AfterThrowing(pointcut = "pointCut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Исключение в {}.{}() с причиной = {} - ({})",
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getSimpleName(),
                e.getClass().getSimpleName(),
                e.getMessage());
    }
}

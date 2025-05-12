package kr.hhplus.be.server.config.redis;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/*
 * distributed lock에서 어노테이션에 전달할 key값 매개변수를 구성해주는 클래스입니다.
 * 매개변수를 전달받아 Parser에서 파싱한 배열 key - value 중 매핑(부합) value 값을 추출합니다.
 * */
public class SpringELParser {
	/*
	 * Constructor
	 * */
	private SpringELParser() {
    }

    public static Object getDynamicValue(String[] parameterNames, Object[] args, String key) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        
        /*
         * 메서드에서 전달받은 매개변수들을 context에 저장합니다.
         * */
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        
        /*
         * parser는 어노테이션에서 전달받은 key값과 일치하는 값을 찾아 해당 value 값을 반환하게 됩니다.
         * */
        return parser.parseExpression(key).getValue(context, Object.class);
    }
}

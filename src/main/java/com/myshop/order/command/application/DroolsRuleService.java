package com.myshop.order.command.application;

import com.myshop.order.command.domain.Money;
import com.myshop.order.command.domain.OrderLine;
import com.myshop.order.infra.DroolsRuleEngine;

import java.beans.Customizer;
import java.util.Arrays;
import java.util.List;



// 가격 계산을 위해 인프라스트럭처 영역의 DroolsRuleEngine을 사용
public class DroolsRuleService {
    private DroolsRuleEngine ruleEngine;

    public interface RuleDiscounter {
        Money applyRules(Customer customer, List<OrderLine> orderLines);
    }
//    코드만 보면 Drools가 제공하는 타입을 직접 사용하지 않으므로 Drools 자체에 의존하지 않는다고 생각할 수 있음
//    but, 문자열이 Drools의 세션 이름을 의미하므로 Drools의 세션 이름을 변경하면 해당 코드도 변경해야 함
//    -> Drools라는 인프라 영역에 완전히 의존하고 있으므로, 다른 구현 기술을 사용하기 위해서는 많은 부분을 고쳐야 함
//    인프라의 의존할 때의 어려움: 테스트 어려움, 기능 확장의 어려움 -> DIP을 이용하여 해소할 수 있음

//    public CalculateDiscountService() {
//        ruleEngine = new DroolsRuleEngine();
//    }

//    RuleDiscounter를 이용하도록 수정
//    Drools에 의존하는 코드 없이 RuleDiscounter 인터페이스만을 상속받아 구현함
    public CalculateDiscountService() {
        private RuleDiscounter ruleDiscounter;

        public CalculateDiscountService(RuleDiscounter ruleDiscounter) {
            this.ruleDiscounter = ruleDiscounter;
        }

        public Money calculateDiscountService(List<OrderLine> orderLines, String customerId) {
            Customer customer = findCustomer(customerId);
            return ruleDiscounter.applyRules(customer, orderLines);
        }
    }

    public Money calculateDiscount(List<OrderLine> orderLines, String customerId) {
        Customer customer = findCustomer(customerId);

//      Drools에 특화된 코드들
        MutableMoney money = new MutableMoney(0); // 연산결과를 받기 위해 추가한 타입
        // 룰에 필요한 데이터(지식)
        List<?> facts = Arrays.asList(customer, money);
        facts.addAll(orderLines);
        ruleEngine.evalute("discountCalculation", facts); // Drools의 세션 이름
        return money.toImmutableMoney();
    }
}

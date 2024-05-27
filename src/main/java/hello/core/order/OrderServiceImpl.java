package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    // 필드 주입
    @Autowired private MemberRepository memberRepository;
    @Autowired private DiscountPolicy discountPolicy;

    @Autowired  //일반 메서드 주입
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // private final -> 값이 필수적으로 있어야 함
//    private MemberRepository memberRepository;
//    private DiscountPolicy dis
//    countPolicy;

    /*
    // setter 주입은 빈을 다 등록하고 의존관계 주입단계에서 이루어 진다.
    @Autowired // @Autowired(required = false) -> 주입할 대상 없어도 동작하게 함
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = "+memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = "+discountPolicy);
        this.discountPolicy = discountPolicy;
    }
    */

    // @Autowired -> 생성자가 하나일 때에는 @Autowired를 생략할 수 있다.
    // 생성자 주입은 객체가 생성될 때 주입된다.
    /*
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
     */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

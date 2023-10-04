package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        // given
        Member member = new Member("John", 34);

        // when
        Member savedMember = memberRepository.save(member);

        // them
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);

    }

    @Test
    void findAll(){
        // given
        Member member1 = new Member("John", 23);
        Member member2 = new Member("Chris", 27);
        Member member3 = new Member("Tina", 31);

        // when
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        // then
        List<Member> list = memberRepository.findAll();
        /*for(Member member : list){
            System.out.println("member.getId() = " + member.getId());
            System.out.println("member.getUsername() = " + member.getUsername());
            System.out.println("member.getAge() = " + member.getAge());
            System.out.println();
        }*/
        assertThat(list.size()).isEqualTo(3);
        assertThat(list).contains(member1, member2, member3);
    }
}

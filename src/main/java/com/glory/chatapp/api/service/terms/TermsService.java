package com.glory.chatapp.api.service.terms;

import com.glory.chatapp.api.service.terms.response.TermsResponse;
import org.springframework.stereotype.Service;

/*
    ✅ 1. "임시 저장" 용도로 Redis 사용 (회원가입 진행 중)
    유저가 회원가입을 완료하기 전에 약관 동의를 먼저 할 수도 있어.

    이 경우, 약관 동의 정보를 DB에 저장하기 애매하니까 Redis에 임시 저장하는 거야.

    예를 들어, 유저가 가입을 진행하다가 중간에 앱을 종료하면,
    다음에 다시 접속했을 때 Redis에서 동의 정보를 불러올 수 있어.

🔹  흐름 예시
    ✅ 유저가 약관 동의 → ✅ Redis에 저장 → ✅ 회원가입 진행 시 검증 후 최종 저장(DB로 이동)
 */
@Service
public class TermsService {

    public TermsResponse saveAgreementToRedis(TermsAgreementRequestList requestList) {

        // 검증..
        // 필수, 선택

    }
}

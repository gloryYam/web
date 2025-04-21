package com.glory.chatapp.repository.notice;

import com.glory.chatapp.domain.Notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

}

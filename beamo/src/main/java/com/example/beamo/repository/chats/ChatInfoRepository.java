package com.example.beamo.repository.chats;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatInfoRepository extends JpaRepository<ChatInfo, Long> {

    @Query( value = "select c from ChatInfo as c where c.seq=?1")
    ChatInfo findBySeq(Long seq);

    List<ChatInfo> findByAddress(String address, Sort seq);
}

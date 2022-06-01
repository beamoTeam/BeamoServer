package com.example.beamo.repository.chats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query(value = "select c FROM ChatRoom as c WHERE c.users.seq=?1")
    ChatRoom findByU_seq(Long seq);

    @Query(value = "select c FROM ChatRoom as c WHERE c.seq=?1")
    ChatRoom findByC_seq(Long seq);

}

package com.example.beamo.repository.chats;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ChatRoomRepository extends JpaRepository<ChatRoom, ChatRoomId> {

    @Query(value = "select c FROM ChatRoom as c WHERE c.users.seq=?1")
    ChatRoom findByU_seq(Long seq);

    @Query(value = "select c FROM ChatRoom as c WHERE c.chatInfo.seq=?1")
    ChatRoom findByC_I_seq(Long seq);

    @Query(value = "select c FROM ChatRoom as c WHERE c.users.seq=?1 and c.chatInfo.seq=?2")
    ChatRoom findByU_seqAndC_I_Seq(Long u, Long c_i);

    @Query( value = "select c from ChatRoom as c where c.seq=?1")
    List<ChatRoom> findByC_seq(Long seq);

    @Modifying
    @Query(value = "INSERT INTO beamo.chat_room\n" +
            "(seq, users_seq, chat_info_seq)\n" +
            "VALUES(?2, ?1, ?2)", nativeQuery = true)
    @Transactional
    int saveComposite_Primary_Keys(Long u, Long c);
}

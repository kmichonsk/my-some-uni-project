package com.app.sip.repositories;

import com.app.sip.model.Comment;
import com.app.sip.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByStationId(Long stationId);
}

package com.stellive.music.domain.repository;

import com.stellive.music.domain.entity.DataList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DataListRepository extends JpaRepository<DataList, Long> {

    @Query("SELECT d FROM DataList d WHERE d.isDataFetched = TRUE")
    List<DataList> findAllByDataFetched();

}

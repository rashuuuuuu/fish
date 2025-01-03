package com.website.common.repo;

import com.website.common.dto.SearchParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository<T> {

    Long count(SearchParam searchParam);
    List<T> getAll(SearchParam searchParam);

}

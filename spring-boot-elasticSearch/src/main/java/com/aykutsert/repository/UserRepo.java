package com.aykutsert.repository;

import com.aykutsert.entity.User;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends ElasticsearchRepository<User, String> {


    @Query("{\"bool\": {\"must\": [{\"match\": {\"ad\": \"?0\"}}]}}")
    List<User> getByCustomQuery(String search);

    List<User>  findByAdLikeOrSoyadLike(String ad, String soyad);
}
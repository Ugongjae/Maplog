package com.maplog.b.home.service;

import com.maplog.b.home.model.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlockDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Block> selectAllBlocks(){
        String query = "Select * from block";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<Block>(Block.class));
    }

    public int makeSample(int x,int y,String color){
        String query = "INSERT INTO block(x,y,color) VALUES(?,?,?)";
        return jdbcTemplate.update(query,x,y,color);
    }
}

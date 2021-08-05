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

    public Block selectBlockById(Long id){
        String query = "Select * from block where id=?";
        return jdbcTemplate.queryForObject(query,new Object[]{id},new BeanPropertyRowMapper<>(Block.class));
    }

    public int makeSample(int x,int y,String color,Long userId){
        String query = "INSERT INTO block(x,y,color,user_id) VALUES(?,?,?,?)";
        return jdbcTemplate.update(query,x,y,color,userId);
    }
}

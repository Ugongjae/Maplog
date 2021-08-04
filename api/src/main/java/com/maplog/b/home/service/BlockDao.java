package com.maplog.b.home.service;

import com.maplog.b.home.model.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BlockDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Block> selectAllBlocks(){
        String query = "Selet * from block";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<Block>(Block.class));
    }

    public int makeSample(int x,int y,String color){
        String query = "INSERT INTO block(x,y,color) VALUES(?,?,?)";
        return jdbcTemplate.update(query,x,y,color);
    }
}

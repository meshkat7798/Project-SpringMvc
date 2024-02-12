package com.example.projectspringmvc.service;



import com.example.projectspringmvc.dto.CommentDto;
import com.example.projectspringmvc.entity.Comment;
import com.example.projectspringmvc.entity.MyOrder;

import java.util.List;



public interface CommentService {

    CommentDto save(CommentDto commentDto);

    CommentDto deleteById(int id);

    CommentDto findById(Integer id);

    List<CommentDto> findAll();

    boolean existsById(Integer id);

    CommentDto update(CommentDto commentDto);

}

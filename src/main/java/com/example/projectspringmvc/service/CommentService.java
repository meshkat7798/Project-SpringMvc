package com.example.projectspringmvc.service;



import com.example.projectspringmvc.dto.CommentDto;
import com.example.projectspringmvc.dto.response.ResponseCommentDto;
import com.example.projectspringmvc.entity.Comment;
import com.example.projectspringmvc.entity.MyOrder;

import java.util.List;



public interface CommentService {

    CommentDto save(CommentDto commentDto);

    CommentDto deleteById(int id);

    ResponseCommentDto findById(Integer id);

    CommentDto findById2(Integer id);

    List<ResponseCommentDto> findAll();

    boolean existsById(Integer id);

}

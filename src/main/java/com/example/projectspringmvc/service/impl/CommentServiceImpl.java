package com.example.projectspringmvc.service.impl;


import com.example.projectspringmvc.dto.AdminDto;
import com.example.projectspringmvc.dto.CommentDto;
import com.example.projectspringmvc.entity.Comment;
import com.example.projectspringmvc.entity.user.Admin;
import com.example.projectspringmvc.exception.NotFoundException;
import com.example.projectspringmvc.repository.CommentRepository;
import com.example.projectspringmvc.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Configurable
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;

    private final ModelMapper modelMapper;


    @Override
    public CommentDto save(CommentDto commentDto) {

        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment = commentRepository.save(comment);
        commentDto = modelMapper.map(comment, CommentDto.class);
        return commentDto;

    }

    @Override
    public CommentDto findById(Integer id) {
        Comment comment = commentRepository.findById(id). orElseThrow(
                () -> new NotFoundException(String.format("%d not Fount",id)));
        return modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public CommentDto update(CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentDto.getId()).orElseThrow(()->new NotFoundException("id not found"));
        comment.setMyOrder(comment.getMyOrder());
        comment.setComment(comment.getComment());
        comment.setSpecialistScore(comment.getSpecialistScore());
        comment=commentRepository.save(comment);
        commentDto=modelMapper.map(comment,CommentDto.class);
        return commentDto;

    }

    @Override
    public CommentDto deleteById(int id) {
        Comment comment = commentRepository.findById(id).orElseThrow(()->new NotFoundException("id not found"));
        CommentDto commentDto = modelMapper.map(comment,CommentDto.class);
        commentRepository.deleteById(id);
        return commentDto ;

    }


    @Override
    public List<CommentDto> findAll() {
        List<Comment> commentList = commentRepository.findAll();
        return commentList.stream().map(comment -> modelMapper
                .map(comment,CommentDto.class)).collect(Collectors.toList());
    }
    @Override
    public boolean existsById(Integer id) {
        return commentRepository.existsById(id);
    }


}

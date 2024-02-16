package com.example.projectspringmvc.controller;
import com.example.projectspringmvc.dto.CommentDto;
import com.example.projectspringmvc.dto.response.ResponseCommentDto;
import com.example.projectspringmvc.service.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

private final CommentServiceImpl commentService;

    //Done
    @PostMapping("/save-comment")
    public ResponseEntity<CommentDto> save(@RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.save(commentDto), HttpStatus.OK);
    }


    //Done
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ResponseCommentDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(commentService.findById(id), HttpStatus.OK);
    }


    //Done
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<CommentDto> deleteById(@PathVariable("id") int id) {
        return new ResponseEntity<>(commentService.deleteById(id), HttpStatus.OK);
    }

    //Done
    @GetMapping("/find-all")
    public ResponseEntity<List<ResponseCommentDto>> findAll() {
        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
    }


    //Done
    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable Integer id) {
        return commentService.existsById(id);
    }

}

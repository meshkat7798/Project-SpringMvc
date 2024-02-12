package com.example.projectspringmvc.controller;
import com.example.projectspringmvc.dto.CommentDto;
import com.example.projectspringmvc.entity.Comment;
import com.example.projectspringmvc.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

private final CommentService commentService;

    @PostMapping("/save-comment")
    public ResponseEntity<CommentDto> save(@RequestBody CommentDto commentDto) {
        return new ResponseEntity<CommentDto>(commentService.save(commentDto), HttpStatus.OK);
    }


    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<CommentDto> findById(@PathVariable("id") Integer id) {
        Comment comment = new Comment();
        return new ResponseEntity<CommentDto>(commentService.findById(id), HttpStatus.OK);
    }


    @PutMapping("/update-comment/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable("id") int id, @RequestBody CommentDto commentDto) {
        commentDto.setId(id);
        return new ResponseEntity<CommentDto>(commentService.update(commentDto), HttpStatus.OK);

    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<CommentDto> deleteById(@PathVariable("id") int id) {
        return new ResponseEntity<CommentDto>(commentService.deleteById(id), HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<CommentDto>> findAll() {
        return new ResponseEntity<List<CommentDto>>(commentService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/existsById/{id}")
    public boolean existsById(@PathVariable Integer id) {
        return commentService.existsById(id);
    }

}

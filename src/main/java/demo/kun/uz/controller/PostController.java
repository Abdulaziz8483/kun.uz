package demo.kun.uz.controller;

import demo.kun.uz.dto.PostDTO;
import demo.kun.uz.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;


    @PostMapping(" ")
    public PostDTO create(@RequestBody PostDTO postDTO) {
     PostDTO result=   postService.createPost(postDTO);

        return result;
    }

    @GetMapping("/{id}")
    public PostDTO byId(@PathVariable("id") Integer id) {
        PostDTO result = postService.getById(id);
        return result;
    }
}

package demo.kun.uz.service;

import demo.kun.uz.dto.AttachDTO;
import demo.kun.uz.dto.PostDTO;
import demo.kun.uz.entity.PostEntity;
import demo.kun.uz.repository.PostAttachRepository;
import demo.kun.uz.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;
    @Autowired
    private PostAttachRepository attachRepository;
    @Autowired
    private  PostAttachService postAttachService;

    public PostDTO createPost(PostDTO postDTO){
        PostEntity post=new PostEntity();

        post.setTitle(postDTO.getTitle());
        post.setContent(post.getContent());

        repository.save(post);
        postAttachService.create(post.getId(),postDTO.getAttachDTOList());
        postDTO.setId(post.getId());
        return postDTO  ;

    }

    public PostDTO getById(Integer id){

        PostEntity post=repository.findById(id).orElseThrow(()-> new RuntimeException("Post not found"));


        PostDTO postDTO=new PostDTO();
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setId(post.getId());

        List<AttachDTO> attachDTOList=postAttachService.getAttachList(id);
        postDTO.setAttachDTOList(attachDTOList);

        return postDTO;
    }

    public boolean update(Integer postId, PostDTO postDTO) {
        PostEntity post = repository.findById(postId).orElseThrow(() -> {
            throw new RuntimeException("Post not found");
        });
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        repository.save(post); // update
        // post attach save
//        postAttachService.update(post.getId(), postDTO.getAttachList());
        postAttachService.mere(post.getId(), postDTO.getAttachDTOList());
        return true;
    }



}

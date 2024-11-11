package demo.kun.uz.service;

import demo.kun.uz.dto.AttachDTO;
import demo.kun.uz.entity.PostAttachEntity;
import demo.kun.uz.repository.PostAttachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.exists;

@Service
public class PostAttachService {
    @Autowired
    private PostAttachRepository postAttachRepository;
    @Autowired
    private AttachService attachService;

    public void create(Integer postId, List<AttachDTO> attachIdList){

        if(attachIdList==null){
            return;
        }
        for (AttachDTO dto : attachIdList) {


            PostAttachEntity postAttach = new PostAttachEntity();
            postAttach.setPostId(postId);
            postAttach.setAttachId(dto.getId());

            postAttachRepository.save(postAttach);
        }

    }

    public List<AttachDTO> getAttachList(Integer postId) {
        List<String> attachIdList = postAttachRepository.findAllByPostId(postId);
        List<AttachDTO> attachDTOList = new ArrayList<>();
        for (String attachId : attachIdList) {
            attachDTOList.add(attachService.getDTO(attachId));
        }
        return attachDTOList;
    }

    public void update(Integer postId, List<AttachDTO> attachIdList){

        if(attachIdList==null){
            return;
        }
        for (AttachDTO dto : attachIdList) {


            PostAttachEntity postAttach = new PostAttachEntity();
            postAttach.setPostId(postId);
            postAttach.setAttachId(dto.getId());

            postAttachRepository.save(postAttach);
        }

    }

    public void mere(Integer postId, List<AttachDTO> newAttachIdList) {
        // old [1,2,3,4]
        // new [1,7]
        // -----------
        // result [1,7]

        if (newAttachIdList == null) {
            newAttachIdList = new ArrayList<>();
        }
        List<String> oldAttachIdList = postAttachRepository.findAllByPostId(postId);
        for (String attachId : oldAttachIdList) {
            if (!exists(attachId, newAttachIdList)) {
                // delete operation {attachId}
                postAttachRepository.deleteByPostIdAndAttachId(postId, attachId);
            }
        }

        for (AttachDTO newAttach : newAttachIdList) {
            if (!oldAttachIdList.contains(newAttach.getId())) {
                // save
                PostAttachEntity entity = new PostAttachEntity();
                entity.setPostId(postId);
                entity.setAttachId(newAttach.getId());
                postAttachRepository.save(entity);
            }
        }
//        postAttachRepository.deleteByPostId(postId);
//        create(postId, newAttachIdList);
    }
    private boolean exists(String attachId, List<AttachDTO> dtoList) {
        for (AttachDTO dto : dtoList) {
            if (dto.getId().equals(attachId)) {
                return true;
            }
        }
        return false;
    }
}

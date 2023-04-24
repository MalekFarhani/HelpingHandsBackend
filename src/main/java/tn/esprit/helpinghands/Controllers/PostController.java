package tn.esprit.helpinghands.Controllers;

import com.sun.security.auth.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;
import tn.esprit.helpinghands.entities.Post;
import tn.esprit.helpinghands.entities.User;
import tn.esprit.helpinghands.security.AuthenticationService;
import tn.esprit.helpinghands.services.PostIservice;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {
    PostIservice postIservice;
    private AuthenticationService authenticationService ;
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addPost(@ModelAttribute Post post, @RequestParam(value = "files",required = false) List<MultipartFile> files) throws IOException {
        return postIservice.addPost(files,post);
    }
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<?> Update_Post(@ModelAttribute Post post,@RequestParam(value = "files",required = false) List<MultipartFile> files, @RequestParam Integer idPost) throws IOException {
        return postIservice.Update_post(post,files,idPost);
    }
    @PostMapping("/delete")
    public String deletePost(@RequestParam Integer idPost) {
        return postIservice.deletePost(idPost);
    }


    @GetMapping("/findAll")
    public List<Post> Get_all_post(){

        return postIservice.Get_all_post();
    }
    @GetMapping("/findByUser")
    public List<Post> Get_post_by_User(@RequestParam Integer idUser){
        return postIservice.Get_post_by_User(idUser);
    }
    @GetMapping("/findById")
    public Post Get_Post_Details(@RequestParam Integer idPost) {
        return postIservice.getPostById(idPost);

    }
    @GetMapping("/searchPost")
    public  List<Post> adversting_bydata(@RequestParam String ch){
        Integer idUser = authenticationService.currentlyAuthenticatedUser().getId();
        return postIservice.Searchpost(ch,idUser);
    }



}


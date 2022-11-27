package bj.orace.voyage.metiers;

import java.io.IOException;
import java.util.List;

import bj.orace.voyage.models.Post;

public interface PostManager {
    Post createPost(Post post);
    List<Post> findAll() throws IOException;


}

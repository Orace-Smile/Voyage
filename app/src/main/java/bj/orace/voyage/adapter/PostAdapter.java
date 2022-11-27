package bj.orace.voyage.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

import java.util.ArrayList;
import java.util.List;

import bj.orace.voyage.R;
import bj.orace.voyage.models.Post;

public class PostAdapter extends BaseAdapter {

    public List<Post> posts = new ArrayList<>();

    public PostAdapter(List<Post> posts){
        this.posts = posts;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int i) {
        return posts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return posts.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        LinearLayoutCompat layout = (LinearLayoutCompat) inflater.inflate(R.layout.post_item_view,viewGroup,false);
        Post post = posts.get(i);
        ((TextView)layout.findViewById(R.id.post_name)).setText(post.name);
        ((TextView)layout.findViewById(R.id.post_description)).setText(post.description);
        ((TextView)layout.findViewById(R.id.post_price)).setText(post.price.toString());

        return layout;
    }
}

package bj.orace.voyage;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.core.view.View;

public class PostModel {
    private View view;
    private ListView listView;
    private Button buttonReserver;
    private Button buttonAddToFovaories;
    private ImageView imageView;
    private EditText editText;


    public PostModel(View view, ListView listView, Button buttonReserver, Button buttonAddToFovaories, ImageView imageView,EditText editText) {
        this.view = view;
        this.listView = listView;
        this.buttonReserver = buttonReserver;
        this.buttonAddToFovaories = buttonAddToFovaories;
        this.imageView = imageView;
        this.editText = editText;
    }



    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public Button getButtonReserver() {
        return buttonReserver;
    }

    public void setButtonReserver(Button buttonReserver) {
        this.buttonReserver = buttonReserver;
    }

    public Button getButtonAddToFovaories() {
        return buttonAddToFovaories;
    }

    public void setButtonAddToFovaories(Button buttonAddToFovaories) {
        this.buttonAddToFovaories = buttonAddToFovaories;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }
}

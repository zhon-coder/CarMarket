package com.example.carmarket;

import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import com.example.carmarket.databinding.DetailsActivityBinding;
import com.example.carmarket.repository.CarRepository;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_PARAM_ID = "detail:_id";
    public static final String VIEW_NAME_HEADER_IMAGE = "detail:header:image";
    public static final String VIEW_NAME_HEADER_TITLE = "detail:header:title";
    private DetailsActivityBinding detailBinding;
    private CarItem mItem;
    private ImageView iv_header;
    private TextView tv_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailBinding = DetailsActivityBinding.inflate(getLayoutInflater());
        setContentView(detailBinding.getRoot());

        mItem = CarRepository.getCarItem(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));
        iv_header = detailBinding.ivHeader;
        tv_title = detailBinding.tvTitle;

        ViewCompat.setTransitionName(iv_header, VIEW_NAME_HEADER_IMAGE);
        ViewCompat.setTransitionName(tv_title, VIEW_NAME_HEADER_TITLE);

        loadItem();
    }

    private void loadItem() {
        tv_title.setText(mItem.getFullTitle());

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && addTransitionListener()) {
            loadThumbnail();
        }else {
            loadFullSizeImage();
        }
    }

    private void loadFullSizeImage() {
        detailBinding.ivHeader.setImageResource(mItem.getPhotoResId());
    }

    private void loadThumbnail() {
        detailBinding.ivHeader.setImageResource(mItem.getThumbnailResId());
    }

    private boolean addTransitionListener() {
        final Transition transition = getWindow().getSharedElementEnterTransition();

        if(transition != null) {
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionCancel(Transition transition) {
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    loadFullSizeImage();
                    transition.removeListener(this);
                }

                @Override
                public void onTransitionPause(Transition transition) {
                }

                @Override
                public void onTransitionResume(Transition transition) {
                }

                @Override
                public void onTransitionStart(Transition transition) {
                }
            });
            return true;
        }
        return false;
    }
}

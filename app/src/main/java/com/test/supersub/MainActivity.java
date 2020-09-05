package com.test.supersub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.test.supersub.models.MainResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    VideoView videoView;
    WebView webView;
    YouTubePlayerView youTubePlayerView;
    TextView title, description,category,illustration,subcategory,illus_dsc;
    ImageView illustrationImage;
    RetrofitAPI retrofitAPI;
    ProgressDialog dialog;
    MainResponse mainResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        dialog=new ProgressDialog(this);
         category = toolbar.findViewById(R.id.category_tv);
       //  videoView =findViewById(R.id.videoView);
        //   webView =findViewById(R.id.videoView);
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        final MediaController mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(videoView);
         title = findViewById(R.id.title_tv);
         description=findViewById(R.id.description);
         illus_dsc=findViewById(R.id.illus_desc);
        illustrationImage= findViewById(R.id.illustration_imgv);
         illustration= findViewById(R.id.illustration_tv);
         subcategory= findViewById(R.id.subcat);
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
         dialog.show();


        retrofitAPI = RetrofitClientInstance.getRetrofitInstance().create(RetrofitAPI.class);
        retrofitAPI.getResponse().enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if(response.body()!=null&&response.isSuccessful()){
                    dialog.dismiss();
                    mainResponse = response.body();
                    if(getSupportActionBar()!=null){
                        category.setText(mainResponse.getCategory());
                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                    }

                   /* videoView.setMediaController(mediacontroller);
                    videoView.setVideoURI(Uri.parse(mainResponse.getVideo().getUrl()));
                    videoView.requestFocus();*/
                    youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                            String videoId = "gusv3BsHd3g";
                            youTubePlayer.loadVideo(videoId, 0f);

                        }
                    });
                    title.setText(mainResponse.getTitle());
                    description.setText(mainResponse.getDescription());
                    subcategory.setText(mainResponse.getSubcategory());
                    Glide.with(MainActivity.this).load(mainResponse.getIllustrations().get(0).getImageUrl()).into(illustrationImage);
                    illustration.setText(mainResponse.getIllustrations().get(0).getCaption());
                    illus_dsc.setText(mainResponse.getIllustrations().get(0).getDescription());




                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });





    }
}
package com.example.android.bakingapp.StepDetails;


import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.food.Steps;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;


public class RecipestepFragment extends Fragment {

    private Steps steps;
    private com.google.android.exoplayer2.ui.PlayerView videoView;
    private SimpleExoPlayer player;
    int state;
    long position;
    boolean playwhenready;


    public RecipestepFragment()
    {
    }

    public RecipestepFragment(Steps steps)
    {
        this.steps = steps;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if(savedInstanceState!=null)
        {
            steps= (Steps) savedInstanceState.getSerializable("Step");

        }

        View rootView= inflater.inflate(R.layout.fragment_recipestep,container,false);
        videoView =rootView.findViewById(R.id.video_view);
        player = ExoPlayerFactory.newSimpleInstance(getContext());
        playwhenready=true;
        videoView.setPlayer(player);
        MediaSource mediaSource = buildMediaSource(Uri.parse(steps.getVideoURL()));
        player.prepare(mediaSource, false, false);
        if(savedInstanceState!=null){
            state= savedInstanceState.getInt("state");
            System.out.println("state is ldjjnflknjlksmALS "+state);
            position=savedInstanceState.getLong("position");
            System.out.println("position is mlsddjlkasl:"+position);
            playwhenready=savedInstanceState.getBoolean("ready");
            player.seekTo(position);
        }
        player.setPlayWhenReady(playwhenready);
        return rootView;
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(getContext(), "exoplayer");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
    }
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("Step",steps);
        outState.putInt("state",player.getPlaybackState());
        outState.putLong("position",player.getCurrentPosition());
        outState.putBoolean("ready",player.getPlayWhenReady());
    }

    @Override
    public void onPause() {
        super.onPause();
        position=player.getCurrentPosition();
        state=player.getPlaybackState();
        player.getPlayWhenReady();
        player.release();
    }

    @Override
    public void onResume() {
        super.onResume();
        player = ExoPlayerFactory.newSimpleInstance(getContext());
        videoView.setPlayer(player);
        MediaSource mediaSource = buildMediaSource(Uri.parse(steps.getVideoURL()));
        player.prepare(mediaSource, false, false);
        player.seekTo(position);
        player.setPlayWhenReady(playwhenready);

    }
}

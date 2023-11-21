package com.example.newnewmusicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicPlayer extends AppCompatActivity implements SongAdapter.OnItemClickListener {

    private RecyclerView musicRecyclerView;
    private SongAdapter musicAdapter;
    private List<Song> musicList;
    private MediaPlayer mediaPlayer;
    public static int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        currentPosition = 0;

        musicRecyclerView = findViewById(R.id.musicRecyclerView);
        musicRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        musicList = new ArrayList<>();

        musicList.add(new Song("diet mountain dew", "Lana Del Rey", R.raw.lana_del_rey_diet_mountain_dew));
        musicList.add(new Song("Believer", "Lana Del Rey", R.raw.lana_del_rey_born_to_die));
        musicList.add(new Song("Smells like teen spirit", "Lana Del Rey", R.raw.lana_del_rey_doin_time));
        musicList.add(new Song("Young and beautiful", "Lana Del Rey", R.raw.lana_del_rey_young_and_beautiful));



        musicAdapter = new SongAdapter(musicList, (SongAdapter.OnItemClickListener) this);
        musicRecyclerView.setAdapter(musicAdapter);
    }

    private int currentSongPosition = -1;
    private boolean isPaused = false;

    @Override
    public void onItemClick(int position) {
        if (position == currentSongPosition) {
            if (mediaPlayer != null) {
                if (isPaused) {
                    mediaPlayer.start();
                    Toast.makeText(this, "Продолжение воспроизведения", Toast.LENGTH_SHORT).show();
                    isPaused = false;
                } else {
                    mediaPlayer.pause();
                    Toast.makeText(this, "Пауза", Toast.LENGTH_SHORT).show();
                    isPaused = true;
                }
            }
        } else {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            Song song = musicList.get(position);

            mediaPlayer = MediaPlayer.create(this, song.getPath());

            mediaPlayer.start();
            Toast.makeText(this, "Воспроизведение: " + song.getTitle(), Toast.LENGTH_SHORT).show();
            currentSongPosition = position;
            isPaused = false;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}

package kong.qingwei.volumecontroller;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {

    private AudioManager mAudioManager;
    private SwitchCompat mSwitchVolume;
    private SwitchCompat mSwitchUi;
    private int flag = AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwitchVolume = (SwitchCompat) findViewById(R.id.switch_volume);
        if (mSwitchVolume != null) {
            mSwitchVolume.setOnCheckedChangeListener(this);
        }
        mSwitchUi = (SwitchCompat) findViewById(R.id.switch_ui);
        if (mSwitchUi != null) {
            mSwitchUi.setOnCheckedChangeListener(this);
        }

        // AudioManager
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // 媒体音量
        int mMusicMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int mCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar seekBar_music = (SeekBar) findViewById(R.id.seekBar_music);
        if (seekBar_music != null) {
            seekBar_music.setMax(mMusicMaxVolume);
            seekBar_music.setProgress(mCurrentVolume);
            seekBar_music.setOnSeekBarChangeListener(this);
        }

        // 通话音量
        int mCallMaxVolume1 = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
        int mCallCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
        SeekBar seekBar_call = (SeekBar) findViewById(R.id.seekBar_call);
        if (seekBar_call != null) {
            seekBar_call.setMax(mCallMaxVolume1);
            seekBar_call.setProgress(mCallCurrentVolume);
            seekBar_call.setOnSeekBarChangeListener(this);
        }

        // 闹钟音量
        int mAlarmMaxVolume1 = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
        int mAlarmCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_ALARM);
        SeekBar seekBar_alarm = (SeekBar) findViewById(R.id.seekBar_alarm);
        if (seekBar_alarm != null) {
            seekBar_alarm.setMax(mAlarmMaxVolume1);
            seekBar_alarm.setProgress(mAlarmCurrentVolume);
            seekBar_alarm.setOnSeekBarChangeListener(this);
        }

        // 铃声音量
        int mRingMaxVolume1 = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        int mRingCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_RING);
        SeekBar seekBar_ring = (SeekBar) findViewById(R.id.seekBar_ring);
        if (seekBar_ring != null) {
            seekBar_ring.setMax(mRingMaxVolume1);
            seekBar_ring.setProgress(mRingCurrentVolume);
            seekBar_ring.setOnSeekBarChangeListener(this);
        }

        //        // 铃声音量
        //        int mNotificationMaxVolume1 = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION);
        //        int mNotificationCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
        //        SeekBar seekBar_notification = (SeekBar) findViewById(R.id.seekBar_notification);
        //        assert seekBar_notification != null;
        //        seekBar_notification.setMax(mNotificationMaxVolume1);
        //        seekBar_notification.setProgress(mNotificationCurrentVolume);
        //        seekBar_notification.setOnSeekBarChangeListener(this);
        //
        //        // 系统音量
        //        int mSystemMaxVolume1 = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
        //        int mSystemCurrentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
        //        SeekBar seekBar_system = (SeekBar) findViewById(R.id.seekBar_system);
        //        assert seekBar_system != null;
        //        seekBar_system.setMax(mSystemMaxVolume1);
        //        seekBar_system.setProgress(mSystemCurrentVolume);
        //        seekBar_system.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekBar_music: // 媒体音量
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, flag);
                break;
            case R.id.seekBar_call: // 通话音量
                mAudioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, progress, flag);
                break;
            case R.id.seekBar_alarm: // 闹钟音量
                mAudioManager.setStreamVolume(AudioManager.STREAM_ALARM, progress, flag);
                break;
            case R.id.seekBar_ring: // 铃声音量
                mAudioManager.setStreamVolume(AudioManager.STREAM_RING, progress, flag);
                break;
            //            case R.id.seekBar_notification: // 铃声音量
            //                mAudioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, progress, AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
            //                break;
            //            case R.id.seekBar_system: // 系统音量
            //                mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, progress, AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI);
            //                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.switch_volume:
                if (isChecked) {
                    if (mSwitchUi.isChecked()) {
                        flag = AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI;
                    } else {
                        flag = AudioManager.FLAG_PLAY_SOUND;
                    }
                } else {
                    if (mSwitchUi.isChecked()) {
                        flag = AudioManager.FLAG_SHOW_UI;
                    } else {
                        flag = AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE;
                    }
                }
                break;
            case R.id.switch_ui:
                if (isChecked) {
                    if (mSwitchVolume.isChecked()) {
                        flag = AudioManager.FLAG_PLAY_SOUND | AudioManager.FLAG_SHOW_UI;
                    } else {
                        flag = AudioManager.FLAG_SHOW_UI;
                    }
                } else {
                    if (mSwitchVolume.isChecked()) {
                        flag = AudioManager.FLAG_PLAY_SOUND;
                    } else {
                        flag = AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE;
                    }
                }
                break;
        }
    }
}

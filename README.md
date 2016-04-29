#Android系统的音量控制

##效果图：

![G2](http://img.blog.csdn.net/20160429153826923)

##GitHub

[GitHub(源码):https://github.com/kongqw/VolumeController](https://github.com/kongqw/VolumeController)


##方法
	
* 获取AudioManager

	```
	mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
	```

* 获取最大音量

	```
	mAudioManager.getStreamMaxVolume(`streamType`);
	```

* 获取当前音量

	```
	mAudioManager.getStreamVolume(`streamType`);
	```

* 设置音量

	```
	mAudioManager.setStreamVolume(`streamType`, `音量大小`, `flags`);
	```

* `streamType` 几种类型

	1. AudioManager.STREAM_MUSIC			媒体音量
	2. AudioManager.STREAM_VOICE_CALL		通话音量
	3. AudioManager.STREAM_ALARM			闹钟音量
	4. AudioManager.STREAM_RING				铃声音量
	5. ……

* `flags` 几种类型

	1. AudioManager.FLAG_PLAY_SOUND		声音
	2. AudioManager.FLAG_SHOW_UI		UI
	3. AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE	移除声音和震动
	4. ……


##Code

```
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
```


##XML

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="kong.qingwei.volumecontroller.MainActivity">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="声音" />

        <android.support.v7.widget.SwitchCompat
            android:id="@+id/switch_volume"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="10dp"
            android:checked="true" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="30dp"
            android:text="界面" />

        <android.support.v7.widget.SwitchCompat
            android:id="@+id/switch_ui"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="10dp"
            android:checked="true" />


    </LinearLayout>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        android:text="媒体音量" />

    <SeekBar
        android:id="@+id/seekBar_music"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="通话音量" />

    <SeekBar
        android:id="@+id/seekBar_call"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="闹钟音量" />

    <SeekBar
        android:id="@+id/seekBar_alarm"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="铃声音量" />

    <SeekBar
        android:id="@+id/seekBar_ring"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true" />

    <!--<TextView-->
    <!--android:layout_width="wrap_content"-->
    <!--android:layout_height="wrap_content"-->
    <!--android:text="铃声音量" />-->

    <!--<SeekBar-->
    <!--android:id="@+id/seekBar_notification"-->
    <!--android:layout_width="match_parent"-->
    <!--android:layout_height="wrap_content"-->
    <!--android:layout_centerHorizontal="true" />-->

    <!--<TextView-->
    <!--android:layout_width="wrap_content"-->
    <!--android:layout_height="wrap_content"-->
    <!--android:text="系统音量" />-->

    <!--<SeekBar-->
    <!--android:id="@+id/seekBar_system"-->
    <!--android:layout_width="match_parent"-->
    <!--android:layout_height="wrap_content"-->
    <!--android:layout_centerHorizontal="true" />-->
</LinearLayout>
```


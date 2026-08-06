package com.capiro.composables

import android.content.Context
import android.media.MediaPlayer

class AlarmEvents(appContext: Context) {
    val ERROR_FORMAT_NOT_VALID = -1
    val ERROR_LABEL_ALREADY_READ = -2
    val ERROR_VARIETY_CHANGE = -4
    val RIP_SOUND = -5
    val SUCCESSFUL_READ = 0

    private val _alarmRightScan by lazy {
        MediaPlayer.create(
            appContext,
            R.raw.right
        )
    }
    private val _alarmWrongScan by lazy {
        MediaPlayer.create(
            appContext,
            R.raw.wrong
        )
    }
    private val _alarmRepeatedScan by lazy {
        MediaPlayer.create(
            appContext,
            R.raw.repeated
        )
    }

    private val _alarmVarietyChangeScan by lazy {
        MediaPlayer.create(
            appContext,
            R.raw.error
        )
    }

    private val _alarmRipScan by lazy {
        MediaPlayer.create(
            appContext,
            R.raw.rip
        )
    }

    //sound Scanner
    fun playAlarmCapiro(
        readingState: Int,
    ) {

        if (_alarmRightScan.isPlaying) {
            _alarmRightScan.pause()
            _alarmRightScan.seekTo(0)
        }

        if (_alarmWrongScan.isPlaying) {
            _alarmWrongScan.pause()
            _alarmWrongScan.seekTo(0)
        }
        if (_alarmRepeatedScan.isPlaying) {
            _alarmRepeatedScan.pause()
            _alarmRepeatedScan.seekTo(0)
        }
        if (_alarmVarietyChangeScan.isPlaying) {
            _alarmVarietyChangeScan.pause()
            _alarmVarietyChangeScan.seekTo(0)
        }
        if (_alarmRipScan.isPlaying) {
            _alarmRipScan.pause()
            _alarmRipScan.seekTo(0)
        }

        when (readingState) {
            SUCCESSFUL_READ -> _alarmRightScan.start()
            ERROR_FORMAT_NOT_VALID -> _alarmWrongScan.start()
            ERROR_LABEL_ALREADY_READ -> _alarmRepeatedScan.start()
            ERROR_VARIETY_CHANGE -> _alarmVarietyChangeScan.start()
            RIP_SOUND -> _alarmRipScan.start()
        }
    }
}




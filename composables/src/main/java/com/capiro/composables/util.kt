package com.capiro.composables

import android.media.MediaPlayer

class AlarmEvents {
    val ERROR_FORMAT_NOT_VALID = -1
    val ERROR_LABEL_ALREADY_READ = -2
    val ERROR_VARIETY_CHANGE = -4
    val SUCCESSFUL_READ = 0

    private val _alarmRightScan by lazy {
        MediaPlayer.create(
            BaseApplicationCapiroUI.appContext,
            R.raw.right
        )
    }
    private val _alarmWrongScan by lazy {
        MediaPlayer.create(
            BaseApplicationCapiroUI.appContext,
            R.raw.wrong
        )
    }
    private val _alarmRepeatedScan by lazy {
        MediaPlayer.create(
            BaseApplicationCapiroUI.appContext,
            R.raw.repeated
        )
    }

    private val _alarmVarietyChangeScan by lazy {
        MediaPlayer.create(
            BaseApplicationCapiroUI.appContext,
            R.raw.error
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

        when (readingState) {
            SUCCESSFUL_READ -> _alarmRightScan.start()
            ERROR_FORMAT_NOT_VALID -> _alarmWrongScan.start()
            ERROR_LABEL_ALREADY_READ -> _alarmRepeatedScan.start()
            ERROR_VARIETY_CHANGE -> _alarmVarietyChangeScan.start()
        }
    }
}



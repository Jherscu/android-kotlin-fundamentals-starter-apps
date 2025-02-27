/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.devbyteviewer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseVideo::class], version = 1)
abstract class VideoDatabase : RoomDatabase() {
    abstract val videoDao: VideoDao
}

private var INSTANCE: VideoDatabase? = null

fun getDatabase(context: Context): VideoDatabase {
    return INSTANCE ?: synchronized(VideoDatabase::class.java) {
        val instance: VideoDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                VideoDatabase::class.java,
                "videos"
            ).build()

        INSTANCE = instance

        return INSTANCE as VideoDatabase
    }
}

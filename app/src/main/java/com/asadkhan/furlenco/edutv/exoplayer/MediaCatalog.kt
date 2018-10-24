package com.asadkhan.furlenco.edutv.exoplayer

/*
 * Copyright 2018 Google LLC. All rights reserved.
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

import android.net.Uri
import android.support.v4.media.MediaDescriptionCompat

/**
 * Manages a set of media metadata that is used to create a playlist for [VideoActivity].
 */

open class MediaCatalog(private val list: MutableList<MediaDescriptionCompat>) :
  List<MediaDescriptionCompat> by list {
  
  
  companion object : MediaCatalog(mutableListOf())
  
  init {
    // More creative commons, creative commons videos - https://www.blender.org/about/projects/
    val URL = Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4")
    list.add(
      with(MediaDescriptionCompat.Builder()) {
        setDescription("MP4 loaded over HTTP")
        setMediaId("1")
        setMediaUri(URL)
        setTitle("Short film Big Buck Bunny")
        setSubtitle("Streaming video")
        build()
      })
  }
}

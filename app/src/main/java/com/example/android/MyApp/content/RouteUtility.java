/*
 * Copyright (C) 2017 Google Inc.
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

package com.example.android.MyApp.content;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample song content.
 */
public class RouteUtility {

    // An ArrayList of Songs
    public static final List<Route> ROUTE_ITEM = new ArrayList<>();

    // The ID for the index into song titles.
    public static final String ROUTE_ID_KEY = "item_id";

    // The number of songs.
    private static final int COUNT = 7;

    /**
     * A Song item represents a song title, and song details.
     */
    public static class Route {
        public final String route_title;
        public final String details;
        public final String start;
        public final String end;
        public final String stop_title;

        private Route(String content, String details, String start, String end, String stop_title) {
            this.route_title = content;
            this.details = details;
            this.start = start;
            this.end = end;
            this.stop_title = stop_title;
        }
    }

    /**
     * Add an item to the array.
     *
     * @param item Each song
     */
    private static void addItem(Route item) {
        ROUTE_ITEM.add(item);
    }

    static {
        // Fill the array with songs.
        for (int i = 0; i < COUNT; i++) {
            addItem(createSongAtPosition(i));
        }
    }

    private static Route createSongAtPosition(int position) {
        String newTitle = "";
        String newDetail = "";
        String newStart = "";
        String newEnd = "";
        String newStop = "";
        switch (position) {
            case 0:
                newTitle = "2SchoolAM";
                newDetail = "AM";
                newStart = "8:00";
                newEnd = "9:30";
                newStop = "Stop1\nP\n10";
                break;
            case 1:
                newTitle = "2SchoolAM";
                newDetail = "AM";
                newStart = "8:00";
                newEnd = "9:30";
                newStop = "Stop1\nP\n10";
                break;
            case 2:
                newTitle = "2SchoolAM";
                newDetail = "AM";
                newStart = "8:00";
                newEnd = "9:30";
                newStop = "Stop1\nP\n10";
                break;
            case 3:
                newTitle = "2SchoolAM";
                newDetail = "AM";
                newStart = "8:00";
                newEnd = "9:30";
                newStop = "Stop1\nP\n10";
                break;
            case 4:
                newTitle = "2SchoolAM";
                newDetail = "AM";
                newStart = "8:00";
                newEnd = "9:30";
                newStop = "Stop1\nP\n10";
                break;
            case 5:
                newTitle = "2SchoolAM";
                newDetail = "AM";
                newStart = "8:00";
                newEnd = "9:30";
                newStop = "Stop1\nP\n10";
                break;
            default:
                newTitle = "2SchoolAM";
                newDetail = "AM";
                newStart = "8:00";
                newEnd = "9:30";
                newStop = "Stop1\nP\n10";
                break;
        }
        return new Route(newTitle, newDetail, newStart, newEnd, newStop);
    }
}

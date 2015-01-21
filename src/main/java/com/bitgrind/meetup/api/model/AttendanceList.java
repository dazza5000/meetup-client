package com.bitgrind.meetup.api.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class AttendanceList {
    public final Map<Long, AttendanceInfo> info = new LinkedHashMap<>();
}

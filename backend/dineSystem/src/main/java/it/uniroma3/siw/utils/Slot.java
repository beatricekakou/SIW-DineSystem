package it.uniroma3.siw.utils;

import it.uniroma3.siw.model.SlotEntity;


import java.time.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Slot {

    FIRST_SLOT(1, "First Slot", LocalTime.of(19,0).format(Formatter.slotFormatter())),
    SECOND_SLOT(2, "Second Slot", LocalTime.of(21,0).format(Formatter.slotFormatter())),
    THIRD_SLOT(3, "Third Slot", LocalTime.of(23,0).format(Formatter.slotFormatter()));

    private final int id;
    private final String description;
    private final String time;


    Slot(int id, String description, String time) {
        this.id = id;
        this.description = description;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public SlotEntity toEntity() {
        return new SlotEntity(this.id, this.description, LocalTime.parse(this.time));
    }

    public static List<String> getDescriptions() {
        return Arrays.stream(Slot.values())
                .map(Slot::getDescription)
                .collect(Collectors.toList());
    }

    public static List<String> getTimes() {
        return Arrays.stream(Slot.values())
                .map(Slot::getTime)
                .collect(Collectors.toList());
    }

    public static Slot getSlotFromTimeString(String timeSlot) {
        switch(timeSlot) {
            case "19:00":
                return Slot.FIRST_SLOT;
            case "21:00":
                return Slot.SECOND_SLOT;
            case "23:00":
                return Slot.THIRD_SLOT;
            default:
                return null;
        }
    }
}

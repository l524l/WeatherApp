package com.l524l.weather;

import java.util.List;

public class Settings {
    private List cities;
    private boolean darkMode;
    private boolean compact;

    public List getCities() {
        return cities;
    }

    public void setCities(List cities) {
        this.cities = cities;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public boolean isCompact() {
        return compact;
    }

    public void setCompact(boolean compact) {
        this.compact = compact;
    }
}

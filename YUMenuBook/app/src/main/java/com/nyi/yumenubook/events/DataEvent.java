package com.nyi.yumenubook.events;

import com.nyi.yumenubook.data.VOs.MenuItem;

/**
 * Created by IN-3442 on 20-Nov-16.
 */

public class DataEvent {
    public static class AddCartEvent{

        private String extraMessage;
        private MenuItem menuItem;

        public AddCartEvent(String extraMessage, MenuItem menuItem) {
            this.extraMessage = extraMessage;
            this.menuItem = menuItem;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public MenuItem getMenuItem() {
            return menuItem;
        }
    }
}

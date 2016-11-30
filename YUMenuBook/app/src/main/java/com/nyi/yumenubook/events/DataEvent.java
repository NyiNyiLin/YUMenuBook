package com.nyi.yumenubook.events;

import com.nyi.yumenubook.data.VOs.MenuItemVO;

/**
 * Created by IN-3442 on 20-Nov-16.
 */

public class DataEvent {
    public static class AddCartEvent{

        private String extraMessage;
        private MenuItemVO menuItemVO;

        public AddCartEvent(String extraMessage, MenuItemVO menuItemVO) {
            this.extraMessage = extraMessage;
            this.menuItemVO = menuItemVO;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public MenuItemVO getMenuItemVO() {
            return menuItemVO;
        }
    }
}

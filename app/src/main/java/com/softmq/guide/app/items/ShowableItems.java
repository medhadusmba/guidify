package com.softmq.guide.app.items;

import com.softmq.guide.app.common.ui.Showable;

public interface ShowableItems extends AppItems, Showable {

    Item get(int i);
}

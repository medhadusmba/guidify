package com.softmq.guide.app.items;

import android.os.Bundle;

public class BundleItem extends Item {
    private Bundle bundle;

    public BundleItem(Bundle bundle) {
        super(bundle.getString(Item.TITLE),
                bundle.getString(Item.DESCRIPTION),
                bundle.getString(Item.IMAGE));
        this.bundle = bundle;
    }

    public BundleItem(Item item) {
        super(item);
    }

    public Bundle asBundle() {
        Bundle result = new Bundle();
        result.putString(Item.TITLE, getTitle());
        result.putString(Item.DESCRIPTION, getDescription());
        result.putString(Item.IMAGE, getImage());
        return result;
    }

}

package com.developer.paul.recyclerview.viewModels;

import android.databinding.BaseObservable;
import com.developer.paul.recyclerview.BR;
import com.developer.paul.recyclerview.ItemBinding;
import com.developer.paul.recyclerview.R;

/**
 * Created by Paul on 2/5/17.
 */

public class IconViewModel extends BaseViewModel<IconItemViewModel> {

    public final ItemBinding<IconItemViewModel> iconItem = ItemBinding.of(BR.icon, R.layout.icon);

}

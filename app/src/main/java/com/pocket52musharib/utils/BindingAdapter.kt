package com.pocket52musharib.utils

import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.pocket52musharib.api.LoadingState

/**
 * Created by Musharib Ali on 14/1/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */

@BindingAdapter("isVisible")
fun View.setIsVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
@BindingAdapter("isLoading")
fun ProgressBar.progressVisibility(loadingState: LoadingState?) {
    loadingState?.let {
        isVisible = when(it.status) {
            LoadingState.Status.RUNNING -> true
            LoadingState.Status.SUCCESS -> false
            LoadingState.Status.FAILED -> false
        }
    }
}
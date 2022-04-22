package com.shock.saturdaylifestyle.ui.base.adapter

/**
 * Allows DataBinding to set items into [RecyclerView.Adapter]. Refer [RecyclerView.setItems].
 */
interface BindableAdapter<T> {
    var items: List<T>
}
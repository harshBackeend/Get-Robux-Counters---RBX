package com.gtrbx.gtrbxcounter034.workData

import com.google.firebase.database.PropertyName

data class SpinRowDataHolder(
    @PropertyName("spin_status") val spin_status: String? = "0",
    @PropertyName("spin_big") val spin_big: ArrayList<BigModel>? = null,
    @PropertyName("spin_small") val spin_small: ArrayList<SmallModel>? = null,
    @PropertyName("spin_small_2") val spin_small_2: ArrayList<SmallModel>? = null,
    @PropertyName("spin_pcs") val spin_pcs: String? = null,
) {
    data class ListModel(
        var viewType: Int = 1,
        var titleHolder: String? = null,
        var subTitleHolder: String? = null,
        var bigLayout: BigModel? = null
    )

    data class BigModel(
        @PropertyName("spin_main_image") val spin_main_image: String? = "",
        @PropertyName("spin_p") val spin_p: String? = "",
        @PropertyName("spin_main_button") val spin_main_button: String? = "",
        @PropertyName("spin_main_text") val spin_main_text: String? = "",
        @PropertyName("spin_main_sub_text") val spin_main_sub_text: String? = "",
    )

    data class SmallModel(
        @PropertyName("spin_main_image") val spin_main_image: String? = "",
    )
}

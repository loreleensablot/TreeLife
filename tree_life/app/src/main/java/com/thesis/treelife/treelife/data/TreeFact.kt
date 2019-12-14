package com.thesis.treelife.treelife.data

import android.os.Parcel
import android.os.Parcelable

data class TreeFact(
    val imageResource : Int?,
    val title: String?,
    val description: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(imageResource)
        parcel.writeString(title)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TreeFact> {
        override fun createFromParcel(parcel: Parcel): TreeFact {
            return TreeFact(parcel)
        }

        override fun newArray(size: Int): Array<TreeFact?> {
            return arrayOfNulls(size)
        }
    }
}
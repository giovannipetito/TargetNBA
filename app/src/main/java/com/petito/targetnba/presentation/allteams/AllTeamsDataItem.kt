package com.petito.targetnba.presentation.allteams

import android.os.Parcel
import android.os.Parcelable

class AllTeamsDataItem(

    val id: Int,
    val abbreviation: String?,
    val city: String?,
    val conference: String?,
    val division: String?,
    val full_name: String?,
    val name: String?

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeInt(id)
        parcel.writeString(abbreviation)
        parcel.writeString(city)
        parcel.writeString(conference)
        parcel.writeString(division)
        parcel.writeString(full_name)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AllTeamsDataItem> {
        override fun createFromParcel(parcel: Parcel): AllTeamsDataItem {
            return AllTeamsDataItem(parcel)
        }

        override fun newArray(size: Int): Array<AllTeamsDataItem?> {
            return arrayOfNulls(size)
        }
    }
}
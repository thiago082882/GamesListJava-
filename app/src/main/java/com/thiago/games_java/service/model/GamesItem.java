package com.thiago.games_java.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class GamesItem  implements Parcelable {

    @SerializedName("id")
    public int id;

    @SerializedName("imgUrl")
    public String imgUrl;

    @SerializedName("shortDescription")
    public String shortDescription;

    @SerializedName("title")
    public String title;

    @SerializedName("year")
    public int year;

    public GamesItem(String title) {this.title = title;}

    protected GamesItem(Parcel in) {
        id = in.readInt();
        imgUrl = in.readString();
        shortDescription = in.readString();
        title = in.readString();
        year = in.readInt();
    }
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(imgUrl);
        dest.writeString(shortDescription);
        dest.writeString(title);
        dest.writeInt(year);
    }

    public static final Creator<GamesItem> CREATOR = new Creator<GamesItem>() {
        @Override
        public GamesItem createFromParcel(Parcel in) {
            return new GamesItem(in);
        }

        @Override
        public GamesItem[] newArray(int size) {
            return new GamesItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}

package co.fabrk.booklisting.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by ebal on 17/12/16.
 */

public class GBook implements Parcelable {

    public ArrayList<String> getAuthors() {
        return Authors;
    }

    public String getTitle() {
        return Title;
    }

    private String Title;

    private ArrayList<String> Authors;

    public GBook(String title, ArrayList<String> authors) {
        Authors = authors;
        Title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Title);
        dest.writeStringList(this.Authors);
    }

    public GBook() {
    }

    private GBook(Parcel in) {
        this.Title = in.readString();
        this.Authors = in.createStringArrayList();
    }

    public static final Parcelable.Creator<GBook> CREATOR = new Parcelable.Creator<GBook>() {
        @Override
        public GBook createFromParcel(Parcel source) {
            return new GBook(source);
        }

        @Override
        public GBook[] newArray(int size) {
            return new GBook[size];
        }
    };

}

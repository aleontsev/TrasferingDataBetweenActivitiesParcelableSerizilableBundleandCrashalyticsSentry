package service_test.service_test;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

//class implementing parcelable to transfer object
public class DataParcelable implements Parcelable {

    final static String LOG_TAG ="mLogs";

    public String s;
    public int i;

    //simple constructor for our class
    public DataParcelable(String _s, int _i){
        s = _s;
        i = _i;
    }

    //not using this method
    @Override
    public int describeContents() {
        return 0;
    }

    //packing our object
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(s);
        dest.writeInt(i);
    }

    //recreating object from parcel
    public static final Creator<DataParcelable> CREATOR = new Creator<DataParcelable>() {
        @Override
        public DataParcelable createFromParcel(Parcel in) {
            return new DataParcelable(in);
        }

        @Override
        public DataParcelable[] newArray(int size) {
            return new DataParcelable[size];
        }
    };

    //constructor reading info from parcel
    protected DataParcelable(Parcel in) {
        s = in.readString();
        i = in.readInt();
    }
}

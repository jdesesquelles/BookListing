package co.fabrk.booklisting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import co.fabrk.booklisting.model.GBook;

/**
 * Created by ebal on 21/12/16.
 */
public class BookListAdapter extends BaseAdapter {
    public ArrayList<GBook> mBookArrayList = new ArrayList<>();
    LayoutInflater layoutInflater;

    public BookListAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return mBookArrayList.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater
                .inflate(R.layout.book_item, viewGroup, false);
        BookAdapterViewHolder holder = new BookAdapterViewHolder(view);
        holder.textview_title.setText(mBookArrayList.get(i).getTitle());
        String authors = new String();
        for (int j = 0; j < mBookArrayList.get(i).getAuthors().size(); j++) {
            if (null == authors) {
                authors = mBookArrayList.get(i).getAuthors().get(j);
            } else authors = authors + ", " + mBookArrayList.get(i).getAuthors().get(j);
        }
        holder.textview_author.setText(authors);
        return view;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    public void swapData(ArrayList<GBook> bookArrayList) {
        mBookArrayList = bookArrayList;
        notifyDataSetChanged();
    }

    public class BookAdapterViewHolder {
        public final TextView textview_title;
        public final TextView textview_author;

        public BookAdapterViewHolder(View view) {
            textview_title = (TextView) view.findViewById(R.id.textview_title);
            textview_author = (TextView) view.findViewById(R.id.textview_author);
        }
    }
}

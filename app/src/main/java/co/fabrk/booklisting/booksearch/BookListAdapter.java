package co.fabrk.booklisting.booksearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import co.fabrk.booklisting.R;
import co.fabrk.booklisting.model.GBook;

/**
 * Created by ebal on 21/12/16.
 */
class BookListAdapter extends BaseAdapter {
    public ArrayList<GBook> mBookArrayList = new ArrayList<>();
    private final LayoutInflater layoutInflater;

    public BookListAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }


    @Override
    public int getCount() {
        return mBookArrayList.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (null == view) {
            view = layoutInflater
                    .inflate(R.layout.book_item, viewGroup, false);
        }

        BookAdapterViewHolder holder = new BookAdapterViewHolder(view);
        holder.textview_title.setText(mBookArrayList.get(i).getTitle());
        String authors = "";
        for (int j = 0; j < mBookArrayList.get(i).getAuthors().size(); j++) {
            if (j == 0) {
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
        return mBookArrayList.get(i);
    }

    public void swapData(ArrayList<GBook> bookArrayList) {
        if (null == bookArrayList) mBookArrayList.clear();
        else mBookArrayList = bookArrayList;
        notifyDataSetChanged();
    }

    private class BookAdapterViewHolder {
        private final TextView textview_title;
        private final TextView textview_author;

        private BookAdapterViewHolder(View view) {
            textview_title = (TextView) view.findViewById(R.id.textview_title);
            textview_author = (TextView) view.findViewById(R.id.textview_author);
        }
    }
}

package co.fabrk.booklisting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import co.fabrk.booklisting.model.GBook;

public class SearchBooks extends AppCompatActivity {

    BookPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_books);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new BookPresenter((View) findViewById(R.id.root_view));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_books, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // RecyclerView Adapter
    public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookAdapterViewHolder> {
        private ArrayList<GBook> mBookArrayList;

        public BookAdapter(final ArrayList<GBook> bookArrayList) {
            mBookArrayList = bookArrayList;
        }

        public BookAdapter() {
            mBookArrayList = null;
        }

        public class BookAdapterViewHolder extends RecyclerView.ViewHolder {
            public final TextView textview_title;
            public final TextView textview_author;

            public BookAdapterViewHolder(View view) {
                super(view);
                textview_title = (TextView) view.findViewById(R.id.textview_title);
                textview_author = (TextView) view.findViewById(R.id.textview_author);
            }
        }

        @Override
        public int getItemCount() {
            if (null == mBookArrayList) {
                return 0;
            }
            else return mBookArrayList.size();
        }

        @Override
        public void onBindViewHolder(BookAdapterViewHolder holder, int position) {
            holder.textview_title.setText(mBookArrayList.get(position).getTitle());
            String authors = new String();
            for (int i = 0; i < mBookArrayList.get(position).getAuthors().size(); i++) {
                if (0 == i) {
                    authors = mBookArrayList.get(position).getAuthors().get(i);
                } else authors = authors + ", " + mBookArrayList.get(position).getAuthors().get(i);
            }
            holder.textview_author.setText(authors);
        }

        @Override
        public BookAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.book_item, parent, false);
            return new BookAdapterViewHolder(view);
        }

        public void swapData(ArrayList<GBook> bookArrayList) {
            mBookArrayList = bookArrayList;
            notifyDataSetChanged();
        }
    }

    private class BookPresenter implements SearchBookPresenter, FetchBookList.BookListCaller {

        private BookView mBookView;
        int currentPage =0;

        public BookPresenter(View view) {
            mBookView = new BookView(view);
            mBookView.searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadBookList(mBookView.search_books_edit_text_search.getText().toString());
                }
            });
        }

        @Override
        public void loadBookList(String query) {
            FetchBookList fetchBookListTask = new FetchBookList(this);
            fetchBookListTask.execute(query, String.valueOf(currentPage));
        }

        @Override
        public void updateBookList(ArrayList<GBook> bookArrayList) {
            mBookView.showBookList(new BookAdapter(bookArrayList));
        }

        private class BookView implements SearchBookView{
            View mRootView;
            EditText search_books_edit_text_search;
            ImageView emptyView;
            ImageButton searchButton;
            RecyclerView books_list_view;

            public BookView(View rootView) {
                this.mRootView = rootView;
                search_books_edit_text_search = (EditText) findViewById(R.id.search_books_edit_text_search);
                searchButton = (ImageButton) findViewById(R.id.searchButton);
                emptyView = (ImageView) findViewById(R.id.empty_view);
                books_list_view = (RecyclerView) findViewById(R.id.books_recycler_view);
                setupRecyclerView(books_list_view);
            }

            private void setupRecyclerView(RecyclerView recyclerView) {
                recyclerView.setLayoutManager(new LinearLayoutManager(mRootView.getContext()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(new BookAdapter());
            }

            @Override
            public void showBookList(BookAdapter bookAdapter) {
                mBookView.books_list_view.swapAdapter(bookAdapter, true);
            }
        }

    }


    private interface SearchBookView {
        void showBookList(BookAdapter bookAdapter);
    }

    private interface SearchBookPresenter {
        void loadBookList(String query);
    }
}

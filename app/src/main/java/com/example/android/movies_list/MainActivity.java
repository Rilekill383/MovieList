package com.example.android.movies_list;

        import android.app.AlertDialog;

        import android.view.View;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;
        import android.net.Uri;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.Spinner;

        import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Button AddMov;
    private ListView LV;

    private String[] movies = {"The GodFather PartI", "No Country For Old Men", "Spirited Away", "Reservoir Dogs",
            "Fight Club", "Django Unchained", "The Good, the Bad and the Ugly", "Goodfellas", "Monty Python and the Holy Grail"};
    private String[] URLs = {"http://www.imdb.com/title/tt0068646/", "http://www.imdb.com/title/tt0477348/",
            "http://www.imdb.com/title/tt0245429/", "http://www.imdb.com/title/tt0105236/", "http://www.imdb.com/title/tt0137523/",
            "http://www.imdb.com/title/tt1853728/", "http://www.imdb.com/title/tt0060196/", "http://www.imdb.com/title/tt0099685/",
            "http://www.imdb.com/title/tt0071853/"};

    public ArrayList<String> MovieTitlesList;
    public ArrayList<String> MovieCodesList;

    private int DeletedMovieVariable;
    private AlertDialog DScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddMov = (Button) findViewById(R.id.add_item_button);
        LV = (ListView) findViewById(R.id.AwesomeList);
        MovieTitlesList = new ArrayList<String>();
        MovieCodesList = new ArrayList<String>();

        ArrayAdapter<String> adapter1;
        ArrayAdapter<String> adapter2;
        adapter1 = new ArrayAdapter<String> (this, R.layout.list_item_view, movies);
        adapter2 = new ArrayAdapter<String> (this, R.layout.list_item_view, URLs);
        LV.setAdapter(adapter1);


        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int progress, long l) {
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(MovieCodesList.get(progress)));
                startActivity(in);
            }
        });

        //When the list is held down, and the 'int' is deleted
        LV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int progress, long l) {
                DeletedMovieVariable = progress;
                DScreen.show();
                return true;
            }
        });

        AddMov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain2Activity();
            }
        });

        for (int i=0; i<movies.length; i++) {
            MovieTitlesList.add(movies[i]);
            MovieCodesList.add(URLs[i]);
        }

        Creating_the_Alert_Dialog();
    }

    //The behavior with the dialog screen from the LongClick is here.
    public void Creating_the_Alert_Dialog() {
        AlertDialog.Builder DeletePrompt = new AlertDialog.Builder(MainActivity.this);
        DeletePrompt.setTitle("WOAH, hold your horse's cowboy!");
        DeletePrompt.setMessage("Are you sure you want to delete this movie?");
        DeletePrompt.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MovieTitlesList.remove(DeletedMovieVariable);
                MovieCodesList.remove(DeletedMovieVariable);

                //I believe this updates the Arraylist with the new updated string?

                ArrayAdapter<String> adapter1;
                adapter1 = new ArrayAdapter<String> (getApplicationContext(), R.layout.list_item_view, MovieTitlesList);
                LV.setAdapter(adapter1);
            }
        });

        DeletePrompt.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        DScreen = DeletePrompt.create();
    }


    public void openMain2Activity() {
        Intent open2M = new Intent(this, Main2Activity.class);
        startActivityForResult(open2M, 2);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        MovieTitlesList.add(data.getStringExtra("Titles"));
        MovieCodesList.add(data.getStringExtra("Codes"));
        ArrayAdapter<String> adapter1;
        adapter1 = new ArrayAdapter<String>(this, R.layout.list_item_view, MovieTitlesList);
        LV.setAdapter(adapter1);
    }

    //// Persistant Storage ?
    //SharedPreferences
    //        p = getPreferences(Context.MODE_PRIVATE);
    //String s = p.getString("Label", "error");
    //// or int c = p.getInt("Label", -1);  ????(depends on what's used)????
    //Map<String,?> map = p.getAll();
    //// End Persistant Storage ?
    //It may need to be

    //@Override
    //public void onStop()  {
    //super.onStop();

    //SharedPreferences.Editor  e = p.edit();
    // e.putString(MovieTitles, MovieCodes);

    // e.apply();
    //}

}

//**This is a template for what needs to be done next**//
//**In the Shared Preferences attribute**//

//  Persistant stroage;
//      Sheared Preferences   p = getPreferences(context.MODE_PRIVATE);
//           p.getInt("Label", -1);
//           p.getInt("Label");
//      Map<String(or int)>   map = p.getAll();
//
//     (to add things)  SharedPreferences.Editor e = p.edit();
//                      e.putString(s1, s2, ..., ...);
//                   e.apply();




























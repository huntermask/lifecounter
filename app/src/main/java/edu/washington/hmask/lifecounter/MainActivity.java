package edu.washington.hmask.lifecounter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    ListView playerListView;

    private static final int INITIAL_PLAYERS_COUNT = 4;
    private static final int MIN_PLAYERS_COUNT = 2;
    private static final int MAX_PLAYERS_COUNT = 8;

    private static final String SAVED_ADAPTER_INSTANCE = "savedAdapterInstance";

    private PlayerArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerListView = (ListView) findViewById(R.id.playerListView);

        List<Player> initialPlayers;
        if (null != savedInstanceState && savedInstanceState.containsKey(SAVED_ADAPTER_INSTANCE)) {
            initialPlayers = savedInstanceState.getParcelableArrayList(SAVED_ADAPTER_INSTANCE);
            adapter = new PlayerArrayAdapter(this, R.layout.player, initialPlayers);
        } else {
            initialPlayers = new ArrayList<>();
            for (int i = 0; i < INITIAL_PLAYERS_COUNT; i++) {
                initialPlayers.add(new Player("Player " + (i + 1)));
            }
            adapter = new PlayerArrayAdapter(this, R.layout.player, initialPlayers);
        }

        playerListView.setAdapter(adapter);

        Button addPlayerButton = (Button) findViewById(R.id.addPlayerButton);
        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getCount() < MAX_PLAYERS_COUNT) {
                    adapter.add(new Player("Player " + (adapter.getCount() + 1)));
                    if (adapter.getCount() == MAX_PLAYERS_COUNT) {
                        v.setEnabled(false);
                    }
                    if (adapter.getCount() > MIN_PLAYERS_COUNT) {
                        Button removePlayerButton = (Button) findViewById(R.id.removePlayerButton);
                        removePlayerButton.setEnabled(true);
                    }
                }
            }
        });

        Button removePlayerButton = (Button) findViewById(R.id.removePlayerButton);
        removePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getCount() > MIN_PLAYERS_COUNT) {
                    adapter.remove(adapter.getItem(adapter.getCount() - 1));
                    if (adapter.getCount() == MIN_PLAYERS_COUNT) {
                        v.setEnabled(false);
                    }
                    if (adapter.getCount() < MAX_PLAYERS_COUNT) {
                        Button addPlayerButton = (Button) findViewById(R.id.addPlayerButton);
                        addPlayerButton.setEnabled(true);
                    }
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        ArrayList<Player> players = new ArrayList<>(adapter.getPlayers());
        savedInstanceState.putParcelableArrayList(SAVED_ADAPTER_INSTANCE, players);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}

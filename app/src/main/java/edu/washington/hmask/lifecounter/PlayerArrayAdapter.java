package edu.washington.hmask.lifecounter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Hunter Mask on 4/18/15.
 */
public class PlayerArrayAdapter extends ArrayAdapter<Player> implements View.OnClickListener {

    List<Player> players = new ArrayList<>();
    private final Context context;

    public PlayerArrayAdapter(Context context, int resourceId,
                              List<Player> objects) {
        super(context, resourceId, objects);
        this.players = objects;
        this.context = context;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void add(Player object) {
        super.add(object);
        players.add(object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.player, parent, false);
        rowView.setTag(position);
        Player p = getItem(position);
        TextView lifeCountView = (TextView) rowView.findViewById(R.id.lifeCountView);
        lifeCountView.setText(p.getLifeCount() + (p.getLifeCount() == 1 ? " life" : " lives") + " remaining");

        EditText playerNameView = (EditText) rowView.findViewById(R.id.playerNameView);
        playerNameView.setTag(position);
        playerNameView.setText(p.getName());
        playerNameView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    int position = (Integer) (v.getTag());

                    Player p = getItem(position);

                    p.setName(v.getText().toString());

                }
                return false;
            }
        });

        Button plusButton = (Button) rowView.findViewById(R.id.plusButton);
        plusButton.setTag(position);
        plusButton.setOnClickListener(this);

        Button minusButton = (Button) rowView.findViewById(R.id.minusButton);
        minusButton.setTag(position);
        minusButton.setOnClickListener(this);

        Button plusFiveButton = (Button) rowView.findViewById(R.id.plusFiveButton);
        plusFiveButton.setTag(position);
        plusFiveButton.setOnClickListener(this);

        Button minusFiveButton = (Button) rowView.findViewById(R.id.minusFiveButton);
        minusFiveButton.setTag(position);
        minusFiveButton.setOnClickListener(this);

        return rowView;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) (v.getTag());
        Player p = getItem(position);

        switch (v.getId()) {
            case R.id.plusButton:
                p.setLifeCount(p.getLifeCount() + 1);
                break;
            case R.id.plusFiveButton:
                p.setLifeCount(p.getLifeCount() + 5);
                break;
            case R.id.minusFiveButton:
                p.setLifeCount(p.getLifeCount() - 4);
            case R.id.minusButton:
                p.setLifeCount(p.getLifeCount() - 1);
                if (p.getLifeCount() <= 0) {
                    Toast toast = Toast.makeText(context, p.getName() + " LOSES!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            default:
                break;
        }
        TextView lifeCountView = (TextView) ((View) v.getParent()).findViewById(R.id.lifeCountView);
        lifeCountView.setText(p.getLifeCount() + (p.getLifeCount() == 1 ? " life" : " lives") + " remaining");
    }
}

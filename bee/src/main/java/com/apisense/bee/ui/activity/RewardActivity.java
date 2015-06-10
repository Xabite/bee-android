package com.apisense.bee.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.apisense.bee.R;
import com.apisense.bee.games.BeeGameActivity;
import com.apisense.bee.games.BeeGameManager;
import com.apisense.bee.widget.ApisenseTextView;

/**
 * Created by Warnant on 08-03-15.
 */
public class RewardActivity extends BeeGameActivity implements View.OnClickListener {

    private static final int MISSION_LEARDBOARD_REQUEST_CODE = 1;
    private static final int MISSION_ACHIEVEMENTS_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_reward);

        Toolbar toolbar = (Toolbar) findViewById(R.id.material_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        setSupportActionBar(toolbar);

        LinearLayout layoutAchievements = (LinearLayout) findViewById(R.id.reward_game_badge_panel);
        layoutAchievements.setOnClickListener(this);

        //ApisenseTextView apvPoints = (ApisenseTextView) findViewById(R.id.reward_game_points);
        //apvPoints.setText("" + 0);

        ApisenseTextView apvAchievements = (ApisenseTextView) findViewById(R.id.reward_game_achievements);
        apvAchievements.setText("" + BeeGameManager.getInstance().getAchievementUnlockCount());

        //ApisenseTextView apvThanks = (ApisenseTextView) findViewById(R.id.reward_game_thanks);
        //apvAchievements.setText("" + 0);
    }

    public void doGoToHome(View homeButton) {
        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.reward_game_badge_panel:
                startActivityForResult(BeeGameManager.getInstance().getAchievementList(), MISSION_ACHIEVEMENTS_REQUEST_CODE);
                break;
           /* case R.id.reward_monthly_button:
                Intent intent = new Intent(getApplicationContext(), RewardDetailsActivity.class);
                startActivity(intent);
                finish();
                break;*/
            default:
                break;
        }

    }
}
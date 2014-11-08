package excusebot.felixware.com.excusebot;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends Activity
{
    private LinearLayout mLayout;
    private Button mButton;
    private String[] mPhrases, mSubjects, mVerbs, mObjects;
    private Random mRand = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

        setupArrays();
    }

    private void bindViews()
    {
        mLayout = (LinearLayout) findViewById(R.id.layout_excuses);
        mButton = (Button) findViewById(R.id.button_excuse);
    }

    private void setupArrays()
    {
        mPhrases = getResources().getStringArray(R.array.phrase);
        mSubjects = getResources().getStringArray(R.array.subject);
        mVerbs = getResources().getStringArray(R.array.verb);
        mObjects = getResources().getStringArray(R.array.object);
    }

    public void onExcuseClicked(View v)
    {
        mButton.setText(R.string.excuse_button_after);

        int phraseNumber = randInt(3);
        String phrase = mPhrases[phraseNumber];
        String subject = mSubjects[randInt(11)];
        if (phraseNumber == 0) {
            subject = subject.substring(0, 1).toUpperCase() + subject.substring(1);
        }
        String verb = mVerbs[randInt(10)];
        String object = mObjects[randInt(10)];

        String excuse = String.format(phrase, subject, verb, object);

        LinearLayout excuseView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.view_excuse, null);

        ((TextView) excuseView.findViewById(R.id.textview_excuse)).setText(excuse);

        mLayout.addView(excuseView, 0);
    }

    public int randInt(int max)
    {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = mRand.nextInt(max);

        return randomNum;
    }
}

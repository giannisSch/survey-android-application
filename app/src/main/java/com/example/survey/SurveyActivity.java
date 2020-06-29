package com.example.survey;

import android.app.Fragment;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.shuhart.stepview.StepView;

public class SurveyActivity extends AppCompatActivity{

    private StepView stepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        FirstQuestionFragment firstQuestionFragment = new FirstQuestionFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, firstQuestionFragment, "firstQuestionFragment");
        transaction.addToBackStack("firstQuestionFragment");
        transaction.commit();

        initStepView();
     }

     public void initStepView(){
         StepView stepView = findViewById(R.id.step_view);
         stepView.setStepsNumber(3);
         this.stepView = stepView;
     }

    @Override
    public void onBackPressed() {
        int stackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (stackEntryCount > 1) {
            stepView.done(false);
            stepView.go(stackEntryCount - 2, true);
        }
        super.onBackPressed();
    }
}

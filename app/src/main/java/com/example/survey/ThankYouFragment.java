package com.example.survey;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.shuhart.stepview.StepView;

public class ThankYouFragment extends Fragment {

    private AnimatedVectorDrawableCompat doneVectorCompat;
    private AnimatedVectorDrawable doneVector;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thank_you, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        ImageView done = getView().findViewById(R.id.doneImageView);
        Drawable drawable = done.getDrawable();
        if (drawable instanceof AnimatedVectorDrawableCompat){
            doneVectorCompat = (AnimatedVectorDrawableCompat) drawable;
            doneVectorCompat.start();
        }
        if (drawable instanceof AnimatedVectorDrawable){
            doneVector = (AnimatedVectorDrawable) drawable;
            doneVector.start();
        }
        StepView stepView = getActivity().findViewById(R.id.step_view);
        stepView.go(2, true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Thread.sleep(2000);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((SurveyActivity) getActivity()).initStepView();
                            gotoFirstQuestionFragment();
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void gotoFirstQuestionFragment(){
        FragmentManager  manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        manager.popBackStackImmediate("firstQuestionFragment", 0);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("firstQuestionFragment");
        ft.commit();
    }

}

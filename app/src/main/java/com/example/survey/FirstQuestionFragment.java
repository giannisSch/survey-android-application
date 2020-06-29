package com.example.survey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.shuhart.stepview.StepView;

import static android.content.Context.MODE_PRIVATE;


public class FirstQuestionFragment extends Fragment {


    private String branchId;
    private ImageButton veryHappyImageButton;
    private ImageButton happyImageButton;
    private ImageButton notHappyImageButton;
    private ImageButton badImageButton;

    public FirstQuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            branchId = getArguments().getString("BranchId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_question, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        View view = getView();
        if(view != null) { //view can be null
            //stepView
            StepView stepView = getActivity().findViewById(R.id.step_view);
            stepView.done(false);

            veryHappyImageButton = view.findViewById(R.id.veryHappyImageButton);
            happyImageButton = view.findViewById(R.id.happyImageButton);
            notHappyImageButton = view.findViewById(R.id.notHappyImageButton);
            badImageButton = view.findViewById(R.id.badImageButton);

            Glide.with(this).load(R.drawable.very_happy).into(veryHappyImageButton);
            Glide.with(this).load(R.drawable.happy).into(happyImageButton);
            Glide.with(this).load(R.drawable.not_happy).into(notHappyImageButton);
            Glide.with(this).load(R.drawable.bad).into(badImageButton);


            veryHappyImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoSecondQuestionFragment("veryHappy");
                }
            });
            happyImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoSecondQuestionFragment("happy");
                }
            });
            notHappyImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoSecondQuestionFragment("notHappy");
                }
            });
            badImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoSecondQuestionFragment("bad");
                }
            });



        }
    }

    private void gotoSecondQuestionFragment(String firstQuestionAnswer){

        SecondQuestionFragment secondQuestionFragment = SecondQuestionFragment.newInstance(firstQuestionAnswer);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            secondQuestionFragment.setSharedElementEnterTransition(new DetailsTransition());
            secondQuestionFragment.setEnterTransition(new Fade());
            setExitTransition(new Fade());
            secondQuestionFragment.setSharedElementReturnTransition(new DetailsTransition());
        }

        ImageButton transitionImageButton = getImageButton(firstQuestionAnswer);
        transitionImageButton.setTransitionName("reactionImageButton");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.addSharedElement(transitionImageButton, "reactionImageButton");
        transaction.replace(R.id.frameLayout, secondQuestionFragment, "secondQuestionFragment");
        transaction.addToBackStack("secondQuestionFragment");
        transaction.commit();
    }


    public class DetailsTransition extends TransitionSet {
        public DetailsTransition() {
            setOrdering(ORDERING_TOGETHER);
            addTransition(new ChangeBounds()).
                    addTransition(new ChangeTransform()).
                    addTransition(new ChangeImageTransform());
        }
    }

    public ImageButton getImageButton(String name){
        if (name.equals("veryHappy")){
            return veryHappyImageButton;
        }else if (name.equals("happy")){
            return happyImageButton;
        }else if (name.equals("notHappy")){
            return notHappyImageButton;
        }
        return badImageButton;
    }

}

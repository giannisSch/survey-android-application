package com.example.survey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.shuhart.stepview.StepView;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;


public class SecondQuestionFragment extends Fragment {

    private String firstQuestionAnswer;
    private CheckBox answer1CheckBox, answer2CheckBox, answer3CheckBox, answer4CheckBox;
    private StepView stepView;

    public SecondQuestionFragment() {
        // Required empty public constructor
    }

    public static SecondQuestionFragment newInstance(String firstQuestionAnswer) {
        SecondQuestionFragment fragment = new SecondQuestionFragment();
        Bundle args = new Bundle();
        args.putString("firstQuestionAnswer", firstQuestionAnswer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            firstQuestionAnswer = getArguments().getString("firstQuestionAnswer");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_question, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        View view = getView();

        if(view != null) { //view can be null

            stepView = getActivity().findViewById(R.id.step_view);
            stepView.go(1, true);

            TextView questionTextView = view.findViewById(R.id.questionTextField);
            ImageButton reactionImageButton = view.findViewById(R.id.reactionImageButton);

            if (firstQuestionAnswer.equals("veryHappy")){
                questionTextView.setText("What was it that particularly pleased you?");
                Glide.with(this).load(R.drawable.very_happy).into(reactionImageButton);
            }else if (firstQuestionAnswer.equals("happy")){
                questionTextView.setText("What was it that particularly pleased you?");
                Glide.with(this).load(R.drawable.happy).into(reactionImageButton);
            }else if (firstQuestionAnswer.equals("notHappy")){
                questionTextView.setText("What was it that particularly displeased you?");
                Glide.with(this).load(R.drawable.not_happy).into(reactionImageButton);
            }else{ // firstQuestionAnswer.equals("bad")
                questionTextView.setText("What was it that particularly displeased you?");
                Glide.with(this).load(R.drawable.bad).into(reactionImageButton);
            }

            answer1CheckBox = view.findViewById(R.id.asnwer1CheckBox);
            answer2CheckBox = view.findViewById(R.id.asnwer2CheckBox);
            answer3CheckBox = view.findViewById(R.id.asnwer3CheckBox);
            answer4CheckBox = view.findViewById(R.id.asnwer4CheckBox);
            Button finishButton = view.findViewById(R.id.finishButton);
            finishButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JSONObject json = null;
                    try {
                        json = createJson();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    makePostRequestToServer(json);
                }
            });

        }
    }
    private JSONObject createJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("VHappy", firstQuestionAnswer.equals("veryHappy") ? 1 : 0);
        json.put("Happy", firstQuestionAnswer.equals("happy") ? 1 : 0);
        json.put("NotHappy", firstQuestionAnswer.equals("not_happy") ? 1 : 0);
        json.put("Bad", firstQuestionAnswer.equals("bad") ? 1 : 0);
        json.put("An1", answer1CheckBox.isChecked() ? 1 : 0);
        json.put("An2", answer2CheckBox.isChecked() ? 1 : 0);
        json.put("An3", answer3CheckBox.isChecked() ? 1 : 0);
        json.put("An4", answer4CheckBox.isChecked() ? 1 : 0);
        json.put("time", System.currentTimeMillis()/1000);
        Log.i("JSON", json.toString());
        return json;
    }

    private void makePostRequestToServer(JSONObject json){

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://ptsv2.com/t/o6v42-1593421908";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(getContext(), "Please check internet connection", Toast.LENGTH_LONG).show();
                        }
                    }
                }) {
                    @Override
                    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                        if (response.statusCode == 200) {
                            gotoThankYouFragment();
                        } else {
                            Toast.makeText(getContext(), "Oops Please try again", Toast.LENGTH_LONG).show();
                            gotoFirstQuestionFragment();
                        }
                        return super.parseNetworkResponse(response);
                    }
        };
        queue.add(jsonObjectRequest);
    }
    private void gotoThankYouFragment(){
        //stepView.done(true);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, new ThankYouFragment(), "thankYouFragment" );
        transaction.addToBackStack("thankYouFragment");
        transaction.commit();
    }

    private void gotoFirstQuestionFragment(){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        manager.popBackStackImmediate("firstQuestionFragment", 0);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack("firstQuestionFragment");
        ft.commit();
    }


}


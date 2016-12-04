package kz.nixwins.periodictable.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.text.WordUtils;

import kz.nixwins.periodictable.R;
import kz.nixwins.periodictable.helper.Animator;
import kz.nixwins.periodictable.helper.ElementColor;
import kz.nixwins.periodictable.model.Element;
import kz.nixwins.periodictable.model.ElementSQL;

/**
 * Created by nixwins on 12/1/16.
 */

public class QuestionActivity extends AppCompatActivity {

    private LinearLayout questionContainerLl;
    private TextView name;
    private TextView lantinName;
    private TextView atomicNumber;
    private TextView atomicWeight;
    private TextView symbol;

    private Button checkAnswerBtn;
    private EditText answer;
    private Button questionNextBtn;

    private  ElementSQL elementSQL;
    private  Element element;
    private  Animator animator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_layout);

        animator = new Animator(getApplicationContext());
        elementSQL  = new ElementSQL(this);
        element      = elementSQL.getRandomRow();


        questionContainerLl = (LinearLayout) findViewById(R.id.question_contanierLl);
        ElementColor.setElementBackgroundColor(element.getElectronEnergyType(), questionContainerLl);

        checkAnswerBtn = (Button) findViewById(R.id.question_check_btn);
        questionNextBtn = (Button) findViewById(R.id.question_next);
        answer         = (EditText) findViewById(R.id.question_answer);
        name = (TextView) findViewById(R.id.question_name);
        lantinName = (TextView) findViewById(R.id.question_latin_name);
        atomicNumber = (TextView) findViewById(R.id.question_atomic_nubmer);
        atomicWeight = (TextView) findViewById(R.id.question_atomic_weight);
        symbol = (TextView) findViewById(R.id.question_symbol);

        symbol.setText("?");
        name.setText(WordUtils.capitalize(element.getName()));
        lantinName.setText(WordUtils.capitalize(element.getLatinName()));
        atomicNumber.setText(Integer.toString(element.getAtomicNumber()));
        atomicWeight.setText(Double.toString(element.getAtomicWeight()));

        answer.setOnEditorActionListener(new EditText.OnEditorActionListener() {
           @Override
           public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

               if(i == EditorInfo.IME_ACTION_DONE){

                   checkAnswerBtn.performClick();
                   return true;
               }
               return false;
           }
       });

        checkAnswerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String answerStr = answer.getText().toString();

                boolean flag = checkAnswer(answerStr);

                if(flag){

                    Toast.makeText(getApplicationContext(),  R.string.question_right_answer, Toast.LENGTH_SHORT).show();

                    answer.setText("");

                    symbol.setText(element.getSymbol());
                    animator.animZoomIn(symbol);

                    checkAnswerBtn.setVisibility(View.INVISIBLE);
                    questionNextBtn.setVisibility(View.VISIBLE);

                    questionNextBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            questionNextBtn.setVisibility(View.INVISIBLE);
                            checkAnswerBtn.setVisibility(View.VISIBLE);

                            animator.animLeftToRight(questionContainerLl);
                            nextQuestion();
                        }
                    });

                }else{

                    Toast.makeText(getApplicationContext(), R.string.question_wrong_answer, Toast.LENGTH_SHORT).show();
                    animator.animBlink(questionContainerLl);

                }
            }
        });

        /* Setting Toolbar */

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView toolbarTitle;
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.questionToolbarTitle);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean checkAnswer(String answerStr) {

        if (answerStr.toLowerCase().equals(element.getSymbol().toLowerCase())){
            symbol.setText(element.getSymbol());
            animator.animZoomIn(symbol);
            return true;
        }

        else
            return false;
    }

    private void nextQuestion(){

        element = elementSQL.getRandomRow();
        ElementColor.setElementBackgroundColor(element.getElectronEnergyType(), questionContainerLl);
        symbol.setText("?");

        name.setText(WordUtils.capitalize(element.getName()));
        lantinName.setText(WordUtils.capitalize(element.getLatinName()));
        atomicNumber.setText(Integer.toString(element.getAtomicNumber()));
        atomicWeight.setText(Double.toString(element.getAtomicWeight()));

    }
}

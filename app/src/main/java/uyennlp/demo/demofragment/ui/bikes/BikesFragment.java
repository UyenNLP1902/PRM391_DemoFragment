package uyennlp.demo.demofragment.ui.bikes;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import java.sql.SQLException;
import java.util.List;

import uyennlp.demo.demofragment.R;
import uyennlp.demo.demofragment.daos.StudentDAO;
import uyennlp.demo.demofragment.dtos.StudentDTO;

public class BikesFragment extends Fragment {

    private static String SEARCH_AGE, RESULT_TEXT;

    private Button btnSearch;
    private EditText edtSearchAge;
    private TextView txtResult;
    private HomeViewModel homeViewModel;
    private LinearLayout layoutResult;

    private Handler handler = new Handler();
    private Runnable runnable;

    public BikesFragment() {}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_bikes, container, false);

        FragmentActivity activity = getActivity();
        btnSearch = activity.findViewById(R.id.btnSearch);
        edtSearchAge = activity.findViewById(R.id.edtSearchAge);
        txtResult = activity.findViewById(R.id.txtResult);
//        layoutResult = activity.findViewById(R.id.layoutResult);
//        layoutResult.setVisibility(View.GONE);

//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        btnSearch = getActivity().findViewById(R.id.btnSearch);
        System.out.println(btnSearch.getText().toString());
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnSearch.getText().toString().equals("Search")) {
                    // button search

                    edtSearchAge = getActivity().findViewById(R.id.edtSearchAge);
                    txtResult = getActivity().findViewById(R.id.txtResult);
                    int age = Integer.parseInt(edtSearchAge.getText().toString());

                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                StudentDAO dao = new StudentDAO();
                                StudentDTO dto = dao.searchFirstRecordByAge(age);

                                if (dto != null) {
                                    handler.removeCallbacks(this);
                                    Toast.makeText(getContext(), "Found!", Toast.LENGTH_LONG).show();
                                    txtResult.setText("Found!");
                                    btnSearch.setText(R.string.btn_search);

//                                    layoutResult = getActivity().findViewById(R.id.layoutResult);
//                                    layoutResult.setVisibility(View.VISIBLE);
//                                    TextView txtResultId = getActivity().findViewById(R.id.txtResultId);
//                                    TextView txtResultName = getActivity().findViewById(R.id.txtResultName);
//                                    TextView txtResultAge = getActivity().findViewById(R.id.txtResultAge);
//                                    txtResultId.setText(dto.getId());
//                                    txtResultName.setText(dto.getName());
//                                    txtResultAge.setText(dto.getAge());
                                } else {
                                    btnSearch.setText(R.string.btn_stop);
                                    txtResult.setText("Searching...");
                                    Toast.makeText(getContext(), "Searching...", Toast.LENGTH_SHORT).show();
                                    handler.postDelayed(this, 5*1000); //search mỗi 5 giây
                                }
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    runnable.run();
                } else if (btnSearch.getText().toString().equals("Stop")) {
                    // button stop
                    btnSearch.setText(R.string.btn_search);
                    txtResult.setText("");

                    if (runnable != null) {
                        handler.removeCallbacks(runnable);
                    }
                }
            }
        });
    }
}
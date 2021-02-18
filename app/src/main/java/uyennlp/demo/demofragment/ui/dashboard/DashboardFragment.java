package uyennlp.demo.demofragment.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.sql.SQLException;

import uyennlp.demo.demofragment.R;
import uyennlp.demo.demofragment.daos.StudentDAO;
import uyennlp.demo.demofragment.dtos.StudentDTO;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public DashboardFragment() {}

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Button btnAdd = getActivity().findViewById(R.id.btnAdd);

//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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

        Button btnAdd = getActivity().findViewById(R.id.btnAdd);
        EditText edtName = getActivity().findViewById(R.id.edtName);
        EditText edtAge = getActivity().findViewById(R.id.edtAge);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                int age = Integer.parseInt(edtAge.getText().toString());
                StudentDTO dto = new StudentDTO(name, age);
                try {
                    StudentDAO dao = new StudentDAO();
                    boolean check = dao.addStudent(dto);
                    if (check) {
                        Toast.makeText(getActivity(), "Success!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}